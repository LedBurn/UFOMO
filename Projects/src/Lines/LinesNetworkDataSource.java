import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class LinesNetworkDataSource implements INetworkDataSource<LinesObject> {
    private Controller t1;
    private Controller t2;

    private Segment[] segments = new Segment[16];

    @Override
    public void configure(Network network) {
        try {
            t1 = new Controller("T1", InetAddress.getByName("10.0.0.201"));
            t2 = new Controller("T2", InetAddress.getByName("10.0.0.202"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 8; i++) {
            segments[i] = new Segment("S"+i, i, 0, 480/3);
        }

        for (int i = 0; i < 8; i++) {
            segments[i+8] = new Segment("S"+(i+8), i, 0, 480/3);
        }

        t1.addSegments(Arrays.copyOfRange(segments, 0, 8));
        t2.addSegments(Arrays.copyOfRange(segments, 8, 16));

        network.addController(t1);
        network.addController(t2);
    }

    @Override
    public void setData(Network network, LinesObject ledObject) {
        for (int i = 0; i < 16; i++) {
            segments[i].setData(NetworkUtils.connect(
                    NetworkUtils.getRGB(ledObject.lines[i], 0, 240/3-1, 1.0),
                    NetworkUtils.getRGB(ledObject.lines[i], 240/3-1, 0, 1.0)));
        }
    }
}
