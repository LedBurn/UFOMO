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
                if (isTesting) {
                    tester.apply(ufomoObject);
                } else {
                    freeStyleAnimations.apply(ufomoObject);
                }

                // show in simulator
                if (simulator != null) simulator.draw(ufomoSimulated, 0, 10);

                // send network
                network.send(ufomoObject);

                Thread.sleep(30);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleUserCode() {
        if (userCode < 0) {
            return;
        }

        switch (userCode) {
            case 91:
                tester.startWithCode(91);
                isTesting = true;
                break;
            case 9:
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
                            onBeat = value>0;
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
}