import java.net.InetAddress;
import java.net.UnknownHostException;

public class BabushkaNetworkDataSource implements INetworkDataSource<BabushkaObject> {
    private Controller teensy;

    private Segment[] segments = new Segment[BabushkaObject.SIZES.length];

    @Override
    public void configure(Network network) {
        try {
            teensy = new Controller("T4", InetAddress.getByName("10.0.0.204"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < BabushkaObject.SIZES.length; i++) {
            segments[i] = new Segment("S" + i, i, 0, BabushkaObject.SIZES[i]);
        }

        teensy.addSegments(segments);
        network.addController(teensy);
    }

    @Override
    public void setData(Network network, BabushkaObject ledObject) {
        for (int i = 0; i < segments.length; i++) {
            segments[i].setData(NetworkUtils.getRGB(ledObject.babushkas[i], 0, ledObject.babushkas[i].numOfPixels()-1, 1.0));
        }
    }
}
