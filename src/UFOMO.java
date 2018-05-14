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

    private int userCode = 4;
    private boolean isTesting = true;

    private int brightnessLevel = 100; // 0 - 100

    private String gpioState = "off"; // off, on, on1, on2, on3, normal
    private long gpioLastSent = 0;
    private boolean projector1 = false;
    private boolean projector2 = false;
    private boolean projector3 = false;


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
//                isTesting = false;
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
                if (newBeat) {
                    projector1 = Math.random() > 0.5;
                    projector2 = Math.random() > 0.5;
                    projector3 = Math.random() > 0.5;
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
        if (System.currentTimeMillis() - lastEqStatus > 2000) {
            for (int i = 0; i < 8; i++) {
                eq[i] = 1;
            }
        }

        if (userCode < 0) {
            return;
        }

        System.out.println("Handle Code: "+ userCode);

        // animations
        if (userCode == 1) {
            isTesting = false;
            gpioState = "normal";
        }

        // gpio
        if (userCode == 40) {
            gpioState = "off";
        }
        if (userCode == 41) {
            gpioState = "normal";
        }
        if (userCode == 45) {
            gpioState = "on1";
        }
        if (userCode == 46) {
            gpioState = "on2";
        }
        if (userCode == 47) {
            gpioState = "on3";
        }
        if (userCode == 48) {
            gpioState = "on";
        }

        // testing
        if (userCode >= 2 && userCode <= 4) {
            tester.startWithCode(userCode);
            isTesting = true;
            gpioState = "off";
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

        // random animation
        if (userCode == 30) {
            freeStyleAnimations.newAnimation("Low");
        }
        if (userCode == 31) {
            freeStyleAnimations.newAnimation(null);
        }
        if (userCode == 32) {
            freeStyleAnimations.newAnimation("High");
        }

        // animations
        if (userCode == 93) {
            freeStyleAnimations.newAnimation("Spikes1");
        }

        if (userCode == 94) {
            freeStyleAnimations.newAnimation("Spikes2");
        }

        if (userCode == 95) {
            freeStyleAnimations.newAnimation("Spikes3");
        }

        if (userCode == 96) {
            freeStyleAnimations.newAnimation("Spikes4");
        }

        if (userCode == 97) {
            freeStyleAnimations.newAnimation("EQHue1");
        }

        if (userCode == 98) {
            freeStyleAnimations.newAnimation("EQHue2");
        }

        if (userCode == 101) {
            freeStyleAnimations.newAnimation("Alternate1");
        }
        if (userCode == 102) {
            freeStyleAnimations.newAnimation("Alternate2");
        }

        if (userCode == 103) {
            freeStyleAnimations.newAnimation("Dandoo1");
        }
        if (userCode == 104) {
            freeStyleAnimations.newAnimation("Dandoo2");
        }
        if (userCode == 105) {
            freeStyleAnimations.newAnimation("Dandoo3");
        }

        if (userCode == 106) {
            freeStyleAnimations.newAnimation("Confetti1");
        }
        if (userCode == 107) {
            freeStyleAnimations.newAnimation("Confetti2");
        }

        if (userCode == 108) {
            freeStyleAnimations.newAnimation("EQ1");
        }
        if (userCode == 109) {
            freeStyleAnimations.newAnimation("EQ2");
        }
        if (userCode == 120) {
            freeStyleAnimations.newAnimation("EQ3");
        }
        if (userCode == 121) {
            freeStyleAnimations.newAnimation("EQ4");
        }

        if (userCode == 123) {
            freeStyleAnimations.newAnimation("Rainbow1");
        }
        if (userCode == 124) {
            freeStyleAnimations.newAnimation("Rainbow2");
        }
        if (userCode == 125) {
            freeStyleAnimations.newAnimation("Rainbow3");
        }
        if (userCode == 126) {
            freeStyleAnimations.newAnimation("Rainbow4");
        }


        userCode = -1;
    }

    private long lastEqStatus = 0;

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
//                        System.out.println("Received type=" + type + " value=" + value);

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
        lastEqStatus = System.currentTimeMillis();
//        System.out.println("eq="+eq[7]);
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
                final GpioPinDigitalOutput pin7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "7", PinState.HIGH);
                final GpioPinDigitalOutput pin23 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "23", PinState.HIGH);
                final GpioPinDigitalOutput pin26 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "26", PinState.HIGH);

                pin7.high();
                pin23.high();
                pin26.high();

                boolean state7 = false;
                boolean state23 = false;
                boolean state26 = false;

                while (true) {
                    if (System.currentTimeMillis() - gpioLastSent < 250) continue;

                    if (gpioState.equals("off")) {
                        if (state7) {
                            pin7.high();
                            state7 = false;
                        }
                        if (state23) {
                            pin23.high();
                            state23 = false;
                        }
                        if (state26) {
                            pin26.high();
                            state26 = false;
                        }
                    } else if (gpioState.equals("on")) {
                        if (!state7) {
                            pin7.low();
                            state7 = true;
                        }
                        if (!state23) {
                            pin23.low();
                            state23 = true;
                        }
                        if (!state26) {
                            pin26.low();
                            state26 = true;
                        }
                    } else if (gpioState.equals("on1")) {
                        if (!state7) {
                            pin7.low();
                            state7 = true;
                        }
                        if (state23) {
                            pin23.high();
                            state23 = false;
                        }
                        if (state26) {
                            pin26.high();
                            state26 = false;
                        }
                    } else if (gpioState.equals("on2")) {
                        if (state7) {
                            pin7.high();
                            state7 = false;
                        }
                        if (!state23) {
                            pin23.low();
                            state23 = true;
                        }
                        if (state26) {
                            pin26.high();
                            state26 = false;
                        }
                    } else if (gpioState.equals("on3")) {
                        if (state7) {
                            pin7.high();
                            state7 = false;
                        }
                        if (state23) {
                            pin23.high();
                            state23 = false;
                        }
                        if (!state26) {
                            pin26.low();
                            state26 = true;
                        }
                    } else if (gpioState.equals("normal")) {
                        if (state7 != projector1) {
                            pin7.toggle();
                            state7 = projector1;
                        }

                        if (state23 != projector2) {
                            pin23.toggle();
                            state23 = projector2;
                        }

                        if (state26 != projector3) {
                            pin26.toggle();
                            state26 = projector3;
                        }
                    }
                }
            }
        }).start();
    }
}