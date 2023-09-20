import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class HomeNetworkDataSource implements INetworkDataSource<HomeObject> {
    private Controller teensy;

    private Segment[] segments = new Segment[7];

    @Override
    public void configure(Network network) {
        try {
            teensy = new Controller("T1", InetAddress.getByName("10.0.0.203"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // data 0 - external 0 -> external 1
        // data 1 - external 3 R -> external 2 R
        // data 2 - external 4 R
        // data 3 - internal 3 -> internal 0
        // data 4 - internal 2 R -> internal 1 R
        // data 5 - ceiling 0 -> ceiling 1
        // data 6 - ceiling 3 R -> ceiling 2 R
        this.segments[0] = new Segment("S0", 0, 0, HomeObject.EXTERNAL_FRONT_NUM_OF_LEDS[0] + HomeObject.EXTERNAL_FRONT_NUM_OF_LEDS[1]);
        this.segments[1] = new Segment("S1", 1, 0, HomeObject.EXTERNAL_FRONT_NUM_OF_LEDS[3] + HomeObject.EXTERNAL_FRONT_NUM_OF_LEDS[2]);
        this.segments[2] = new Segment("S2", 2, 0, HomeObject.EXTERNAL_FRONT_NUM_OF_LEDS[4]);
        this.segments[3] = new Segment("S3", 3, 0, HomeObject.INTERNAL_FRONT_NUM_OF_LEDS[3] + HomeObject.INTERNAL_FRONT_NUM_OF_LEDS[0]);
        this.segments[4] = new Segment("S4", 4, 0, HomeObject.INTERNAL_FRONT_NUM_OF_LEDS[2] + HomeObject.INTERNAL_FRONT_NUM_OF_LEDS[1]);
        this.segments[5] = new Segment("S5", 5, 0, HomeObject.CEILING_NUM_OF_LEDS[0] + HomeObject.CEILING_NUM_OF_LEDS[1]);
        this.segments[6] = new Segment("S6", 6, 0, HomeObject.CEILING_NUM_OF_LEDS[3] + HomeObject.CEILING_NUM_OF_LEDS[2]);


        this.teensy.addSegments(this.segments);
        network.addController(this.teensy);
    }

    @Override
    public void setData(Network network, HomeObject homeObject) {
        segments[0].setData(NetworkUtils.connect(NetworkUtils.getRGB(homeObject.frontExternal[0]), NetworkUtils.getRGB(homeObject.frontExternal[1])));
        segments[1].setData(NetworkUtils.connect(NetworkUtils.getRGB(homeObject.frontExternal[3], true), NetworkUtils.getRGB(homeObject.frontExternal[2], true)));
        segments[2].setData(NetworkUtils.getRGB(homeObject.frontExternal[4], true));
        segments[3].setData(NetworkUtils.connect(NetworkUtils.getRGB(homeObject.frontInternal[3]), NetworkUtils.getRGB(homeObject.frontInternal[0])));
        segments[4].setData(NetworkUtils.connect(NetworkUtils.getRGB(homeObject.frontInternal[2], true), NetworkUtils.getRGB(homeObject.frontInternal[1], true)));
        segments[5].setData(NetworkUtils.connect(NetworkUtils.getRGB(homeObject.ceiling[0]), NetworkUtils.getRGB(homeObject.ceiling[1])));
        segments[6].setData(NetworkUtils.connect(NetworkUtils.getRGB(homeObject.ceiling[3], true), NetworkUtils.getRGB(homeObject.ceiling[2], true)));
    }
}
