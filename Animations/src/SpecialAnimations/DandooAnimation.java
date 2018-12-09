
public class DandooAnimation extends PixelsArrayAnimation {

    private int numOfPoints = 16 * 2;
    private int tailLength = 1;
    private boolean addBackground = true;
    private final double[] points;
    private boolean reversed = false;

    public DandooAnimation(IPixelsArray ledObject, int numOfPoints, boolean addBackground) {
        this(ledObject, numOfPoints, addBackground, new Addon[]{});
    }

    public DandooAnimation(IPixelsArray ledObject, int numOfPoints, boolean addBackground, Addon[] addons) {
        super(ledObject,null, addons);
        this.numOfPoints = numOfPoints;
        this.addBackground = addBackground;
        tailLength = addBackground ? 1 : 2;

        points = new double[numOfPoints];
        for (int i = 0; i < numOfPoints; i++) {
            points[i] = i * ledObject.numOfPixels() / numOfPoints;
        }
    }

    @Override
    public void apply(double level, boolean newBeat, boolean isOn, int[] eq) {
//        if (newBeat) reversed = !reversed;
        if (eq[7] / 128.0 > 0.8) {
//            System.out.println("new spike");
            reversed = !reversed;
        }

        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            ledObject.setColor(i, HSBColor.BLACK);
        }


        for (int i = 0; i < numOfPoints; i++) {
            // update point
            double point = points[i];
            if (reversed) {
                if (i % 2 == 0) {
                    point -=  0.75;
                } else {
                    point +=  0.75;
                }
            } else {
                if (i % 2 == 0) {
                    point +=  0.75;
                } else {
                    point -=  0.75;
                }
            }
            points[i] = point;

            // tail
            for (int j = 0; j < tailLength; j++) {
                int tailIndex1 = (int)Math.floor(point) + j + 1;
                int tailIndex2 = (int)Math.floor(point) - j - 1;
                double tailLevel = 1 - j * 1.0/tailLength;

                if (ledObject.getColor(fixPoint(tailIndex1)).brightness < tailLevel) {
                    ledObject.setColor(fixPoint(tailIndex1), new HSBColor(level, 1, tailLevel));
                }
                if (ledObject.getColor(fixPoint(tailIndex2)).brightness < tailLevel) {
                    ledObject.setColor(fixPoint(tailIndex2), new HSBColor(level, 1, tailLevel));
                }
            }

            // point
            ledObject.setColor(fixPoint((int)Math.floor(point)), new HSBColor(level,1,1));
        }


        // bg color
        if (this.addBackground) {
            for (int i = 0; i < ledObject.numOfPixels(); i++) {
                HSBColor newColor = new HSBColor(HSBColor.mixHue(level, level + 0.5, ledObject.getColor(i).brightness), 1, 1);
                ledObject.setColor(i, newColor);
            }
        }

        for (Addon addon : addons) {
            addon.change(ledObject, level, newBeat, isOn, eq);
        }
    }

    private int fixPoint(int point) {
        while (point < 0) point += ledObject.numOfPixels();
        while (point >= ledObject.numOfPixels()) point -= ledObject.numOfPixels();
        return point;
    }
}
