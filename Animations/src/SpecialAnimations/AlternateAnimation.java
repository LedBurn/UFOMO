import java.util.ArrayList;

public class AlternateAnimation extends Animation {

    private final ArrayList<Segment> segments = new ArrayList<>();

    /**
     * @param divider 1-infinity number of chunks
     * @param color1 color1
     * @param color2 color2
     *
     */
    public AlternateAnimation (IPixelsArray ledObject, int divider, HSBColor color1, HSBColor color2) {
        super(ledObject);
        double segmentSize = this.ledObject.numOfPixels() / (double) divider;
        double currSegmentNum = 0;
        Segment currSegment = new Segment();
        currSegment.startIndex = 0;
        currSegment.color = color1;
        segments.add(currSegment);

        for(int i = 1; i < this.ledObject.numOfPixels(); i++) {
            int segmentNum = (int) Math.round((i - i % segmentSize) / segmentSize);
            if (segmentNum != currSegmentNum) {
                currSegment.endIndex = i - 1;

                currSegmentNum = segmentNum;
                currSegment = new Segment();
                currSegment.startIndex = i;
                currSegment.color = segmentNum % 2 == 0 ? color1 : color2;
                segments.add(currSegment);
            }
        }
        currSegment.endIndex = this.ledObject.numOfPixels() - 1;
    }

    @Override
    public void animate(long cycleNum, double cycleTimePercent) {
        for (int i = 0; i < this.ledObject.numOfPixels(); i++) {
            double precisedPixelNum;
            if (cycleNum % 2 == 0) {
                 precisedPixelNum = i + cycleTimePercent * this.ledObject.numOfPixels();
            } else {
                precisedPixelNum = i + (1 - cycleTimePercent) * this.ledObject.numOfPixels();
            }
            while (precisedPixelNum > this.ledObject.numOfPixels() - 1) {
                precisedPixelNum -= this.ledObject.numOfPixels();
            }
            while (precisedPixelNum < 0) {
                precisedPixelNum += this.ledObject.numOfPixels();
            }

            HSBColor color = getColorForPrecisedPixelNum(precisedPixelNum);
            ledObject.setColor(i, color);
        }
    }

    private HSBColor getColorForPrecisedPixelNum(double precisedPixelNum) {
        Segment lowSegment = null;
        Segment highSegment = null;
        for (Segment segment : segments) {
            if (precisedPixelNum >= segment.startIndex && precisedPixelNum <= segment.endIndex) {
                return segment.color;
            }
            if (Math.floor(precisedPixelNum) == segment.endIndex) {
                lowSegment = segment;
            }
            if (Math.ceil(precisedPixelNum) == segment.startIndex) {
                highSegment = segment;
            }
            if (Math.ceil(precisedPixelNum) == this.ledObject.numOfPixels()) {
                highSegment = this.segments.get(0);
            }
        }
        if (lowSegment != null && highSegment != null) {
            return HSBColor.averageColors(lowSegment.color, highSegment.color, 1 - precisedPixelNum % 1);
        }
        return null;
    }

    private static class Segment {
        private int startIndex;
        private int endIndex;
        private HSBColor color;
    }
}
