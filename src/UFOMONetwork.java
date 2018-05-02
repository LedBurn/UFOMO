import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UFOMONetwork {

    private Network network;

    private Controller t1;
    private Controller t2;
    private Controller t3;
    private Controller t4;

    private Segment d14_1 = new Segment("D14_1", 0, 0, 240);
    private Segment d14_2 = new Segment("D14_1", 0, 240, 240);
    private Segment d15_1 = new Segment("D15_1", 1, 0, 240);
    private Segment d15_2 = new Segment("D15_1", 1, 240, 240);
    private Segment d16_1 = new Segment("D16_1", 2, 0, 210);
    private Segment d16_2 = new Segment("D16_1", 2, 210, 210);
    private Segment d17_1 = new Segment("D17_1", 3, 0, 210);
    private Segment d17_2 = new Segment("D17_1", 3, 210, 210);

    public UFOMONetwork() {

        try {
            network = new Network();
            t1 = new Controller("T1", InetAddress.getByName("10.0.0.201"));
            t2 = new Controller("T2", InetAddress.getByName("10.0.0.202"));
            t3 = new Controller("T3", InetAddress.getByName("10.0.0.203"));
            t4 = new Controller("T4", InetAddress.getByName("10.0.0.204"));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }



        // ------------------------ T1 ------------------------
        // | 0: D14        1: D15        2: D16        3: D17 |
        // | 4:            5:            6:            7:     |
        // ----------------------------------------------------
        network.addController(t1);
        t1.addSegments(new Segment[]{ d14_1, d14_2, d15_1, d15_2, d16_1, d16_2, d17_1, d17_2});

        // ------------------------ T2 ------------------------
        // | 0: D21        1: D22        2: D20        3: D19 |
        // | 4: D18        5:            6:            7:     |
        // ----------------------------------------------------
        network.addController(t2);
//        t2.addSegments(new Segment[]{ d14_1, d14_2, d15_1, d15_2, d16_1, d16_2, d17_1, d17_2});
    }


    public void send(UFOMOObject ufomoObject) {
        d14_1.setData(getDataPoint(ufomoObject,"D14", 1));
        d14_2.setData(getDataPoint(ufomoObject,"D14", 2));
        d15_1.setData(getDataPoint(ufomoObject,"D15", 1));
        d15_2.setData(getDataPoint(ufomoObject,"D15", 2));
        d16_1.setData(getDataPoint(ufomoObject,"D16", 1));
        d16_2.setData(getDataPoint(ufomoObject,"D16", 2));
        d17_1.setData(getDataPoint(ufomoObject,"D17", 1));
        d17_2.setData(getDataPoint(ufomoObject,"D17", 2));

        network.send();
    }



    private RGBColor[] getDataPoint(UFOMOObject ufomoObject, String name, int index) {
        switch (name) {
            case "D14":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.bigCircle, 0, 240 - 1);
                    case 2:
                        return getRGB(ufomoObject.bigCircle, 240, 240 * 2 -1);
                }
                break;

            case "D15":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.bigCircle, 240 * 8 - 1, 240 * 7);
                    case 2:
                        return getRGB(ufomoObject.bigCircle, 240 * 7 - 1, 240 * 6);
                }
                break;

            case "D16":
                switch (index) {
                    case 1:
                        RGBColor[] colors1 = getRGB(ufomoObject.mediumCircle, 105 * 11, 105 * 12 - 1);
                        RGBColor[] colors2 = getRGB(ufomoObject.mediumCircle, 0, 105 -1);
                        return connect(colors1, colors2);
                    case 2:
                        return getRGB(ufomoObject.mediumCircle, 105, 105 * 3 - 1);
                }
                break;

            case "D17":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.mediumCircle, 105 * 10 - 1, 105 * 8);
                    case 2:
                        return getRGB(ufomoObject.mediumCircle, 105 * 8 - 1, 105 * 6);
                }
                break;

            case "D18":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.smallCircle, 240 - 1, 0);
                    case 2:
                        return getRGB(ufomoObject.smallCircle, 240 * 2 - 1, 240);
                }
                break;

            case "D19":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.smallCircle, 240 * 2, 240 * 3 - 1);
                    case 2:
                        return getRGB(ufomoObject.smallCircle, 240 * 3, 240 * 4 - 1);
                }
                break;

            case "D20":
                switch (index) {
                    case 0:
                        return getRGB(ufomoObject.mediumCircle, 105 * 6 - 1, 105 * 4);
                    case 1:
                        return getRGB(ufomoObject.mediumCircle, 105 * 4 - 1, 105 * 2);
                }
                break;

            case "D21":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.bigCircle, 240 * 4 - 1, 240 * 3);
                    case 2:
                        return getRGB(ufomoObject.bigCircle, 240 * 3 - 1, 240 * 2);
                }
                break;

            case "D22":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.bigCircle, 240 * 4, 240 * 5 -1);
                    case 2:
                        return getRGB(ufomoObject.bigCircle, 240 * 5, 240 * 6 - 1);
                }
                break;

        }
        return new RGBColor[0];
    }

    // returns rgb colors from the object. toIndex can be smaller than fromIndex so it will be a reversed array.
    private RGBColor[] getRGB(LEDObject object, int fromIndex, int toIndex) {
        if (fromIndex < toIndex) {
            RGBColor[] colors = new RGBColor[toIndex-fromIndex+1];
            for (int i = 0; i < colors.length; i++) {
                colors[i] = object.getColorRGB(fromIndex + i);
            }
            return colors;
        } else {
            RGBColor[] colors = new RGBColor[fromIndex-toIndex+1];
            for (int i = 0; i < colors.length; i++) {
                colors[i] = object.getColorRGB(toIndex + i);
            }
            return colors;
        }
    }

    private RGBColor[] connect(RGBColor[] colors1, RGBColor[] colors2) {
        RGBColor[] colors = new RGBColor[colors1.length + colors2.length];
        for (int i = 0; i < colors.length; i++) {
            if (i<colors1.length) {
                colors[i] = colors1[i];
            } else {
                colors[i] = colors2[i - colors1.length];
            }
        }
        return colors;
    }
}
