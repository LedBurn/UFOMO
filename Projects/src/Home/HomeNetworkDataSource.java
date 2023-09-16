import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class HomeNetworkDataSource implements INetworkDataSource<HomeObject> {
    private Controller t1;
//    private Controller t2;

    private int num = 8;
    private Segment[] segments = new Segment[num*2];

    @Override
    public void configure(Network network) {
        try {
            t1 = new Controller("T1", InetAddress.getByName("10.0.0.203"));
//            t2 = new Controller("T2", InetAddress.getByName("10.0.0.203"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < num; i++) {
            segments[i*2] = new Segment("S"+i*2, i, 0, 300);
            segments[i*2+1] = new Segment("S"+i*2+1, i, 300, 300);
//            segments[i*2+1] = new Segment("S"+i*2+1, i, 150, 150);
        }
//        segments[0] = new Segment("test", 0, 0, 150);
//        segments[1] = new Segment("test", 1, 0, 150);
//        segments[2] = new Segment("test", 4, 0, 150);
//        segments[3] = new Segment("test", 5, 0, 150);
//
//        segments[4] = new Segment("test", 0, 149, 150);
//        segments[5] = new Segment("test", 1, 149, 150);
//        segments[6] = new Segment("test", 4, 149, 150);
//        segments[7] = new Segment("test", 5, 149, 150);

//        t1.addSegments(segments);
//        t1.addSegments(segments);

//        network.addController(t1);
//        network.addController(t2);
    }

    @Override
    public void setData(Network network, HomeObject ledObject) {
        for (int i = 0; i < num; i++) {
//            segments[i*2].setData(NetworkUtils.getRGB(ledObject.lines[i], 0, 299, 0.01));
//            segments[i*2+1].setData(NetworkUtils.getRGB(ledObject.lines[i], 0, 299, 0.01));
//            segments[i*2+1].setData(NetworkUtils.getRGB(ledObject.lines[i], 150, 299, 1.0));
        }
    }
}
