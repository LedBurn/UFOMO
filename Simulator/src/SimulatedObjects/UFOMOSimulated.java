import java.awt.*;
import java.awt.image.BufferedImage;

// This class sets the position of each pixel from the UFOMO on the screen, and colors it accordingly
public class UFOMOSimulated implements ISimulatedLEDObject {

    private static final int SIZE = 1000; // in pixels
    private static final int PADDING = 20; // in pixels

    private static final double LARGE_CIRCLE_DIAMETER = 10.1; // in meters
    private static final double MEDIUM_CIRCLE_DIAMETER = 6.695; // in meters
    private static final double SMALL_CIRCLE_DIAMETER = 4.45; // in meters
    private static final double OCTAGON_DIAMETER = 2.5; // in meters

    private Pixel[] bigCirclePixels;
    private Pixel[] mediumCirclePixels;
    private Pixel[] smallCirclePixels;
    private Pixel[][] octagonPixels = new Pixel[8][]; // 8 arrays of pixels
    private Pixel[][] linePixels = new Pixel[16][]; // 16 arrays of pixels
    private Pixel[][] beamPixels = new Pixel[8][]; // 8 arrays of pixels

    private UFOMOObject ufomoObject;

    public UFOMOSimulated(UFOMOObject ufomoObject) {
        this.ufomoObject = ufomoObject;
        calculatePixelsPositions();
    }

    // calculate all the UFOMO pixels positions on the screen. and saves the data
    private void calculatePixelsPositions() {

        // Circles positions
        int maxRadius = SIZE / 2 - PADDING;
        int bigCircleRadius = maxRadius; // in pixels
        int mediumPixelRadius = (int) Math.round(bigCircleRadius * MEDIUM_CIRCLE_DIAMETER / LARGE_CIRCLE_DIAMETER); // in pixels
        int smallPixelRadius = (int) Math.round(bigCircleRadius * SMALL_CIRCLE_DIAMETER / LARGE_CIRCLE_DIAMETER); // in pixels
        bigCirclePixels = getCirclePixelPositions(ufomoObject.bigCircle.numOfPixels(), bigCircleRadius);
        mediumCirclePixels = getCirclePixelPositions(ufomoObject.mediumCircle.numOfPixels(), mediumPixelRadius);
        smallCirclePixels = getCirclePixelPositions(ufomoObject.smallCircle.numOfPixels(), smallPixelRadius);

        // Octagon and Lines positions
        setOctagonAndLinesPixelPositions();
    }
    private void setOctagonAndLinesPixelPositions() {
        // Octagon vertices
        int[] xVertices = new int[8];
        int[] yVertices = new int[8];
        for (int i=0; i<8; i++) {
            double angleInDegree = i * 360.0 / 8;
            double angleInRadian = Math.toRadians(angleInDegree);

            double cos = Math.cos(angleInRadian);
            double sin = Math.sin(angleInRadian);

            int xPos = (int) Math.round(SIZE/2 + cos * (SIZE/2-PADDING) * OCTAGON_DIAMETER / LARGE_CIRCLE_DIAMETER);
            int yPos = (int) Math.round(SIZE/2 + sin * (SIZE/2-PADDING) * OCTAGON_DIAMETER / LARGE_CIRCLE_DIAMETER);

            xVertices[i] = xPos;
            yVertices[i] = yPos;
        }

        // Octagon pixels
        for (int i=0; i<8; i++) {
            if (i < 7) {
                octagonPixels[i] = getPixelsForLine(ufomoObject.octagon[i].numOfPixels(), xVertices[i], yVertices[i], xVertices[i+1], yVertices[i+1]);
            } else {
                octagonPixels[i] = getPixelsForLine(ufomoObject.octagon[i].numOfPixels(), xVertices[i], yVertices[i], xVertices[0], yVertices[0]);
            }
        }

        // Lines
        for (int i=0; i<8; i++) {

            // line start
            Pixel octagonStart = new Pixel(xVertices[i], yVertices[i]);
            Pixel octagonEnd = new Pixel(xVertices[i<7 ? i+1 : 0], yVertices[i<7 ? i+1 : 0]);

            int lineStart1X = (int) Math.round(octagonStart.x + 0.1 * (octagonEnd.x - octagonStart.x));
            int lineStart1Y = (int) Math.round(octagonStart.y + 0.1 * (octagonEnd.y - octagonStart.y));

            int lineStart2X = (int) Math.round(octagonEnd.x + 0.1 * (octagonStart.x - octagonEnd.x));
            int lineStart2Y = (int) Math.round(octagonEnd.y + 0.1 * (octagonStart.y - octagonEnd.y));

            // line end
            double angle1 =  (1 + i * 2) * 360 / 16;
            double angle2 = angle1 + 0.2 * 45;

            double angle1InRadian = Math.toRadians(angle1);
            double angle2InRadian = Math.toRadians(angle2);

            double cos1 = Math.cos(angle1InRadian);
            double sin1 = Math.sin(angle1InRadian);
            double cos2 = Math.cos(angle2InRadian);
            double sin2 = Math.sin(angle2InRadian);

            int lineEnd1X = (int) Math.round(SIZE/2 + cos1 * (SIZE/2-PADDING));
            int lineEnd1Y = (int) Math.round(SIZE/2 + sin1 * (SIZE/2-PADDING));

            int lineEnd2X = (int) Math.round(SIZE/2 + cos2 * (SIZE/2-PADDING));
            int lineEnd2Y = (int) Math.round(SIZE/2 + sin2 * (SIZE/2-PADDING));

            // line
            linePixels[i*2] = getPixelsForLine(ufomoObject.lines[i*2].numOfPixels(), lineStart1X, lineStart1Y, lineEnd1X, lineEnd1Y);
            linePixels[i*2+1] = getPixelsForLine(ufomoObject.lines[i*2+1].numOfPixels(), lineStart2X, lineStart2Y, lineEnd2X, lineEnd2Y);
        }
    }
    private Pixel[] getCirclePixelPositions(int numOfPixels, int radius) {
        int xCenter = SIZE / 2;
        int yCenter = SIZE / 2;

        Pixel[] pixels = new Pixel[numOfPixels];

        for (int i=0; i<numOfPixels; i++) {
            double angleInDegree = i * 360.0 / numOfPixels;
            double angleInRadian = Math.toRadians(angleInDegree);

            double cos = Math.cos(angleInRadian);
            double sin = Math.sin(angleInRadian);

            int xPos = (int) Math.round(xCenter + cos * radius);
            int yPos = (int) Math.round(yCenter + sin * radius);

            pixels[i] = new Pixel(xPos, yPos);
        }

        return  pixels;
    }
    private Pixel[] getPixelsForLine(int numOfLEDs, int startX, int startY, int endX, int endY) {
        Pixel[] pixels = new Pixel[numOfLEDs];

        for (int i=0; i<numOfLEDs; i++) {
            double percent = 1 - i / (float)numOfLEDs;
            int xPos = (int) Math.round(startX + percent * (endX - startX));
            int yPos = (int) Math.round(startY + percent * (endY - startY));

            pixels[i] = new Pixel(xPos, yPos);
        }

        return pixels;
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
        drawElement(bi, ufomoObject.bigCircle, bigCirclePixels);
        drawElement(bi, ufomoObject.mediumCircle, mediumCirclePixels);
        drawElement(bi, ufomoObject.smallCircle, smallCirclePixels);
        drawElements(bi, ufomoObject.octagon, octagonPixels);
        drawElements(bi, ufomoObject.lines, linePixels);
    }

    private void drawElement(BufferedImage bi, LEDObject element, Pixel[] pixels) {
        for (int i=0; i<element.numOfPixels(); i++) {
            drawPixel(bi, pixels[i].x, pixels[i].y, element.getColorRGBInt(i));
        }
    }

    private void drawElements(BufferedImage bi, LEDObject[] elements, Pixel[][] pixels) {
        for (int i=0; i<pixels.length; i++) {
            for (int j=0; j<pixels[i].length; j++) {
                drawPixel(bi, pixels[i][j].x, pixels[i][j].y, elements[i].getColorRGBInt(j));
            }
        }
    }

    private void drawPixel(BufferedImage bi, int x, int y, int color) {
        // TODO delete this if
        if (color == Color.black.getRGB()) { // in case of black - show something just to know the pixel is there
            color = new Color(75, 75, 75).getRGB();
        }
        bi.setRGB(x, y, color);
        bi.setRGB(x+1, y, color);
        bi.setRGB(x-1, y, color);
        bi.setRGB(x, y+1, color);
        bi.setRGB(x, y-1, color);
    }
}
