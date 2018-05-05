/*  OctoWS2811 Rainbow.ino - Rainbow Shifting Test
    http://www.pjrc.com/teensy/td_libs_OctoWS2811.html
    Copyright (c) 2013 Paul Stoffregen, PJRC.COM, LLC

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.


  Required Connections
  --------------------
    pin 2:  LED Strip #1    OctoWS2811 drives 8 LED Strips.
    pin 14: LED strip #2    All 8 are the same length.
    pin 7:  LED strip #3
    pin 8:  LED strip #4    A 100 ohm resistor should used
    pin 6:  LED strip #5    between each Teensy pin and the
    pin 20: LED strip #6    wire to the LED strip, to minimize
    pin 21: LED strip #7    high frequency ringining & noise.
    pin 5:  LED strip #8
    pin 15 & 16 - Connect together, but do not use
    pin 4 - Do not use
    pin 3 - Do not use as PWM.  Normal use is ok.
    pin 1 - Output indicating CPU usage, monitor with an oscilloscope,
            logic analyzer or even an LED (brighter = CPU busier)
*/

#include <OctoWS2811.h>

const int ledsPerStrip = 220;
const int L_start = 0,
          L_end = 17,
          E_start = 18,
          E_end = 44,
          D_start = 45,
          D_end = 76,
          B_start = 77,
          B_end = 117,
          U_start = 118,
          U_end = 146,
          R_start = 147,
          R_end = 176,
          N_start = 177,
          N_end = 219;

DMAMEM int displayMemory[ledsPerStrip * 6];
int drawingMemory[ledsPerStrip * 6];

const int config = WS2811_GRB | WS2811_800kHz;

OctoWS2811 leds(ledsPerStrip, displayMemory, drawingMemory, config);

int rainbowColors[220];

int fillLetter (int start, int end, int color) {
  for (int i = start; i <= end; i++) {
    leds.setPixel(i, color);
  }
}

// Convert HSL (Hue, Saturation, Lightness) to RGB (Red, Green, Blue)
//
//   hue:        0 to 359 - position on the color wheel, 0=red, 60=orange,
//                            120=yellow, 180=green, 240=blue, 300=violet
//
//   saturation: 0 to 100 - how bright or dull the color, 100=full, 0=gray
//
//   lightness:  0 to 100 - how light the color is, 100=white, 50=color, 0=black
//
int makeColor(unsigned int hue, unsigned int saturation, unsigned int lightness)
{
  unsigned int red, green, blue;
  unsigned int var1, var2;

  if (hue > 359) hue = hue % 360;
  if (saturation > 100) saturation = 100;
  if (lightness > 100) lightness = 100;

  // algorithm from: http://www.easyrgb.com/index.php?X=MATH&H=19#text19
  if (saturation == 0) {
    red = green = blue = lightness * 255 / 100;
  } else {
    if (lightness < 50) {
      var2 = lightness * (100 + saturation);
    } else {
      var2 = ((lightness + saturation) * 100) - (saturation * lightness);
    }
    var1 = lightness * 200 - var2;
    red = h2rgb(var1, var2, (hue < 240) ? hue + 120 : hue - 240) * 255 / 600000;
    green = h2rgb(var1, var2, hue) * 255 / 600000;
    blue = h2rgb(var1, var2, (hue >= 120) ? hue - 120 : hue + 240) * 255 / 600000;
  }
  return (red << 16) | (green << 8) | blue;
}

unsigned int h2rgb(unsigned int v1, unsigned int v2, unsigned int hue)
{
  if (hue < 60) return v1 * 60 + (v2 - v1) * hue;
  if (hue < 180) return v2 * 60;
  if (hue < 240) return v1 * 60 + (v2 - v1) * (240 - hue);
  return v1 * 60;
}

// alternate code:
// http://forum.pjrc.com/threads/16469-looking-for-ideas-on-generating-RGB-colors-from-accelerometer-gyroscope?p=37170&viewfull=1#post37170


void setup() {
  Serial.begin(9600);
  Serial.println("hello");
  leds.begin();
}

#define NUM_OF_ANIMATIONS     3     // 
#define NUM_OF_CYCLES         8    // cycles for animation
#define NUM_OF_FRAMES         100    //frames for cycle

int animationType = 0;
int frameNum = 0;
int cycleNum = 0;

void loop() {
  frameNum++;
  if (frameNum == NUM_OF_FRAMES) {
    frameNum = 0;
    cycleNum++;
    if (cycleNum == NUM_OF_CYCLES) {
      cycleNum = 0;
      animationType += 1;
      if (animationType == NUM_OF_ANIMATIONS) {
        animationType = 0;
      }
    }
  }

  double percent = (double)frameNum / NUM_OF_FRAMES;
  switch (animationType) {
    case 0:
      rainbow(percent);
      break;
    case 1:
      words(percent);
      break;
    case 2:
      letters(percent);
      break;
  }

  leds.show();
  delay(25);
}

void rainbow(double cycleTime) {
  for (int i = 0; i < ledsPerStrip; i++) {
    double hue = cycleTime + (ledsPerStrip - i - 1) / (double)ledsPerStrip;
    if (hue >= 1) hue -= 1;
    Serial.println(hue);
    int color = makeColor(floor(356 * hue), 100, 50);
    leds.setPixel(i, color);
  }
}

void words(double cycleTime) {
  double hue1 = cycleTime;
  double hue2 = cycleTime + 0.5;
  if (hue2 >= 1) hue2 -= 1;
  int color1 = makeColor(floor(356 * hue1), 100, 50);
  int color2 = makeColor(floor(356 * hue2), 100, 50);
  for (int i = 0; i < ledsPerStrip; i++) {
    if (i <= D_end) {
      leds.setPixel(i, color1);
    } else {
      leds.setPixel(i, color2);
    }
  }
}

void letters(double cycleTime) {
  double startHue = floor(cycleTime * 20) / 20;
  double lHue = startHue;
  double eHue = startHue + 19.0 / 20.0;
  if (eHue >= 1) eHue -= 1;
  double dHue = startHue + 18.0 / 20.0;
  if (dHue >= 1) dHue -= 1;
  
  
  double bHue = startHue + 7.0 / 20.0;
  while (bHue >= 1) bHue -= 1;
  double uHue = startHue + 8.0 / 20.0;
  while (uHue >= 1) uHue -= 1;
  double rHue = startHue + 9.0 / 20.0;
  while (rHue >= 1) rHue -= 1;
  double nHue = startHue + 10.0 / 20.0;
  while (nHue >= 1) nHue -= 1;

  int lColor = makeColor(floor(356 * lHue), 100, 50);
  int eColor = makeColor(floor(356 * eHue), 100, 50);
  int dColor = makeColor(floor(356 * dHue), 100, 50);
  int bColor = makeColor(floor(356 * bHue), 100, 50);
  int uColor = makeColor(floor(356 * uHue), 100, 50);
  int rColor = makeColor(floor(356 * rHue), 100, 50);
  int nColor = makeColor(floor(356 * nHue), 100, 50);

  for (int i = 0; i < ledsPerStrip; i++) {
    if (i <= L_end) {
      leds.setPixel(i, lColor);
    } else if (i <= E_end) {
      leds.setPixel(i, eColor);
    } else if (i <= D_end) {
      leds.setPixel(i, dColor);
    } else if (i <= B_end) {
      leds.setPixel(i, bColor);
    } else if (i <= U_end) {
      leds.setPixel(i, uColor);
    } else if (i <= R_end) {
      leds.setPixel(i, rColor);
    } else if (i <= N_end) {
      leds.setPixel(i, nColor);
    }
  }
}


void rainbowAll(int phaseShift, int cycleTime) {
  int color, x, y, offset, wait;

  wait = cycleTime * 1000 / 220;
  for (color = 0; color < 220; color++) {
    digitalWrite(1, HIGH);

    fillLetter(L_start, L_end, rainbowColors[color]);
    fillLetter(E_start, E_end, rainbowColors[color]);
    fillLetter(D_start, D_end, rainbowColors[color]);
    fillLetter(B_start, B_end, rainbowColors[color]);
    fillLetter(U_start, U_end, rainbowColors[color]);
    fillLetter(R_start, R_end, rainbowColors[color]);
    fillLetter(N_start, N_end, rainbowColors[color]);

    leds.show();
    digitalWrite(1, LOW);
    delayMicroseconds(wait);
  }
}

void randDots(int cycleTime) {
  int dots[8];
  for (int i = 0; i < 8; i++) {
    dots[i] = random(220);
  }

  for (int i = 0; i < 8; i++) {
    digitalWrite(1, HIGH);

    leds.setPixel(dots[i], makeColor(359, 100, 50));

    leds.show();
    digitalWrite(1, LOW);
  }

  delayMicroseconds(cycleTime);

  for (int i = 0; i < 8; i++) {
    digitalWrite(1, HIGH);

    leds.setPixel(dots[i], makeColor(0, 0, 0));

    leds.show();
    digitalWrite(1, LOW);
    //delayMicroseconds(200);
  }
}

