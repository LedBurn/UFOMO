import javax.swing.text.Segment;

public class Alternate2Animation extends Animation {

    private final int divider;
    private final HSBColor color1;
    private final HSBColor color2;

    /**
     * @param divider 1-infinity number of chunks
     * @param color1 color1
     * @param color2 color2
     *
     */
    public Alternate2Animation (IPixelsArray ledObject, int divider, HSBColor color1, HSBColor color2) {
        super(ledObject);
        this.divider = divider;
        this.color1 = color1;
        this.color2 = color2;
    }

    @Override
    public void animate(long cycleNum, double cycleTimePercent) {
        double segmentSize = this.ledObject.numOfPixels() / (double) this.divider;
        double pixelSize = 1.0 / segmentSize;
        for (int i = 0; i < this.ledObject.numOfPixels(); i++) {
            int segmentNum = (int) Math.round((i - i % segmentSize) / segmentSize);
            double startPositionInSegment = (i - segmentNum * segmentSize) / segmentSize;
            double endPositionInSegment = startPositionInSegment + pixelSize;

            HSBColor color1 = cycleNum % 2 == 0 ? this.color1 : this.color2;
            HSBColor color2 = cycleNum % 2 == 0 ? this.color2 : this.color1;

            if (endPositionInSegment < cycleTimePercent) {
                this.ledObject.setColor(i, color1);
            } else if (startPositionInSegment > cycleTimePercent) {
                this.ledObject.setColor(i, color2);
            } else {
                double ratio = (cycleTimePercent - startPositionInSegment) / pixelSize;
                this.ledObject.setColor(i, HSBColor.averageColors(color1, color2, 1 - ratio));
            }
        }
    }
}
