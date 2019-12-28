import java.net.InetAddress;
import java.net.UnknownHostException;

public class SignNetworkDataSource implements INetworkDataSource<SignLEDObject> {

    private Controller t1;

    private Segment fingers_1_2_3;
    private Segment fingers_4_5;
    private Segment palm;
    private Segment led_l_e;
    private Segment led_d;
    private Segment burn;
    private Segment camp;

    @Override
    public void configure(Network network) {
        try {
            t1 = new Controller("T1", InetAddress.getByName("10.0.0.201"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        int size_fingers_1_2_3 = SignLEDObject.SIZE_FINGER1 + SignLEDObject.SIZE_FINGER2 + SignLEDObject.SIZE_FINGER3;
        fingers_1_2_3 = new Segment("fingers_1_2_3", 7, 0, size_fingers_1_2_3);

        int size_fingers_4_5 = SignLEDObject.SIZE_FINGER4 + SignLEDObject.SIZE_FINGER5;
        fingers_4_5 = new Segment("fingers_4_5", 3, 0, size_fingers_4_5);

        int size_palm = SignLEDObject.SIZE_PALM6 + SignLEDObject.SIZE_PALM7 + SignLEDObject.SIZE_PALM8 + SignLEDObject.SIZE_PALM9 + SignLEDObject.SIZE_PALM10 + SignLEDObject.SIZE_PALM11;
        palm = new Segment("palm", 2, 0, size_palm);

        int size_led_l_e = SignLEDObject.SIZE_L + SignLEDObject.SIZE_E;
        led_l_e = new Segment("led_l_e", 4, 0, size_led_l_e);

        int size_led_d = SignLEDObject.SIZE_D;
        led_d = new Segment("led_d", 6, 0, size_led_d);

        int size_burn = SignLEDObject.SIZE_B + SignLEDObject.SIZE_U + SignLEDObject.SIZE_R + SignLEDObject.SIZE_N;
        burn =  new Segment("burn", 0, 0, size_burn);

        int size_camp = SignLEDObject.SIZE_C + SignLEDObject.SIZE_A + SignLEDObject.SIZE_M + SignLEDObject.SIZE_P;
        camp =  new Segment("camp", 1, 0, size_camp);


        Segment[] segments = new Segment[]{ fingers_1_2_3, fingers_4_5, palm, led_l_e, led_d, burn, camp };
        t1.addSegments(segments);

        network.addController(t1);
    }

    @Override
    public void setData(Network network, SignLEDObject sign) {
        double brightness = 1.0;

        fingers_1_2_3.setData(NetworkUtils.joinArray(
                NetworkUtils.getRGBCyclic(sign.finger1, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.finger2, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.finger3, 0, false, brightness)));

        fingers_4_5.setData(NetworkUtils.joinArray(
                NetworkUtils.getRGBCyclic(sign.finger4, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.finger5, 0, false, brightness)));

        palm.setData(NetworkUtils.joinArray(
                NetworkUtils.getRGBCyclic(sign.palm11, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.palm10, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.palm9, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.palm8, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.palm7, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.palm6, 0, false, brightness)));

        led_l_e.setData(NetworkUtils.joinArray(
                NetworkUtils.getRGBCyclic(sign.led_e, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.led_l, 0, false, brightness)));

        led_d.setData(NetworkUtils.joinArray(
                NetworkUtils.getRGBCyclic(sign.led_d, 0, false, brightness)));

        burn.setData(NetworkUtils.joinArray(
                NetworkUtils.getRGBCyclic(sign.burn_n, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.burn_r, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.burn_u, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.burn_b, 0, false, brightness)));

        camp.setData(NetworkUtils.joinArray(
                NetworkUtils.getRGBCyclic(sign.camp_p, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.camp_m, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.camp_a, 0, false, brightness),
                NetworkUtils.getRGBCyclic(sign.camp_c, 0, false, brightness)));
    }
}
