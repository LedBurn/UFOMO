import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UFOMONetwork {

    private Network network;

    private Controller t1;
    private Controller t2;
    private Controller t3;
    private Controller t4;

    // T3
    private Segment d1_1 = new Segment("D1_1", 0, 0, 208);
    private Segment d1_2 = new Segment("D1_2", 0, 208, 208);
    private Segment d2_1 = new Segment("D2_1", 1, 0, 240);
    private Segment d2_2 = new Segment("D2_2", 1, 240, 240);
    private Segment d3_1 = new Segment("D3_1", 2, 0, 204);
    private Segment d3_2 = new Segment("D3_2", 2, 204, 204);
    private Segment d4_1 = new Segment("D4_1", 3, 0, 240);
    private Segment d4_2 = new Segment("D4_2", 3, 240, 240);
    private Segment d5_1 = new Segment("D5_1", 4, 0, 240);
    private Segment d5_2 = new Segment("D5_2", 4, 240, 240);
    private Segment d6_1 = new Segment("D6_1", 5, 0, 204);
    private Segment d6_2 = new Segment("D6_2", 5, 204, 204);
    private Segment d7_1 = new Segment("D7_1", 6, 0, 240);
    private Segment d7_2 = new Segment("D7_2", 6, 240, 240);

    // T4
    private Segment d8_1 = new Segment("D8_1", 0, 0, 240);
    private Segment d8_2 = new Segment("D8_2", 0, 240, 240);
    private Segment d9_1 = new Segment("D9_1", 1, 0, 204);
    private Segment d9_2 = new Segment("D9_2", 1, 204, 204);
    private Segment d10_1 = new Segment("D10_1", 2, 0, 240);
    private Segment d10_2 = new Segment("D10_2", 2, 240, 240);
    private Segment d11_1 = new Segment("D11_1", 3, 0, 240);
    private Segment d11_2 = new Segment("D11_2", 3, 240, 240);
    private Segment d12_1 = new Segment("D12_1", 4, 0, 204);
    private Segment d12_2 = new Segment("D12_2", 4, 204, 204);
    private Segment d13_1 = new Segment("D13_1", 5, 0, 240);
    private Segment d13_2 = new Segment("D13_2", 5, 240, 240);

    // T1
    private Segment d14_1 = new Segment("D14_1", 0, 0, 240);
    private Segment d14_2 = new Segment("D14_2", 0, 240, 240);
    private Segment d15_1 = new Segment("D15_1", 1, 0, 240);
    private Segment d15_2 = new Segment("D15_2", 1, 240, 240);
    private Segment d16_1 = new Segment("D16_1", 2, 0, 210);
    private Segment d16_2 = new Segment("D16_2", 2, 210, 210);
    private Segment d17_1 = new Segment("D17_1", 3, 0, 210);
    private Segment d17_2 = new Segment("D17_2", 3, 210, 210);

    // T2
    private Segment d18_1 = new Segment("D18_1", 4, 0, 210);
    private Segment d18_2 = new Segment("D18_2", 4, 210, 210);
    private Segment d19_1 = new Segment("D19_1", 3, 0, 210);
    private Segment d19_2 = new Segment("D19_2", 3, 210, 210);
    private Segment d20_1 = new Segment("D20_1", 2, 0, 210);
    private Segment d20_2 = new Segment("D20_2", 2, 210, 210);
    private Segment d21_1 = new Segment("D21_1", 0, 0, 240);
    private Segment d21_2 = new Segment("D21_2", 0, 240, 240);
    private Segment d22_1 = new Segment("D22_1", 1, 0, 240);
    private Segment d22_2 = new Segment("D22_2", 1, 240, 240);

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
        t2.addSegments(new Segment[]{ d21_1, d21_2, d22_1, d22_2, d20_1, d20_2, d19_1, d19_2, d18_1, d18_2});

        // ------------------------ T3 ------------------------
        // | 0: D1         1: D2         2: D3         3: D4  |
        // | 4: D5         5: D6         6: D7         7:     |
        // ----------------------------------------------------
        network.addController(t3);
        t3.addSegments(new Segment[]{ d1_1, d1_2, d2_1, d2_2, d3_1, d3_2, d4_1, d4_2, d5_1, d5_2, d6_1, d6_2, d7_1, d7_2});

        // ------------------------ T4 ------------------------
        // | 0: D8         1: D9         2: D11        3: D11 |
        // | 4: D12        5: D13        6:            7:     |
        // ----------------------------------------------------
        network.addController(t4);
        t4.addSegments(new Segment[]{ d8_1, d8_2, d9_1, d9_2, d10_1, d10_2, d11_1, d11_2, d12_1, d12_2, d13_1, d13_2});
    }


    public void send(UFOMOObject ufomoObject) {
        d1_1.setData(getDataPoint(ufomoObject,"D1", 1));
        d1_2.setData(getDataPoint(ufomoObject,"D1", 2));
        d2_1.setData(getDataPoint(ufomoObject,"D2", 1));
        d2_2.setData(getDataPoint(ufomoObject,"D2", 2));
        d3_1.setData(getDataPoint(ufomoObject,"D3", 1));
        d3_2.setData(getDataPoint(ufomoObject,"D3", 2));
        d4_1.setData(getDataPoint(ufomoObject,"D4", 1));
        d4_2.setData(getDataPoint(ufomoObject,"D4", 2));
        d5_1.setData(getDataPoint(ufomoObject,"D5", 1));
        d5_2.setData(getDataPoint(ufomoObject,"D5", 2));
        d6_1.setData(getDataPoint(ufomoObject,"D6", 1));
        d6_2.setData(getDataPoint(ufomoObject,"D6", 2));
        d7_1.setData(getDataPoint(ufomoObject,"D7", 1));
        d7_2.setData(getDataPoint(ufomoObject,"D7", 2));
        d8_1.setData(getDataPoint(ufomoObject,"D8", 1));
        d8_2.setData(getDataPoint(ufomoObject,"D8", 2));
        d9_1.setData(getDataPoint(ufomoObject,"D9", 1));
        d9_2.setData(getDataPoint(ufomoObject,"D9", 2));
        d10_1.setData(getDataPoint(ufomoObject,"D10", 1));
        d10_2.setData(getDataPoint(ufomoObject,"D10", 2));
        d11_1.setData(getDataPoint(ufomoObject,"D11", 1));
        d11_2.setData(getDataPoint(ufomoObject,"D11", 2));
        d12_1.setData(getDataPoint(ufomoObject,"D12", 1));
        d12_2.setData(getDataPoint(ufomoObject,"D12", 2));
        d13_1.setData(getDataPoint(ufomoObject,"D13", 1));
        d13_2.setData(getDataPoint(ufomoObject,"D13", 2));
        d14_1.setData(getDataPoint(ufomoObject,"D14", 1));
        d14_2.setData(getDataPoint(ufomoObject,"D14", 2));
        d15_1.setData(getDataPoint(ufomoObject,"D15", 1));
        d15_2.setData(getDataPoint(ufomoObject,"D15", 2));
        d16_1.setData(getDataPoint(ufomoObject,"D16", 1));
        d16_2.setData(getDataPoint(ufomoObject,"D16", 2));
        d17_1.setData(getDataPoint(ufomoObject,"D17", 1));
        d17_2.setData(getDataPoint(ufomoObject,"D17", 2));
        d18_1.setData(getDataPoint(ufomoObject,"D18", 1));
        d18_2.setData(getDataPoint(ufomoObject,"D18", 2));
        d19_1.setData(getDataPoint(ufomoObject,"D19", 1));
        d19_2.setData(getDataPoint(ufomoObject,"D19", 2));
        d20_1.setData(getDataPoint(ufomoObject,"D20", 1));
        d20_2.setData(getDataPoint(ufomoObject,"D20", 2));
        d21_1.setData(getDataPoint(ufomoObject,"D21", 1));
        d21_2.setData(getDataPoint(ufomoObject,"D21", 2));
        d22_1.setData(getDataPoint(ufomoObject,"D22", 1));
        d22_2.setData(getDataPoint(ufomoObject,"D22", 2));

        network.send();
    }



    private RGBColor[] getDataPoint(UFOMOObject ufomoObject, String name, int index) {
        switch (name) {
            case "D1":
                switch (index) {
                    case 1:
                        return joinArray(getRGB(ufomoObject.octagon[0], 0, 51),
                                getRGB(ufomoObject.octagon[1], 0, 51),
                                getRGB(ufomoObject.octagon[2], 0, 51),
                                getRGB(ufomoObject.octagon[3], 0, 51));
                    case 2:
                        return joinArray(getRGB(ufomoObject.octagon[4], 0, 51),
                                getRGB(ufomoObject.octagon[5], 0, 51),
                                getRGB(ufomoObject.octagon[6], 0, 51),
                                getRGB(ufomoObject.octagon[7], 0, 51));
                }
                break;

            case "D2":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.lines[0], 0, 240 - 1);
                    case 2:
                        return getRGB(ufomoObject.lines[1], 240 - 1, 0);
                }
                break;

            case "D3":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.beam[0], 0, 204 - 1);
                    case 2:
                        return getRGB(ufomoObject.beam[1], 204 - 1, 0);
                }
                break;

            case "D4":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.lines[2], 0, 240 - 1);
                    case 2:
                        return getRGB(ufomoObject.lines[3], 240 - 1, 0);
                }
                break;

            case "D5":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.lines[4], 0, 240 - 1);
                    case 2:
                        return getRGB(ufomoObject.lines[5], 240 - 1, 0);
                }
                break;

            case "D6":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.beam[2], 0, 204 - 1);
                    case 2:
                        return getRGB(ufomoObject.beam[3], 204 - 1, 0);
                }
                break;

            case "D7":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.lines[6], 0, 240 - 1);
                    case 2:
                        return getRGB(ufomoObject.lines[7], 240 - 1, 0);
                }
                break;

            case "D8":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.lines[8], 0, 240 - 1);
                    case 2:
                        return getRGB(ufomoObject.lines[9], 240 - 1, 0);
                }
                break;

            case "D9":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.beam[4], 0, 204 - 1);
                    case 2:
                        return getRGB(ufomoObject.beam[5], 204 - 1, 0);
                }
                break;

            case "D10":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.lines[10], 0, 240 - 1);
                    case 2:
                        return getRGB(ufomoObject.lines[11], 240 - 1, 0);
                }
                break;

            case "D11":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.lines[12], 0, 240 - 1);
                    case 2:
                        return getRGB(ufomoObject.lines[13], 240 - 1, 0);
                }
                break;

            case "D12":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.beam[6], 0, 204 - 1);
                    case 2:
                        return getRGB(ufomoObject.beam[7], 204 - 1, 0);
                }
                break;

            case "D13":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.lines[14], 0, 240 - 1);
                    case 2:
                        return getRGB(ufomoObject.lines[15], 240 - 1, 0);
                }
                break;

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
                        return getRGB(ufomoObject.smallCircle, 210 - 1, 0);
                    case 2:
                        return getRGB(ufomoObject.smallCircle, 210 * 2 - 1, 210);
                }
                break;

            case "D19":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.smallCircle, 210 * 2, 210 * 3 - 1);
                    case 2:
                        return getRGB(ufomoObject.smallCircle, 210 * 3, 210 * 4 - 1);
                }
                break;

            case "D20":
                switch (index) {
                    case 1:
                        return getRGB(ufomoObject.mediumCircle, 105 * 6 - 1, 105 * 4);
                    case 2:
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

    private RGBColor[] joinArray(RGBColor[]... arrays) {
        int length = 0;
        for (RGBColor[] array : arrays) {
            length += array.length;
        }

        final RGBColor[] result = new RGBColor[length];

        int offset = 0;
        for (RGBColor[] array : arrays) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }

        return result;
    }
}
