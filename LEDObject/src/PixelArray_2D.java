import java.util.ArrayList;

public class PixelArray_2D {

    public double maxX = 0;
    public double maxY = 0;

    public final ArrayList<Pixel_2D> pixels = new ArrayList<>();

    public void addPixel(Pixel_2D pixel) {
        this.pixels.add(pixel);

        if (pixel.x > this.maxX) {
            this.maxX = pixel.x;
        }

        if (pixel.y > this.maxY) {
            this.maxY = pixel.y;
        }
    }

    public void addPixelArray(IPixelsArray pixelsArray, double startX, double startY, double endX, double endY) {
        for (int i = 0; i < pixelsArray.numOfPixels(); i++) {
            double pixelPosition = i / (double) pixelsArray.numOfPixels();
            double pixelX = getPosition(pixelPosition, startX, endX);
            double pixelY = getPosition(pixelPosition, startY, endY);
            Pixel_2D pixel = new Pixel_2D(pixelX, pixelY, pixelsArray, i);
            this.addPixel(pixel);
        }
    }

    private double getPosition(double pixelPosition, double startValue, double endValue) {
        if (startValue < endValue) {
            return startValue + (endValue - startValue) * pixelPosition;
        } else {
            return endValue + (startValue - endValue) * (1 - pixelPosition);
        }
    }
}
