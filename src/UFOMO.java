import com.pi4j.io.gpio.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UFOMO {

    private static final int INCOMING_PORT = 8181;

    private final UFOMONetwork network; // network handler
    private final UFOMOObject ufomoObject; // main UFOMO object

    private final Simulator simulator;
    private final UFOMOSimulated ufomoSimulated;

    private final TestLeds tester;
    private final FreeStyleAnimations freeStyleAnimations;

    private boolean onBeat = false;
    private boolean newBeat = false;
    private long lastBeatTime = 0;
    private long nextBeatTime = 100;

    private int[] eq = new int[8];

    private int userCode = 98;
    private boolean isTesting = true;

    private int brightnessLevel = 100; // 0 - 100

    public UFOMO(boolean runSimulator, boolean runGPIO) {
        network = new UFOMONetwork();
        ufomoObject = new UFOMOObject();
        simulator = runSimulator ? new Simulator() : null;
        ufomoSimulated = runSimulator ? new UFOMOSimulated(ufomoObject) : null;
        tester = new TestLeds();
        freeStyleAnimations = new FreeStyleAnimations();
        startListening();
        if (runGPIO) gpio();
    }

    public void run() {
        try {
            while (true) {

                // user code
                handleUserCode();

                // apply animations
                isTesting = false;
                if (isTesting) {
                    tester.apply(ufomoObject);

                } else {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastBeatTime > 1000) { // if there's no beat for a long time - fake one
                        lastBeatTime = currentTime;
                        nextBeatTime = currentTime + 1000;
                        newBeat = true;
                    }
                    double percentToNextBeat = ((double)(currentTime - lastBeatTime)) / (nextBeatTime - lastBeatTime);
                    freeStyleAnimations.apply(ufomoObject, newBeat, onBeat, percentToNextBeat, eq);
                }
                newBeat = false;

                // show in simulator
                if (simulator != null) simulator.draw(ufomoSimulated, 0, 10);

                // send network
                network.send(ufomoObject, brightnessLevel / 100.0d);

                Thread.sleep(20);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleUserCode() {
        if (userCode < 0) {
            return;
        }

        // testing
        if (userCode >= 90 && userCode <= 99) {
            tester.startWithCode(userCode);
            isTesting = true;
        }

        // animations
        if (userCode == 1) {
            isTesting = false;
        }

        // brightness
        if (userCode == 20) {
            brightnessLevel = Math.max(brightnessLevel - 1, 0);
        }
        if (userCode == 21) {
            brightnessLevel = Math.min(brightnessLevel + 1, 100);
        }
        if (userCode == 24) {
            brightnessLevel = Math.max(brightnessLevel - 10, 0);
        }
        if (userCode == 25) {
            brightnessLevel = Math.min(brightnessLevel + 10, 100);
        }


        userCode = -1;
    }

    private void startListening() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramSocket socket = new DatagramSocket(INCOMING_PORT);
                    socket.setBroadcast(true);
                    System.out.println("Listen on " + socket.getLocalAddress() + " from " + socket.getInetAddress() + " port " + socket.getBroadcast());
                    byte[] buf = new byte[9];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    while (true) {
                        socket.receive(packet);

                        int type = buf[0];
                        int value = buf[1];
                        //System.out.println("Received type=" + type + " value=" + value);

                        if (type == 1) { // beat
                            handleBeatInput(value>0);
                        } else if (type == 2) { // code
                            userCode = value;
                        } else if (type == 3) { // fft
                            handleEQ(buf);
                        }
                    }
                } catch (SocketException e) {
                    // do nothing
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void handleBeatInput(boolean newOnBeat) {
        long currentTime = System.currentTimeMillis();
        if (!onBeat && newOnBeat && currentTime - lastBeatTime > 250) { // new beat
            long nextBeat = currentTime + currentTime - lastBeatTime;
            lastBeatTime = currentTime;
            nextBeatTime = nextBeat;
            newBeat = true;
        }
        onBeat = newOnBeat;
    }

    private void handleEQ(byte[] buf) {
        for (int i = 0; i < 8; i++) {
            int value = buf[i+1];
            eq[i] = value;
        }
    }

    private void gpio() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // create gpio controller
                System.out.println("--> create gpio controller");
                final GpioController gpio = GpioFactory.getInstance();

                // provision gpio pin #01 as an output pin and turn on
                System.out.println("--> provision gpio pin #07 as an output pin and turn on");
                final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "MyLED", PinState.HIGH);

                // set shutdown state for this pin
//                pin.setShutdownOptions(true, PinState.LOW);

                while (true) {
                    pin.high();
                    System.out.println("--> GPIO state should be: OFF");

                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // turn off gpio pin #01
                    pin.low();
                    System.out.println("--> GPIO state should be: ON");

                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}