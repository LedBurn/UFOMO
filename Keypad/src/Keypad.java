import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Keypad {

    private static final int INCOMING_PORT = 8181;

    public int userCode = 0;

    public boolean onBeat = false;
    public boolean newBeat = false;
    public long lastBeatTime = 0;
    public long nextBeatTime = 100;

    public int[] eq = new int[8];
    public long lastEqStatus = 0;


    public void startListening() {
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

                        if (type == 1) { // beat
                            handleBeatInput(value > 0);
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
    }
}
