import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

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

    private int userCode = 91;
    private boolean isTesting = true;

    public UFOMO(boolean runSimulator) {
        network = new UFOMONetwork();
        ufomoObject = new UFOMOObject();
        simulator = runSimulator ? new Simulator() : null;
        ufomoSimulated = runSimulator ? new UFOMOSimulated(ufomoObject) : null;
        tester = new TestLeds();
        freeStyleAnimations = new FreeStyleAnimations();
        startListening();
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
                    freeStyleAnimations.apply(ufomoObject, newBeat, percentToNextBeat);
                }
                newBeat = false;

                // show in simulator
                if (simulator != null) simulator.draw(ufomoSimulated, 0, 10);

                // send network
                network.send(ufomoObject);

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

        if (userCode >= 90 && userCode <= 99) {
            tester.startWithCode(userCode);
            isTesting = true;
        } else {
            isTesting = false;
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
                    byte[] buf = new byte[2];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    while (true) {
                        socket.receive(packet);

                        int type = buf[0];
                        int value = buf[1];
                        System.out.println("Received type=" + type + " value=" + value);

                        if (type == 1) { // beat
                            handleBeatInput(value>0);
                        } else if (type == 2) { // code
                            userCode = value;
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
            System.out.println("New Beat " + Math.random());
        }
        onBeat = newOnBeat;
    }
}