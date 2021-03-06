//import com.pi4j.io.gpio.*;
//
//public class UFOMO {
//
//    private final UFOMONetworkDataSource network; // network handler
//    private final UFOMOObject ufomoObject; // main UFOMO object
//
//    private final Simulator simulator;
//    private final UFOMOSimulated ufomoSimulated;
//
//    private final UFOMOTestLEDs tester;
//    private final UFOMOAnimationsProvider freeStyleAnimations;
//
//    private final Keypad keypad;
//
//    private boolean isTesting = true;
//
//
//    private int brightnessLevel = 100; // 0 - 100
//
//    private String gpioState = "off"; // off, on, on1, on2, on3, normal
//    private long gpioLastSent = 0;
//    private boolean projector1 = false;
//    private boolean projector2 = false;
//    private boolean projector3 = false;
//
//
//    public UFOMO(boolean runSimulator, boolean runGPIO) {
//        network = new UFOMONetworkDataSource();
//        ufomoObject = new UFOMOObject();
//        simulator = runSimulator ? new Simulator() : null;
//        ufomoSimulated = runSimulator ? new UFOMOSimulated(ufomoObject) : null;
//        tester = new UFOMOTestLEDs();
//        freeStyleAnimations = new UFOMOAnimationsProvider();
//
//        keypad = new Keypad();
//        keypad.startListening();
//
//
//        if (runGPIO) gpio();
//    }
//
//    public void run() {
//        try {
//            while (true) {
//
//                // user code
//                handleUserCode(keypad.userCode);
//
//                // apply animations
////                isTesting = false;
//                if (isTesting) {
//                    tester.apply(ufomoObject);
//
//                } else {
//                    long currentTime = System.currentTimeMillis();
//                    if (currentTime - keypad.lastBeatTime > 1000) { // if there's no beat for a long time - fake one
//                        keypad.lastBeatTime = currentTime;
//                        keypad.nextBeatTime = currentTime + 1000;
//                        keypad.newBeat = true;
//                    }
//
//
////                    for (int i = 0; i < eq.length; i++) {
////                        eq[i] = (int)Math.floor(Math.random() * 50) + 50;
////                    }
//                    double percentToNextBeat = ((double)(currentTime - keypad.lastBeatTime)) / (keypad.nextBeatTime - keypad.lastBeatTime);
//                    freeStyleAnimations.apply(ufomoObject, keypad.newBeat, keypad.onBeat, percentToNextBeat, keypad.eq);
//                }
//                if (keypad.newBeat) {
//                    projector1 = Math.random() > 0.5;
//                    projector2 = Math.random() > 0.5;
//                    projector3 = Math.random() > 0.5;
//                }
//
//                keypad.newBeat = false;
//
//                // show in simulator
//                if (simulator != null) simulator.draw(ufomoSimulated, 0, 10);
//
//                // send network
//                network.send(ufomoObject, brightnessLevel / 100.0d);
//
//                Thread.sleep(20);
//
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void handleUserCode(int userCode) {
//        if (System.currentTimeMillis() - keypad.lastEqStatus > 2000) {
//            for (int i = 0; i < 8; i++) {
//                keypad.eq[i] = 1;
//            }
//        }
//
//        if (userCode < 0) {
//            return;
//        }
//
//        System.out.println("Handle Code: "+ userCode);
//
//        // animations
//        if (userCode == 1) {
//            isTesting = false;
//            gpioState = "normal";
//        }
//
//        // gpio
//        if (userCode == 40) {
//            gpioState = "off";
//        }
//        if (userCode == 41) {
//            gpioState = "normal";
//        }
//        if (userCode == 45) {
//            gpioState = "on1";
//        }
//        if (userCode == 46) {
//            gpioState = "on2";
//        }
//        if (userCode == 47) {
//            gpioState = "on3";
//        }
//        if (userCode == 48) {
//            gpioState = "on";
//        }
//
//        // testing
//        if (userCode >= 2 && userCode <= 4) {
//            tester.startWithCode(userCode);
//            isTesting = true;
//            gpioState = "off";
//        }
//
//        // brightness
//        if (userCode == 20) {
//            brightnessLevel = Math.max(brightnessLevel - 1, 0);
//        }
//        if (userCode == 21) {
//            brightnessLevel = Math.min(brightnessLevel + 1, 100);
//        }
//        if (userCode == 24) {
//            brightnessLevel = Math.max(brightnessLevel - 10, 0);
//        }
//        if (userCode == 25) {
//            brightnessLevel = Math.min(brightnessLevel + 10, 100);
//        }
//
//        // random animation
//        if (userCode == 30) {
//            freeStyleAnimations.newAnimation("Low");
//        }
//        if (userCode == 31) {
//            freeStyleAnimations.newAnimation(null);
//        }
//        if (userCode == 32) {
//            freeStyleAnimations.newAnimation("High");
//        }
//
//        // animations
//        if (userCode == 93) {
//            freeStyleAnimations.newAnimation("Spikes1");
//        }
//
//        if (userCode == 94) {
//            freeStyleAnimations.newAnimation("Spikes2");
//        }
//
//        if (userCode == 95) {
//            freeStyleAnimations.newAnimation("Spikes3");
//        }
//
//        if (userCode == 96) {
//            freeStyleAnimations.newAnimation("Spikes4");
//        }
//
//        if (userCode == 97) {
//            freeStyleAnimations.newAnimation("EQHue1");
//        }
//
//        if (userCode == 98) {
//            freeStyleAnimations.newAnimation("EQHue2");
//        }
//
//        if (userCode == 101) {
//            freeStyleAnimations.newAnimation("Alternate1");
//        }
//        if (userCode == 102) {
//            freeStyleAnimations.newAnimation("Alternate2");
//        }
//
//        if (userCode == 103) {
//            freeStyleAnimations.newAnimation("Dandoo1");
//        }
//        if (userCode == 104) {
//            freeStyleAnimations.newAnimation("Dandoo2");
//        }
//        if (userCode == 105) {
//            freeStyleAnimations.newAnimation("Dandoo3");
//        }
//
//        if (userCode == 106) {
//            freeStyleAnimations.newAnimation("Confetti1");
//        }
//        if (userCode == 107) {
//            freeStyleAnimations.newAnimation("Confetti2");
//        }
//
//        if (userCode == 108) {
//            freeStyleAnimations.newAnimation("EQ1");
//        }
//        if (userCode == 109) {
//            freeStyleAnimations.newAnimation("EQ2");
//        }
//        if (userCode == 120) {
//            freeStyleAnimations.newAnimation("EQ3");
//        }
//        if (userCode == 121) {
//            freeStyleAnimations.newAnimation("EQ4");
//        }
//
//        if (userCode == 123) {
//            freeStyleAnimations.newAnimation("Rainbow1");
//        }
//        if (userCode == 124) {
//            freeStyleAnimations.newAnimation("Rainbow2");
//        }
//        if (userCode == 125) {
//            freeStyleAnimations.newAnimation("Rainbow3");
//        }
//        if (userCode == 126) {
//            freeStyleAnimations.newAnimation("Rainbow4");
//        }
//
//
//        userCode = -1;
//    }
//
//    private void gpio() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // create gpio controller
//                System.out.println("--> create gpio controller");
//                final GpioController gpio = GpioFactory.getInstance();
//
//                // provision gpio pin #01 as an output pin and turn on
//                System.out.println("--> provision gpio pin #07 as an output pin and turn on");
//                final GpioPinDigitalOutput pin7 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "7", PinState.HIGH);
//                final GpioPinDigitalOutput pin23 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "23", PinState.HIGH);
//                final GpioPinDigitalOutput pin26 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "26", PinState.HIGH);
//
//                pin7.high();
//                pin23.high();
//                pin26.high();
//
//                boolean state7 = false;
//                boolean state23 = false;
//                boolean state26 = false;
//
//                while (true) {
//                    if (System.currentTimeMillis() - gpioLastSent < 250) continue;
//
//                    if (gpioState.equals("off")) {
//                        if (state7) {
//                            pin7.high();
//                            state7 = false;
//                        }
//                        if (state23) {
//                            pin23.high();
//                            state23 = false;
//                        }
//                        if (state26) {
//                            pin26.high();
//                            state26 = false;
//                        }
//                    } else if (gpioState.equals("on")) {
//                        if (!state7) {
//                            pin7.low();
//                            state7 = true;
//                        }
//                        if (!state23) {
//                            pin23.low();
//                            state23 = true;
//                        }
//                        if (!state26) {
//                            pin26.low();
//                            state26 = true;
//                        }
//                    } else if (gpioState.equals("on1")) {
//                        if (!state7) {
//                            pin7.low();
//                            state7 = true;
//                        }
//                        if (state23) {
//                            pin23.high();
//                            state23 = false;
//                        }
//                        if (state26) {
//                            pin26.high();
//                            state26 = false;
//                        }
//                    } else if (gpioState.equals("on2")) {
//                        if (state7) {
//                            pin7.high();
//                            state7 = false;
//                        }
//                        if (!state23) {
//                            pin23.low();
//                            state23 = true;
//                        }
//                        if (state26) {
//                            pin26.high();
//                            state26 = false;
//                        }
//                    } else if (gpioState.equals("on3")) {
//                        if (state7) {
//                            pin7.high();
//                            state7 = false;
//                        }
//                        if (state23) {
//                            pin23.high();
//                            state23 = false;
//                        }
//                        if (!state26) {
//                            pin26.low();
//                            state26 = true;
//                        }
//                    } else if (gpioState.equals("normal")) {
//                        if (state7 != projector1) {
//                            pin7.toggle();
//                            state7 = projector1;
//                        }
//
//                        if (state23 != projector2) {
//                            pin23.toggle();
//                            state23 = projector2;
//                        }
//
//                        if (state26 != projector3) {
//                            pin26.toggle();
//                            state26 = projector3;
//                        }
//                    }
//                }
//            }
//        }).start();
//    }
//}