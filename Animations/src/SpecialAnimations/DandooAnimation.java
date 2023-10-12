import java.util.ArrayList;
import java.util.Arrays;

public class DandooAnimation extends Animation {
    private final Chunk[] chunks;

    public DandooAnimation(IPixelsArray ledObject, int numOfChunks, double chunkSize) {
        super(ledObject);
        this.chunks = new Chunk[numOfChunks];
        double maxChunkSize = this.ledObject.numOfPixels() / ((double) numOfChunks / 2);
        double chunkSizeScaled = maxChunkSize * chunkSize;
        for (int i = 0; i < numOfChunks / 2; i++) {
            double startPoint = i * this.ledObject.numOfPixels() / ((double) numOfChunks / 2);
            chunks[i * 2] = new Chunk(startPoint + chunkSizeScaled, startPoint, true);
            chunks[i * 2 + 1] = new Chunk(startPoint - chunkSizeScaled, startPoint, false);
        }
    }

    @Override
    public void animate(long cycleNum, double cycleTimePercent) {
        this.fixChunksForTimePercent(cycleTimePercent);

        for (int i = 0; i < this.ledObject.numOfPixels(); i++) {
            double[] positionInChunks = this.getPointPositionInChunks(i);
            if (positionInChunks.length == 0) continue;
            double[] brightnessInChunks = this.getBrightnessForPositions(positionInChunks);
            double maxBrightness = Arrays.stream(brightnessInChunks).max().getAsDouble();
            ledObject.setColor(i, new LEDColor(cycleTimePercent, 1, maxBrightness));
        }
    }

    private void fixChunksForTimePercent(double timePercent) {
        for (Chunk chunk : this.chunks) {
            double diff = timePercent * this.ledObject.numOfPixels();
            if (chunk.isForward) {
                chunk.startPosition = chunk.originalStartPosition + diff;
                chunk.endPosition = chunk.originalEndPosition + diff;
            } else {
                chunk.startPosition = chunk.originalStartPosition - diff;
                chunk.endPosition = chunk.originalEndPosition - diff;
            }

            while (chunk.startPosition < 0) chunk.startPosition += this.ledObject.numOfPixels();
            while (chunk.endPosition < 0) chunk.endPosition += this.ledObject.numOfPixels();
            while (chunk.startPosition >= this.ledObject.numOfPixels()) chunk.startPosition -= this.ledObject.numOfPixels();
            while (chunk.endPosition >= this.ledObject.numOfPixels()) chunk.endPosition -= this.ledObject.numOfPixels();
        }
    }

    private double[] getPointPositionInChunks(int point) {
        ArrayList<Double> pointChunks = new ArrayList<>();

        for (Chunk chunk : this.chunks) {
            double chunkStart = chunk.startPosition;
            double chunkEnd = chunk.endPosition;
            if (chunk.isForward) {
                if (chunkStart < chunkEnd) chunkStart += this.ledObject.numOfPixels();
                if (point < chunkEnd) point += this.ledObject.numOfPixels();

                if (point < chunkStart && point > chunkEnd) {
                    pointChunks.add((chunkStart - point) / (chunkStart - chunkEnd));
                }
            } else {
                if (chunkStart > chunkEnd) chunkStart -= this.ledObject.numOfPixels();
                if (point > chunkEnd) point -= this.ledObject.numOfPixels();

                if (point > chunkStart && point < chunkEnd) {
                    pointChunks.add((point - chunkStart) / (chunkEnd - chunkStart));
                }
            }

        }
        return pointChunks.stream().mapToDouble(i -> i).toArray();
    }

    private double[] getBrightnessForPositions(double[] positions) {
        double[] brightness = new double[positions.length];

        for (int i = 0; i < positions.length; i++) {
            if (positions[i] < 0.1) { // 10% start chunk fade
                brightness[i] = positions[i] * 10.0;
            } else if (positions[i] > 0.1 && positions[i] < 0.2) { // 10% full brightness
                brightness[i] = 1.0;
            } else { //80% linear fade
                brightness[i] = 1 - (positions[i] - 0.2) * (1/0.8);
            }
        }
        return brightness;
    }


    private static class Chunk {
        private final double originalStartPosition;
        private final double originalEndPosition;
        private double startPosition;
        private double endPosition;
        private final boolean isForward;

        public Chunk(double startPosition, double endPosition, boolean isForward) {
            this.originalStartPosition = startPosition;
            this.originalEndPosition = endPosition;
            this.isForward = isForward;
        }
    }
}
