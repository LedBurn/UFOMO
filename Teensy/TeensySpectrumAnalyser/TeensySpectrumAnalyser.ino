#define USE_OCTOWS2811
#include <OctoWS2811.h>
#include <FastLED.h>
#include <Audio.h>
#include <Wire.h>
#include <SPI.h>
#include <SerialFlash.h>
#include <Ethernet.h>
#include <EthernetUdp.h>
#include <Keypad.h>

#define TFT_SCLK 14     // SCLK can also use pin 13
#define TFT_MOSI 7      // MOSI can also use pin 11
#define TFT_CS   10     // CS & DC can use pins 2, 6, 9, 10, 15, 20, 21, 22, 23
#define TFT_DC   20     //  but certain pairs must NOT be used: 2+10, 6+9, 20+23, 21+22
#define TFT_RST   8     // RST can use any pin
#define SD_CS     4     // CS for SD card, can use any pin
//#define GAIN  25   // multiplier for the specific mic


//********* LEDS ***********
#define NUM_LEDS 11
CRGB leds[NUM_LEDS];

#define BPM_LED_NUM 0
#define CODES_LED_NUM 1
#define NETWORK_LED_NUM 2



const byte ROWS = 4; //four rows
const byte COLS = 3; //three columns
//define the cymbols on the buttons of the keypads
char hexaKeys[ROWS][COLS] = {
  {'1', '2', '3'},
  {'4', '5', '6'},
  {'7', '8', '9'},
  {'*', '0', '#'}
};
byte rowPins[ROWS] = {7, 8, 6, 20}; //connect to the row pinouts of the keypad
byte colPins[COLS] = {21, 5, 22}; //connect to the column pinouts of the keypad
Keypad customKeypad = Keypad( makeKeymap(hexaKeys), rowPins, colPins, ROWS, COLS);

//Set-up the audio library functions
AudioInputAnalog         adc1(A9);             //default for adc1 is A2
AudioAnalyzeFFT1024      fft1024_1;
AudioConnection          patchCord1(adc1, fft1024_1);

// An array to hold the 8 frequency bands
float rawLevel[8];
float level[8];
float prevLevel[8];

// Vars for overall gain normalization
float     maxRawLevel[8];
uint32_t  maxLevTimestamp[8];
uint32_t  maxTimeout = 2000;
float     normStepSize = 2;
//float     floatingGain = 1;
uint32_t  buttonTime = 0;
char      lastKey = ' ';

// Offsets for static noise
//float noise[] = {0.00390625, 0.00390625, 0.00390625, 0.00390625, 0.01171875, 0.015625, 0.06640625, 0.02734375};
float noise[] = {0, 0, 0, 0, 0, 0, 0, 0};

// Multipliers for normalization
float normalize[] = {1.26, 1.09, 1.49, 1.26, 0.63, 0.63, 0.63, 0.212};


//********* BEAT DETECTION ***********

//#define LEVEL_HIST_DEPTH 32
#define SPIKE_HIST_DEPTH 64
#define SPIKE_THRESHOLD 0.98
#define OLD_SPIKE_AGE 1500 // in centiseconds
//float     levelHistory[8][LEVEL_HIST_DEPTH];    // last 32 levels of 8 channels
uint32_t  spikeHistory[8][SPIKE_HIST_DEPTH];    // last 32 spike positions for 8 channels
//uint32_t  prevTimestamp;                        // previous sample's timestamp (ms)
int       anchorScore = 0;                      // current anchor's score number for average calc
uint32_t  curTimestamp[8];

#define ERROR_LEN 12
#define NUM_OF_ANCHORS 8
uint32_t anchors[NUM_OF_ANCHORS];


#define MIN_BPM 76
#define MAX_BPM 150
#define MIN_BEAT_LEN ((60000/MAX_BPM)/10) // in centiseconds
#define MAX_BEAT_LEN ((60000/MIN_BPM)/10) // in centiseconds


uint16_t bestMod1 = 0;
uint16_t bestlen1 = 0;
uint16_t bestModDiff1 = 0;
uint16_t bestMod2 = 0;
uint16_t bestlen2 = 0;
uint16_t bestModDiff2 = 0;
uint16_t bestMod3 = 0;
uint16_t bestlen3 = 0;
uint16_t bestModDiff3 = 0;

//^^^^^^^^^ BEAT DETECTION ^^^^^^^^^^


//^^^^^^^^^ NETWORK ^^^^^^^^^^
EthernetUDP Udp;
byte mac[] = {0x90, 0xA2, 0xDA, 0x0D, 0x5C, 0xCD};
IPAddress ip(10, 0, 0, 205);
unsigned int localport = 8888;

IPAddress REMOTE_IP(10, 0, 0, 18);
unsigned int REMOTE_PORT = 8181;

bool lastIsOn = false;

//*********************************************************************************** SETUP
void setup() {

  Serial.begin(9600);
  //pinMode(8, INPUT_PULLUP);
  // Audio connections require memory to work.
  AudioMemory(12);

  // Configure the window algorithm to use
  fft1024_1.windowFunction(AudioWindowHanning1024);

  // Configure Network
  Ethernet.begin(mac, ip);
  Serial.print("IP : ");
  Serial.println(Ethernet.localIP());
  Udp.begin(localport);

  LEDS.addLeds<OCTOWS2811>(leds, NUM_LEDS);
  LEDS.setBrightness(30);
  //// anchors
  //setupAnchors();

  customKeypad.setHoldTime(5000);

  // clean leds
  for (int i = 0; i < NUM_LEDS; i++) {
    CHSV color = CHSV(0, 0, 0);
    leds[i] = color;
  }
  LEDS.show();
}


void loop() {

  if (fft1024_1.available()) {

    doFFT();
    calcFloatingGain();

    // codes & noise
    buttonListener();
    noiseDetection();
    showCodesLed();

    // network
    showNetworkLed();

    for (int i = 0; i < 8; i++) {
      float lev = rawLevel[i] - noise[i];
      if (lev < 0)
      {
        level[i] = 0;
      }
      lev *= normalize[i];
      level[i] = lev;
    }

    bool isSpike = saveSpikes();
    if (isSpike) {
      bpm();
    }
    showAndSendBpm();


    uint8_t val = floor(level[7] * 9);
    for (int i = 0; i < 8; i++) {
      CHSV color = CHSV(112 - 16 * i, 250, 100);
      CHSV black = CHSV(0, 0, 0);
      if (i >= val) {
        leds[i+3] = black;
      } else {
        leds[i+3] = color;
      }
    }
    sendFFT();
    LEDS.show();
  }
}



bool isSpike(int channel) {
  bool spike = level[channel] >= SPIKE_THRESHOLD
               && level[channel] > prevLevel[channel];
  prevLevel[channel] = level[channel];
  return spike;
}

bool saveSpikes() {
  bool ret = false;
  for (int i = 0; i < 8; i++)
  {
    if (isSpike(i))
    {
      ret = true;
      for (int j = 0; j < SPIKE_HIST_DEPTH - 1; j++)
      {
        spikeHistory[i][j] = spikeHistory[i][j + 1];
      }
      spikeHistory[i][SPIKE_HIST_DEPTH - 1] = curTimestamp[i];
    }
  }
  return ret;
}

void bpm() {
  uint32_t startTime = millis();
  uint16_t bestMods[MAX_BEAT_LEN - MIN_BEAT_LEN + 1];
  uint16_t bestModsDiff[MAX_BEAT_LEN - MIN_BEAT_LEN + 1];

  for (uint16_t len = MIN_BEAT_LEN; len <= MAX_BEAT_LEN; len++) {
    // calcuate for each spike - mod for this length
    uint8_t mod[len];
    for (uint16_t i = 0; i < len; i++) {
      mod[i] = 0;
    }
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < SPIKE_HIST_DEPTH; j++) {
        if (spikeHistory[i][j] > 0 && millis() / 10 - spikeHistory[i][j] < OLD_SPIKE_AGE) {
          uint8_t realMod = spikeHistory[i][j] % len;
          while (realMod < 0) {
            realMod += len;
          }
          while (realMod >= len) {
            realMod -= len;
          }
          mod[realMod] += 1;
        }
      }
    }

    // find the best mod
    uint16_t bestModVal = 0;
    uint16_t bestModDiff = 0;
    for (uint16_t i = 0; i < len; i++) {
      uint16_t modVal = mod[i];
      if (i == 0) {
        modVal += mod[i + 1] + mod[len - 1];
      } else if (i == len - 1) {
        modVal += mod[i - 1] + mod[0];
      } else {
        modVal += mod[i - 1] + mod[i + 1];
      }

      if (modVal > bestModVal) {
        bestModVal = modVal;
        bestModDiff = i;
      }
    }

    bestMods[len - MIN_BEAT_LEN] = bestModVal;
    bestModsDiff[len - MIN_BEAT_LEN] = bestModDiff;
  }


  // find the best mod of all lengths
  bestMod1 = 0;
  bestlen1 = 0;
  bestModDiff1 = 0;
  bestMod2 = 0;
  bestlen2 = 0;
  bestModDiff2 = 0;
  bestMod3 = 0;
  bestlen3 = 0;
  bestModDiff3 = 0;
  for (uint16_t len = MIN_BEAT_LEN; len <= MAX_BEAT_LEN; len++) {
    if (bestMods[len - MIN_BEAT_LEN] > bestMod1) {
      bestMod3 = bestMod2;
      bestlen3 = bestlen2;
      bestModDiff3 = bestModDiff2;
      bestMod2 = bestMod1;
      bestlen2 = bestlen1;
      bestModDiff2 = bestModDiff1;
      bestMod1 = bestMods[len - MIN_BEAT_LEN];
      bestlen1 = len;
      bestModDiff1 = bestModsDiff[len - MIN_BEAT_LEN];
    } else if (bestMods[len - MIN_BEAT_LEN] > bestMod2) {
      bestMod3 = bestMod2;
      bestlen3 = bestlen2;
      bestModDiff3 = bestModDiff2;
      bestMod2 = bestMods[len - MIN_BEAT_LEN];
      bestlen2 = len;
      bestModDiff2 = bestModsDiff[len - MIN_BEAT_LEN];
    } else if (bestMods[len - MIN_BEAT_LEN] > bestMod3) {
      bestMod3 = bestMods[len - MIN_BEAT_LEN];
      bestlen3 = len;
      bestModDiff3 = bestModsDiff[len - MIN_BEAT_LEN];
    }
  }

  Serial.print("Time:");
  Serial.print(millis() - startTime);

  Serial.print("      BPM1: ");
  Serial.print(6000.0 / bestlen1);
  Serial.print(" length: ");
  Serial.print(bestlen1);
  Serial.print(" score:");
  Serial.print(bestMod1);
  Serial.print(" diff:");
  Serial.print(bestModDiff1);

  Serial.print("     BPM2: ");
  Serial.print(6000.0 / bestlen2);
  Serial.print(" length: ");
  Serial.print(bestlen2);
  Serial.print(" score:");
  Serial.print(bestMod2);
  Serial.print(" diff:");
  Serial.print(bestModDiff2);

  Serial.print("    BPM3: ");
  Serial.print(6000.0 / bestlen3);
  Serial.print(" length: ");
  Serial.print(bestlen3);
  Serial.print(" score:");
  Serial.print(bestMod3);
  Serial.print(" diff:");
  Serial.println(bestModDiff3);
}

#define BPM_PACKET_TYPE 1

void showAndSendBpm() {
  // calculate if peak is now, and send to led0
  bool isOn;
  if (abs(millis() / 10 % bestlen1 - bestModDiff1) < 10) {
    leds[BPM_LED_NUM] = CHSV(160, 250, 255);
    isOn = true;
  } else {
    leds[BPM_LED_NUM] = CHSV(0, 0, 0);
    isOn = false;
  }


  // send isOn to remote ip
  if (isOn == lastIsOn) {
    // no need to send it again
    return;
  }

  uint8_t data[1];
  data[0] = isOn ? 1 : 0;
  sendToRemote(BPM_PACKET_TYPE, data, 1);
  lastIsOn = isOn;
}

uint16_t bestBeatLength(uint16_t *options, uint16_t numOfOptions) {
  uint32_t anchor = anchors[NUM_OF_ANCHORS - 1];

  uint16_t matches[numOfOptions];
  for (uint16_t optionNum = 0; optionNum < numOfOptions; optionNum++) {
    matches[optionNum] = 0;
    uint16_t  beatLength = options[optionNum];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < SPIKE_HIST_DEPTH; j++) {
        uint32_t spike = spikeHistory[i][j];
        uint32_t diff = abs(spike - anchor);
        //uint32_t diff = abs(spikeHistory[i][SPIKE_HIST_DEPTH/2] - spike);
        int16_t mod = diff % beatLength;
        int16_t mod2 = diff % (beatLength / 2);
        int16_t mod3 = diff % ((beatLength * 2) / 3);

        if (abs(mod) < ERROR_LEN || abs(mod2) < ERROR_LEN || abs(mod3) < ERROR_LEN) {
          //       if (abs(mod) < ERROR_LEN || abs(mod2) < ERROR_LEN) {
          matches[optionNum] += 1;
        }
      }
    }
  }

  uint16_t maxMatch1 = 0;
  uint16_t maxMatch2 = 0;
  uint16_t maxMatch3 = 0;
  uint16_t bestBeatLen1 = 0;
  uint16_t bestBeatLen2 = 0;
  uint16_t bestBeatLen3 = 0;
  for (uint16_t optionNum = 0; optionNum < numOfOptions; optionNum++) {
    if (matches[optionNum] > maxMatch1) {
      maxMatch3 = maxMatch2;
      bestBeatLen3 = bestBeatLen2;
      maxMatch2 = maxMatch1;
      bestBeatLen2 = bestBeatLen1;
      maxMatch1 = matches[optionNum];
      bestBeatLen1 = options[optionNum];
    } else if (matches[optionNum] > maxMatch2) {
      maxMatch3 = maxMatch2;
      bestBeatLen3 = bestBeatLen2;
      maxMatch2 = matches[optionNum];
      bestBeatLen2 = options[optionNum];
    } else if (matches[optionNum] > maxMatch3) {
      maxMatch3 = matches[optionNum];
      bestBeatLen3 = options[optionNum];
    }
  }

  Serial.print("BPM: ");
  Serial.print(60000 / bestBeatLen1);
  Serial.print(" ");
  Serial.print(maxMatch1);
  Serial.print("    ");
  Serial.print(60000 / bestBeatLen2);
  Serial.print(" ");
  Serial.print(maxMatch2);
  Serial.print("    ");
  Serial.print(60000 / bestBeatLen3);
  Serial.print(" ");
  Serial.println(maxMatch3);
  return bestBeatLen1;
}

void calcFloatingGain ()
{
  uint32_t curTime = millis();
  for (int i = 0; i < 8; i++) {

    if (rawLevel[i] >= maxRawLevel[i]) {
      // reached or passed the peak, update vars
      maxRawLevel[i] = rawLevel[i];
      maxLevTimestamp[i] = curTime;
    } else if (curTime - maxLevTimestamp[i] >= maxTimeout) {
      // did not reach peak in the timeout window, decrease peak
      maxRawLevel[i] /= normStepSize;
      maxLevTimestamp[i] = curTime;
    }
    if (maxLevTimestamp[i] == curTime && maxRawLevel[i] > noise[i]) {
      float lev = maxRawLevel[i] - noise[i];
      normalize[i] = 1 / lev;
    }
  }
}

//////////////////////////////////////////////////////////
//-------------- CODES & NOISE -------------------------//
//////////////////////////////////////////////////////////
#define CODES_PACKET_TYPE 2
#define LED_TIME 100
#define NOSIE_DETECTION_INTERVAL 5000

long startCodeTime = 0;
long pressedTime = 0;
long sendingTime = 0;
long noiseStartTime = 0;

bool detectingNoise = false;
float maxNoise[8];

void buttonListener() {
  // new keys
  char customKey = customKeypad.getKey();
  if (customKey != NO_KEY && customKey != ' ' && customKey != lastKey) {
    lastKey = customKey;
    handleKey(customKey);
  }
}

uint8_t code = 0;
void handleKey(char key) {
  if (key == '*') {
    startCodeTime = millis();
    code = 0;
  } else if (key == '#') {
    if (code > 0) {
      sendCode(code);
      sendingTime = millis();
      code = 0;
    }
  } else {
    pressedTime = millis();
    code = code * 10 + (key - '0');
  }
}

void sendCode(uint8_t code) {
  Serial.print("sending ");
  Serial.println(code);

  if (code == 13) {
    startNosie();
    return;
  }
  uint8_t data[1];
  data[0] = code;
  sendToRemote(CODES_PACKET_TYPE, data, 1);
}

void startNosie() {
  for (int i = 0; i < 8; i++) {
    maxNoise[i] = 0;
  }
  noiseStartTime = millis();
  detectingNoise = true;
  Serial.println("Start noise detection");
}

void noiseDetection() {
  if (!detectingNoise) {
    return;
  }

  // done
  if (millis() - noiseStartTime > 5000) {
    detectingNoise = false;
    Serial.print("Noise detection [ ");
    for (int i = 0; i < 8; i++) {
      noise[i] = maxNoise[i];
      Serial.print(noise[i]);
      Serial.print(" ");
    }
    Serial.println("]");
    return;
  }

  // detecting
  for (int i = 0; i < 8; i++) {
    if (rawLevel[i] > maxNoise[i]) {
      maxNoise[i] = rawLevel[i];
    }
  }
}

void showCodesLed() {
  if (millis() - startCodeTime < LED_TIME) {
    leds[CODES_LED_NUM] = CHSV(50, 250, 255);
  } else if (millis() - pressedTime < LED_TIME) {
    leds[CODES_LED_NUM] = CHSV(50, 250, 255);
  } else if (millis() - sendingTime < LED_TIME * 3) {
    leds[CODES_LED_NUM] = CHSV(25, 250, 255);
  } else if (detectingNoise) {
    leds[CODES_LED_NUM] = CHSV(200, 250, 200);
  } else {
    leds[CODES_LED_NUM] = CHSV(0, 0, 0);
  }
}

//////////////////////////////////////////////////////////
//-------------- NETWORK -------------------------------//
//////////////////////////////////////////////////////////
long networkErrorStartTime = 0;
long networkSuccessStartTime = 0;

void sendToRemote(uint8_t type, uint8_t value[], uint8_t valueSize) {
  int success = Udp.beginPacket(REMOTE_IP, REMOTE_PORT);
  if (!success) {
    Serial.println("network error1");
    networkErrorStartTime = millis();
    return;
  }
  Udp.write(type);
  Udp.write(value, valueSize);
  success = Udp.endPacket();
  if (!success) {
    Serial.println("network error2");
    networkErrorStartTime = millis();
    return;
  }

  networkSuccessStartTime = millis();
}

void showNetworkLed() {
  if (millis() - networkErrorStartTime < LED_TIME * 5) {
    leds[NETWORK_LED_NUM] = CHSV(0, 250, 70);
  } else if (millis() - networkSuccessStartTime < LED_TIME * 5) {
    leds[NETWORK_LED_NUM] = CHSV(100, 250, 70);
  } else {
    leds[NETWORK_LED_NUM] = CHSV(0, 0, 0);
  }
}


//////////////////////////////////////////////////////////
//-------------- FFT -----------------------------------//
//////////////////////////////////////////////////////////
#define FFT_PACKET_TYPE 3

void doFFT() {
  // multiply bin number by 43.06640625 to get frequency
  curTimestamp[0] = millis() / 10;
  rawLevel[0] =  fft1024_1.read(0, 1);
  curTimestamp[1] = millis() / 10;
  rawLevel[1] =  fft1024_1.read(2, 5);
  curTimestamp[2] = millis() / 10;
  rawLevel[2] =  fft1024_1.read(6, 12);
  curTimestamp[3] = millis() / 10;
  rawLevel[3] =  fft1024_1.read(13, 25);
  curTimestamp[4] = millis() / 10;
  rawLevel[4] =  fft1024_1.read(26, 115);
  curTimestamp[5] = millis() / 10;
  rawLevel[5] =  fft1024_1.read(116, 227);
  curTimestamp[6] = millis() / 10;
  rawLevel[6] =  fft1024_1.read(228, 511);
  curTimestamp[7] = millis() / 10;
  rawLevel[7] =  fft1024_1.read(3, 230);
  //  rawLevel[7] = 0;
  //  for (int i = 0; i < 7; i++) {
  //    rawLevel[7] += rawLevel[i] * normalize[i];
  //  }
  //  rawLevel[7] /= 7;
}

void sendFFT() {
  uint8_t data[8];
  for (int i=0; i<8; i++) {
    data[i] = min(floor(level[i] * 128), 127);
  }
  sendToRemote(FFT_PACKET_TYPE, data, 8);
}


