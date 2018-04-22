import java.awt.image.BufferedImage;

public class SimulatedTop implements ISimulatedLEDObject {

    private final int SIZE = 1000; // in pixels
    private final int PADDING = 20; // in pixels

    private final double LARGE_DIAMETER = 10.1; // in meters
    private final double MEDIUM_DIAMETER = 6.695; // in meters
    private final double SMALL_DIAMETER = 4.45; // in meters

    private Circle largeCircle;
    private Circle mediumCircle;
    private Circle smallCircle;

    private Pixel[] largeCirclePixels;
    private Pixel[] mediumCirclePixels;
    private Pixel[] smallCirclePixels;

    public SimulatedTop(Circle largeCircle, Circle mediumCircle, Circle smallCircle) {
        this.largeCircle = largeCircle;
        this.mediumCircle = mediumCircle;
        this.smallCircle = smallCircle;

        this.calculatePixels();
    }

    private void calculatePixels() {
        int maxRadius = SIZE / 2 - PADDING;

        int largePixelRadius = maxRadius;
        this.largeCirclePixels = this.calculateCirclePixels(this.largeCircle, largePixelRadius);

        int mediumPixelRadius = (int) Math.round(largePixelRadius * MEDIUM_DIAMETER / LARGE_DIAMETER);
        this.mediumCirclePixels = this.calculateCirclePixels(this.mediumCircle, mediumPixelRadius);

        int smallPixelRadius = (int) Math.round(largePixelRadius * SMALL_DIAMETER / LARGE_DIAMETER);
        this.smallCirclePixels = this.calculateCirclePixels(this.smallCircle, smallPixelRadius);
    }

    private Pixel[] calculateCirclePixels(Circle circle, int radius) {

        int numOfLEDs = circle.GetPixelsNumber();
        int xCenter = SIZE / 2;
        int yCenter = SIZE / 2;

        Pixel[] pixels = new Pixel[numOfLEDs];

        for (int i=0; i<numOfLEDs; i++) {
            double angleInDegree = i * 360.0 / numOfLEDs;
            double angleInRadian = Math.toRadians(angleInDegree);

            double cos = Math.cos(angleInRadian);
            double sin = Math.sin(angleInRadian);

            int xPos = (int) Math.round(xCenter + cos * radius);
            int yPos = (int) Math.round(yCenter + sin * radius);

            pixels[i] = new Pixel(xPos, yPos);
        }

        return  pixels;
    }

    @Override
    public int getWidth() {
        return SIZE;
    }

    @Override
    public int getHeight() {
        return SIZE;
    }

    @Override
    public void draw(BufferedImage bi) {
        this.drawCircle(bi, this.largeCircle, this.largeCirclePixels);
        this.drawCircle(bi, this.mediumCircle, this.mediumCirclePixels);
        this.drawCircle(bi, this.smallCircle, this.smallCirclePixels);
    }

    private void drawCircle(BufferedImage bi, Circle circle, Pixel[] pixels) {
        for (int i=0; i<circle.GetPixelsNumber(); i++) {
            bi.setRGB(pixels[i].x, pixels[i].y, circle.GetAllPixels()[i].toRGBInt());
            bi.setRGB(pixels[i].x+1, pixels[i].y, circle.GetAllPixels()[i].toRGBInt());
            bi.setRGB(pixels[i].x-1, pixels[i].y, circle.GetAllPixels()[i].toRGBInt());
            bi.setRGB(pixels[i].x, pixels[i].y+1, circle.GetAllPixels()[i].toRGBInt());
            bi.setRGB(pixels[i].x, pixels[i].y-1, circle.GetAllPixels()[i].toRGBInt());
        }
    }
}
