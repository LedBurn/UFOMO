
public class DandooAnimation extends Animation {

    private int numOfPoints = 16 * 2;
    private int tailLength = 30;
    private boolean addBackground = true;
    private final int[] points;

    public DandooAnimation(LEDObject ledObject, int numOfPoints, boolean addBackground) {
        this(ledObject, numOfPoints, addBackground, new Addon[]{});
    }

    public DandooAnimation(LEDObject ledObject, int numOfPoints, boolean addBackground, Addon[] addons) {
        super(ledObject,null, addons);
        this.numOfPoints = numOfPoints;
        this.addBackground = addBackground;
        tailLength = addBackground ? 3 : 7;

        points = new int[numOfPoints];
        for (int i = 0; i < numOfPoints; i++) {
            points[i] = i * ledObject.numOfPixels() / numOfPoints;
        }
    }

    @Override
    public void apply(double level, boolean newBeat) {
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            ledObject.setColor(i, HSBColor.BLACK);
        }

        for (int i = 0; i < numOfPoints; i++) {
            int point = points[i];
            if (i % 2 == 0) {
                point += level * 2 * ledObject.numOfPixels() / numOfPoints;
            } else {
                point -= level * 2 * ledObject.numOfPixels() / numOfPoints;
            }

            // tail
            for (int j = 0; j < tailLength; j++) {
                int tail = point;
                if (i % 2 == 0) {
                    tail -= j + 1;
                } else {
                    tail += j + 1;
                }
                double tailLevel = 1 - j * 1.0/tailLength;

                if (ledObject.getColor(fixPoint(tail)).brightness < tailLevel) {
                    ledObject.setColor(fixPoint(tail), new HSBColor(level, 1, tailLevel));
                }
            }

            // point
            ledObject.setColor(fixPoint(point), new HSBColor(level,1,1));
        }


        // bg color
        if (this.addBackground) {
            for (int i = 0; i < ledObject.numOfPixels(); i++) {
                HSBColor newColor = new HSBColor(HSBColor.mixHue(level, level + 0.5, ledObject.getColor(i).brightness), 1, 1);
                ledObject.setColor(i, newColor);
            }
        }

        for (Addon addon : addons) {
            addon.change(ledObject, level, newBeat);
        }
    }

    private int fixPoint(int point) {
        while (point < 0) point += ledObject.numOfPixels();
        while (point >= ledObject.numOfPixels()) point -= ledObject.numOfPixels();
        return point;
    }
}
