import java.awt.image.BufferedImage;

public class SimulatedBottom implements ISimulatedLEDObject {

    private final int SIZE = 1000; // in pixels
    private final int PADDING = 20; // in pixels

    private final double LARGE_DIAMETER = 10.1; // in meters
    private final double SMALL_DIAMETER = 2.5; // in meters

    private Pixel[][] octagonPixels = new Pixel[8][]; // 8 arrays of pixels
    private Pixel[][] linePixels = new Pixel[16][]; // 16 arrays of pixels

    public SimulatedBottom() {
        calculatePixels();
    }

    private void calculatePixels() {
        // Octagon vertices
        int[] xVertices = new int[8];
        int[] yVertices = new int[8];
        for (int i=0; i<8; i++) {
            double angleInDegree = i * 360.0 / 8;
            double angleInRadian = Math.toRadians(angleInDegree);

            double cos = Math.cos(angleInRadian);
            double sin = Math.sin(angleInRadian);

            int xPos = (int) Math.round(SIZE/2 + cos * (SIZE/2-PADDING) * SMALL_DIAMETER / LARGE_DIAMETER);
            int yPos = (int) Math.round(SIZE/2 + sin * (SIZE/2-PADDING) * SMALL_DIAMETER / LARGE_DIAMETER);

            xVertices[i] = xPos;
            yVertices[i] = yPos;
        }

        // Octagon pixels
        for (int i=0; i<8; i++) {
            if (i < 7) {
                this.octagonPixels[i] = pixelsForLine(33, xVertices[i], yVertices[i], xVertices[i+1], yVertices[i+1]);
            } else {
                this.octagonPixels[i] = pixelsForLine(33, xVertices[i], yVertices[i], xVertices[0], yVertices[0]);
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
            linePixels[i*2] = pixelsForLine(240, lineStart1X, lineStart1Y, lineEnd1X, lineEnd1Y);
            linePixels[i*2+1] = pixelsForLine(240, lineStart2X, lineStart2Y, lineEnd2X, lineEnd2Y);
        }
    }

    private Pixel[] pixelsForLine(int numOfLEDs, int startX, int startY, int endX, int endY) {
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
        this.drawOctagon(bi);
        this.drawLines(bi);
    }

    private void drawOctagon(BufferedImage bi) {
        for (int i=0; i<this.octagonPixels.length; i++) {
            for (int j=0; j<this.octagonPixels[i].length; j++) {
                bi.setRGB(this.octagonPixels[i][j].x, this.octagonPixels[i][j].y, 0xFFFF);
            }
        }
    }

    private void drawLines(BufferedImage bi) {
        for (int i=0; i<this.linePixels.length; i++) {
            for (int j=0; j<this.linePixels[i].length; j++) {
                bi.setRGB(this.linePixels[i][j].x, this.linePixels[i][j].y, 0xFFFF);
            }
        }
    }
}
