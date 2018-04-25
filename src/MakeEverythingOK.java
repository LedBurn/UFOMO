import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class MakeEverythingOK {

    private static final double CYCLE_TIME = 2000; //millisecond
    private static double currentTime = 0; //millisecond

    public static void run(final boolean runSimulator) {

        final Network network = new Network();
        network.configure();
        final Simulator s = new Simulator();

        UFOMOObject ufomoObject = new UFOMOObject();
        UFOMOSimulated ufomoSimulated = new UFOMOSimulated(ufomoObject);

        //EffectToObjectMapper mapper = new EffectToObjectMapper(getEffect(largeCircle.GetPixelsNumber()), largeCircle.GetAllPixels(), range(0,largeCircle.GetPixelsNumber()-1));

        try {
            while (true) {
                Thread.sleep(30);
                //mapper.apply(currentTime / CYCLE_TIME );

                s.draw(ufomoSimulated, 0, 10);
                currentTime += 20;
                if (currentTime >= CYCLE_TIME) {
                    currentTime -= CYCLE_TIME;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            boolean lastOn = false;
                            DatagramSocket socket = new DatagramSocket(8181);
                            socket.setBroadcast(true);
                            System.out.println("Listen on " + socket.getLocalAddress() + " from " + socket.getInetAddress() + " port " + socket.getBroadcast());
                            byte[] buf = new byte[1];
                            DatagramPacket packet = new DatagramPacket(buf, buf.length);
                            while (true) {
//                                System.out.println("Waiting for data");
                                socket.receive(packet);
                                if (buf[0]!=0 != lastOn) {
                                    lastOn = buf[0]!=0;
                                    System.out.println("Data received " + buf[0] +" " + Math.random());
                                }
                            }
                        } catch (SocketException e) {
//                            e.printStackTrace();
                        } catch (IOException e) {
//                            e.printStackTrace();
                        }
                    }
                }).start();




                // 1. Listen to user input

                // 2. get fft

                // 3. draw LEDs


            }
//                Integer userInput = null;
//
//                int invitationCallNumber = 0;
//                while (userInput == null && keyPedServer.isActive()) {
//                    // invitation text and then a minute of silence
//                    Song invitation = new Invitation(network, audio, s, soundsPath, keyPedServer, invitationCallNumber++);
//                    userInput = invitation.play();
//                    if (userInput == null) {
//                        Song silence = new Silence(network, audio, s, soundsPath, keyPedServer);
//                        userInput = silence.play();
//                    }
//                }
//
//                makeDigitSound(network, audio, s, soundsPath, keyPedServer, userInput);
//                System.gc();
//                int numberOfHotlinePlays = 0;
//                userInput = null;
//                while (userInput == null && numberOfHotlinePlays < 3 && keyPedServer.isActive()) {
//                    Song hotline = new Hotline(network, audio, s, soundsPath, keyPedServer);
//                    userInput = hotline.play();
//
//                    if (userInput == null) {
//                        Song silence15sec = new Silence15Sec(network, audio, s, soundsPath, keyPedServer);
//                        userInput = silence15sec.play();
//                    }
//                    numberOfHotlinePlays++;
//
//                    if (userInput != null && userInput == 9) {
//                        makeDigitSound(network, audio, s, soundsPath, keyPedServer, userInput);
//                        userInput = null;
//                        numberOfHotlinePlays = 0;
//                    }
//                }
//
//                if (userInput == null) {
//                    continue;
//                }
//
//                // we know that userInput is available
//                makeDigitSound(network, audio, s, soundsPath, keyPedServer, userInput);
//                System.gc();
//                Song silence2Sec = new Silence2Sec(network, audio, s, soundsPath, keyPedServer);
//                silence2Sec.play();
//
//                Song songToPlay = getSongByDigit(network, audio, s, soundsPath, keyPedServer, userInput);
//
//                if (songToPlay != null) {
//                    songToPlay.play();
//
//                    if (userInput >= 1 && userInput <= 7) {
//                        Song allOk = new AllOk(network, audio, s, soundsPath, keyPedServer);
//                        allOk.play();
//                    }
//                }
//
//                songToPlay = null;
//                System.gc();
//                Song silence15sec = new Silence15Sec(network, audio, s, soundsPath, keyPedServer);
//                silence15sec.play();
//            }
//
//
//            /*Song pen = new PenPineappleApplePen(network, audio, s, soundsPath, keyPedServer);
//            userInput = pen.play();
//            System.out.println("song ended. userInput: " + userInput);*/
//            /*Song sun = new HereComesTheSun(network, audio, s, soundsPath, keyPedServer);
//            userInput = sun.play();
//            System.out.println("song ended. userInput: " + userInput);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static DiscreteEffect getEffect(int numOfPixels) {
        // ----------------------------------------- //
        // Change this section to test other effects //
        // ----------------------------------------- //
        ContinuousWhiteEffect effect = new ContinuousWhiteEffect();
        ContinuousRainbowEffect effect1 = new ContinuousRainbowEffect(effect);
        ContinuousCyclicMoveEffect effect2 = new ContinuousCyclicMoveEffect(effect1);
        ContinuousToDiscrete discrete = new ContinuousToDiscrete(numOfPixels, effect2);
        return discrete;
        // ----------------------------------------- //
    }

    private static int[] range(int firstIndex, int lastIndex) {
        int allIndexes[] = new int[lastIndex - firstIndex + 1];
        for(int i=firstIndex; i <= lastIndex; i++) {
            allIndexes[i-firstIndex] = i;
        }
        return allIndexes;
    }
}


