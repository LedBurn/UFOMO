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
    private Segment d1 = new Segment("D1", 0, 0, 408/3);
    private Segment d2 = new Segment("D2", 1, 0, 480/3);
    private Segment d3 = new Segment("D3", 2, 0, 408/3);
    private Segment d4 = new Segment("D4", 3, 0, 480/3);
    private Segment d5 = new Segment("D5", 4, 0, 480/3);
    private Segment d6 = new Segment("D6", 5, 0, 408/3);
    private Segment d7 = new Segment("D7", 6, 0, 480/3);

    // T4
    private Segment d8 = new Segment("D8", 0, 0, 480/3);
    private Segment d9 = new Segment("D9", 1, 0, 408/3);
    private Segment d10 = new Segment("D10", 2, 0, 480/3);
    private Segment d11 = new Segment("D11", 3, 0, 480/3);
    private Segment d12 = new Segment("D12", 4, 0, 408/3);
    private Segment d13 = new Segment("D13", 5, 0, 480/3);

    // T1
    private Segment d14 = new Segment("D14", 0, 0, 480/3); // L1
    private Segment d15 = new Segment("D15", 1, 0, 480/3); // L2
    private Segment d16 = new Segment("D16", 2, 0, 420/3); // L5
    private Segment d17 = new Segment("D17", 3, 0, 420/3); // L6

    // T2
    private Segment d18 = new Segment("D18", 4, 0, 420/3); // L8
    private Segment d19 = new Segment("D19", 3, 0, 420/3); // L9
    private Segment d20 = new Segment("D20", 2, 0, 420/3); // L7
    private Segment d21 = new Segment("D21", 0, 0, 480/3); // L3
    private Segment d22 = new Segment("D22", 1, 0, 480/3); // L4

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
        t1.addSegments(new Segment[]{ d14, d15, d16, d17});

        // ------------------------ T2 ------------------------
        // | 0: D21        1: D22        2: D20        3: D19 |
        // | 4: D18        5:            6:            7:     |
        // ----------------------------------------------------
        network.addController(t2);
        t2.addSegments(new Segment[]{ d21, d22, d20, d19, d18});

        // ------------------------ T3 ------------------------
        // | 0: D1         1: D2         2: D3         3: D4  |
        // | 4: D5         5: D6         6: D7         7:     |
        // ----------------------------------------------------
        network.addController(t3);
        t3.addSegments(new Segment[]{ d1, d2, d3, d4, d5, d6, d7 });

        // ------------------------ T4 ------------------------
        // | 0: D8         1: D9         2: D11        3: D11 |
        // | 4: D12        5: D13        6:            7:     |
        // ----------------------------------------------------
        network.addController(t4);
        t4.addSegments(new Segment[]{ d8, d9, d10, d11, d12, d13});
    }


    private long lastSend = 0;
    public void send(UFOMOObject ufomoObject, double brightnessLevel) {
//        System.out.println("brightness - " + brightnessLevel);
        //System.out.println("sending - " + (System.currentTimeMillis() - lastSend));
        lastSend = System.currentTimeMillis();
        d1.setData(getDataPoint(ufomoObject,"D1", brightnessLevel));
        d2.setData(getDataPoint(ufomoObject,"D2", brightnessLevel));
        d3.setData(getDataPoint(ufomoObject,"D3", brightnessLevel));
        d4.setData(getDataPoint(ufomoObject,"D4", brightnessLevel));
        d5.setData(getDataPoint(ufomoObject,"D5", brightnessLevel));
        d6.setData(getDataPoint(ufomoObject,"D6", brightnessLevel));
        d7.setData(getDataPoint(ufomoObject,"D7", brightnessLevel));
        d8.setData(getDataPoint(ufomoObject,"D8", brightnessLevel));
        d9.setData(getDataPoint(ufomoObject,"D9", brightnessLevel));
        d10.setData(getDataPoint(ufomoObject,"D10", brightnessLevel));
        d11.setData(getDataPoint(ufomoObject,"D11", brightnessLevel));
        d12.setData(getDataPoint(ufomoObject,"D12", brightnessLevel));
        d13.setData(getDataPoint(ufomoObject,"D13", brightnessLevel));
        d14.setData(getDataPoint(ufomoObject,"D14", brightnessLevel));
        d15.setData(getDataPoint(ufomoObject,"D15", brightnessLevel));
        d16.setData(getDataPoint(ufomoObject,"D16", brightnessLevel));
        d17.setData(getDataPoint(ufomoObject,"D17", brightnessLevel));
        d18.setData(getDataPoint(ufomoObject,"D18", brightnessLevel));
        d19.setData(getDataPoint(ufomoObject,"D19", brightnessLevel));
        d20.setData(getDataPoint(ufomoObject,"D20", brightnessLevel));
        d21.setData(getDataPoint(ufomoObject,"D21", brightnessLevel));
        d22.setData(getDataPoint(ufomoObject,"D22", brightnessLevel));

        network.send();
    }

    private RGBColor[] getDataPoint(UFOMOObject ufomoObject, String name, double brightnessLevel) {
        switch (name) {
            case "D1":
                return joinArray(
                        getRGB(ufomoObject.octagon[0], 0, ufomoObject.octagon[0].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.octagon[1], 0, ufomoObject.octagon[1].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.octagon[2], 0, ufomoObject.octagon[2].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.octagon[3], 0, ufomoObject.octagon[3].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.octagon[4], 0, ufomoObject.octagon[4].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.octagon[5], 0, ufomoObject.octagon[5].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.octagon[6], 0, ufomoObject.octagon[6].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.octagon[7], 0, ufomoObject.octagon[7].numOfPixels()-1, brightnessLevel)
                );

            case "D2":
                return joinArray(
                        getRGB(ufomoObject.lines[15], 0, ufomoObject.lines[15].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.lines[14], ufomoObject.lines[14].numOfPixels()-1, 0, brightnessLevel)
                );

            case "D3":
                return joinArray(
                        getRGB(ufomoObject.beam[7], 0, ufomoObject.beam[7].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.beam[6], ufomoObject.beam[6].numOfPixels()-1, 0, brightnessLevel)
                );

            case "D4":
                return joinArray(
                        getRGB(ufomoObject.lines[13], 0, ufomoObject.lines[13].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.lines[12], ufomoObject.lines[12].numOfPixels()-1, 0, brightnessLevel)
                );

            case "D5":
                return joinArray(
                        getRGB(ufomoObject.lines[11], 0, ufomoObject.lines[11].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.lines[10], ufomoObject.lines[10].numOfPixels()-1, 0, brightnessLevel)
                );

            case "D6":
                return joinArray(
                        getRGB(ufomoObject.beam[5], 0, ufomoObject.beam[5].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.beam[4], ufomoObject.beam[4].numOfPixels()-1, 0, brightnessLevel)
                );

            case "D7":
                return joinArray(
                        getRGB(ufomoObject.lines[9], 0, ufomoObject.lines[9].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.lines[8], ufomoObject.lines[8].numOfPixels()-1, 0, brightnessLevel)
                );

            case "D8":
                return joinArray(
                        getRGB(ufomoObject.lines[7], 0, ufomoObject.lines[7].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.lines[6], ufomoObject.lines[6].numOfPixels()-1, 0, brightnessLevel)
                );

            case "D9":
                return joinArray(
                        getRGB(ufomoObject.beam[3], 0, ufomoObject.beam[3].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.beam[2], ufomoObject.beam[2].numOfPixels()-1, 0, brightnessLevel)
                );

            case "D10":
                return joinArray(
                        getRGB(ufomoObject.lines[5], 0, ufomoObject.lines[5].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.lines[4], ufomoObject.lines[4].numOfPixels()-1, 0, brightnessLevel)
                );

            case "D11":
                return joinArray(
                        getRGB(ufomoObject.lines[3], 0, ufomoObject.lines[3].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.lines[2], ufomoObject.lines[2].numOfPixels()-1, 0, brightnessLevel)
                );

            case "D12":
                return joinArray(
                        getRGB(ufomoObject.beam[1], 0, ufomoObject.beam[1].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.beam[0], ufomoObject.beam[0].numOfPixels()-1, 0, brightnessLevel)
                );

            case "D13":
                return joinArray(
                        getRGB(ufomoObject.lines[1], 0, ufomoObject.lines[1].numOfPixels()-1, brightnessLevel),
                        getRGB(ufomoObject.lines[0], ufomoObject.lines[0].numOfPixels()-1, 0, brightnessLevel)
                );

            case "D14":
                return getRGB(ufomoObject.bigCircle, 0, ufomoObject.bigCircle.numOfPixels()/4-1, brightnessLevel);

            case "D15":
                return getRGB(ufomoObject.bigCircle, ufomoObject.bigCircle.numOfPixels()-1, (ufomoObject.bigCircle.numOfPixels()*3)/4, brightnessLevel);

            case "D16":
                RGBColor[] colors1 = getRGB(ufomoObject.mediumCircle, (ufomoObject.mediumCircle.numOfPixels()*11)/12, ufomoObject.mediumCircle.numOfPixels()-1, brightnessLevel);
                RGBColor[] colors2 = getRGB(ufomoObject.mediumCircle, 0, (ufomoObject.mediumCircle.numOfPixels()*3)/12-1, brightnessLevel);
                return connect(colors1, colors2);

            case "D17":
                return getRGB(ufomoObject.mediumCircle, (ufomoObject.mediumCircle.numOfPixels()*11)/12-1, (ufomoObject.mediumCircle.numOfPixels()*7)/12, brightnessLevel);

            case "D18":
                return getRGB(ufomoObject.smallCircle, ufomoObject.smallCircle.numOfPixels()/2-1, 0, brightnessLevel);

            case "D19":
                return getRGB(ufomoObject.smallCircle, ufomoObject.smallCircle.numOfPixels()/2, ufomoObject.smallCircle.numOfPixels()-1, brightnessLevel);

            case "D20":
                return getRGB(ufomoObject.mediumCircle, (ufomoObject.mediumCircle.numOfPixels()*7)/12-1, (ufomoObject.mediumCircle.numOfPixels()*3)/12, brightnessLevel);


            case "D21":
                return getRGB(ufomoObject.bigCircle, (ufomoObject.bigCircle.numOfPixels()*2)/4-1, ufomoObject.bigCircle.numOfPixels()/4, brightnessLevel);

            case "D22":
                return getRGB(ufomoObject.bigCircle, (ufomoObject.bigCircle.numOfPixels()*2)/4, (ufomoObject.bigCircle.numOfPixels()*3)/4-1, brightnessLevel);

        }
        return new RGBColor[0];
    }

    // returns rgb colors from the object. toIndex can be smaller than fromIndex so it will be a reversed array.
    private RGBColor[] getRGB(LEDObject object, int fromIndex, int toIndex, double brightnessLevel) {
        if (fromIndex < toIndex) {
            RGBColor[] colors = new RGBColor[toIndex-fromIndex+1];
            for (int i = 0; i < colors.length; i++) {
                colors[i] = object.getColorRGB(fromIndex + i, brightnessLevel);
            }
            return colors;
        } else {
            RGBColor[] colors = new RGBColor[fromIndex-toIndex+1];
            for (int i = 0; i < colors.length; i++) {
                colors[i] = object.getColorRGB(fromIndex - i, brightnessLevel);
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
