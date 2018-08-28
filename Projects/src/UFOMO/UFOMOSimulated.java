import java.awt.*;
import java.awt.image.BufferedImage;

// This class sets the position of each pixel from the UFOMO on the screen, and colors it accordingly
public class UFOMOSimulated implements ISimulatedLEDObject<UFOMOObject> {

    private static final int SIZE = 800; // in pixels
    private static final int PADDING = 20; // in pixels

    private static final double LARGE_CIRCLE_DIAMETER = 10.1; // in meters
    private static final double MEDIUM_CIRCLE_DIAMETER = 6.695; // in meters
    private static final double SMALL_CIRCLE_DIAMETER = 4.45; // in meters
    private static final double OCTAGON_DIAMETER = 2.5; // in meters
    private static final double BEAM_LENGTH = 3.4; // in meters

    private Pixel[] bigCirclePixels;
    private Pixel[] mediumCirclePixels;
    private Pixel[] smallCirclePixels;
    private Pixel[][] octagonPixels = new Pixel[8][]; // 8 arrays of pixels
    private Pixel[][] linePixels = new Pixel[16][]; // 16 arrays of pixels
    private Pixel[][] beamPixels = new Pixel[8][]; // 8 arrays of pixels

    public UFOMOSimulated() {
        calculatePixelsPositions();
    }

    // calculate all the UFOMO pixels positions on the screen. and saves the data
    private void calculatePixelsPositions() {

        UFOMOObject ufomoObject = new UFOMOObject();

        // Circles positions
        int maxRadius = SIZE / 2 - PADDING;
        int bigCircleRadius = maxRadius; // in pixels
        int mediumPixelRadius = (int) Math.round(bigCircleRadius * MEDIUM_CIRCLE_DIAMETER / LARGE_CIRCLE_DIAMETER); // in pixels
        int smallPixelRadius = (int) Math.round(bigCircleRadius * SMALL_CIRCLE_DIAMETER / LARGE_CIRCLE_DIAMETER); // in pixels
        bigCirclePixels = SimulatorUtils.getCirclePixelPositions(ufomoObject.bigCircle.numOfPixels(), bigCircleRadius, SIZE/2, SIZE/2);
        mediumCirclePixels = SimulatorUtils.getCirclePixelPositions(ufomoObject.mediumCircle.numOfPixels(), mediumPixelRadius, SIZE/2, SIZE/2);
        smallCirclePixels = SimulatorUtils.getCirclePixelPositions(ufomoObject.smallCircle.numOfPixels(), smallPixelRadius, SIZE/2, SIZE/2);

        // Octagon and Lines positions
        setOctagonAndLinesPixelPositions();
    }
    private void setOctagonAndLinesPixelPositions() {
        UFOMOObject ufomoObject = new UFOMOObject();
        // Octagon vertices and beam
        int[] xVertices = new int[8];
        int[] yVertices = new int[8];
        int[] xBeamEnd = new int[8];
        int[] yBeamEnd = new int[8];
        for (int i=0; i<8; i++) {
            double angleInDegree = 360 - 22.5 - 90 - i * 360.0 / 8;
            double angleInRadian = Math.toRadians(angleInDegree);

            double cos = Math.cos(angleInRadian);
            double sin = Math.sin(angleInRadian);

            xVertices[i] = (int) Math.round(SIZE/2 + cos * (SIZE/2-PADDING) * OCTAGON_DIAMETER / LARGE_CIRCLE_DIAMETER);
            yVertices[i] = (int) Math.round(SIZE/2 + sin * (SIZE/2-PADDING) * OCTAGON_DIAMETER / LARGE_CIRCLE_DIAMETER);

            xBeamEnd[i] = (int) Math.round(SIZE/2 + cos * (SIZE/2-PADDING) * (OCTAGON_DIAMETER + BEAM_LENGTH * 2) / LARGE_CIRCLE_DIAMETER);
            yBeamEnd[i] = (int) Math.round(SIZE/2 + sin * (SIZE/2-PADDING) * (OCTAGON_DIAMETER + BEAM_LENGTH * 2) / LARGE_CIRCLE_DIAMETER);
        }

        // Octagon pixels
        for (int i=0; i<8; i++) {
            if (i < 7) {
                octagonPixels[i] = SimulatorUtils.getPixelsForLine(ufomoObject.octagon[i].numOfPixels(), xVertices[i+1], yVertices[i+1], xVertices[i], yVertices[i]);
            } else {
                octagonPixels[i] = SimulatorUtils.getPixelsForLine(ufomoObject.octagon[i].numOfPixels(), xVertices[0], yVertices[0], xVertices[i], yVertices[i]);
            }
        }

        // beam pixels
        for (int i=0; i<8; i++) {
            beamPixels[i] = SimulatorUtils.getPixelsForLine(ufomoObject.beam[i].numOfPixels(), xBeamEnd[i], yBeamEnd[i], xVertices[i], yVertices[i]);
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
            double angle2 =  360 - 27 - 90 - (1 + i * 2) * 360 / 16;
            double angle1 = angle2 + 0.2 * 45;

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
            linePixels[i*2] = SimulatorUtils.getPixelsForLine(ufomoObject.lines[i*2].numOfPixels(), lineEnd1X, lineEnd1Y, lineStart1X, lineStart1Y);
            linePixels[i*2+1] = SimulatorUtils.getPixelsForLine(ufomoObject.lines[i*2+1].numOfPixels(), lineEnd2X, lineEnd2Y, lineStart2X, lineStart2Y);
        }
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
    public void draw(UFOMOObject ufomoObject, BufferedImage bi) {
        SimulatorUtils.drawElement(bi, ufomoObject.bigCircle, bigCirclePixels);
        SimulatorUtils.drawElement(bi, ufomoObject.mediumCircle, mediumCirclePixels);
        SimulatorUtils.drawElement(bi, ufomoObject.smallCircle, smallCirclePixels);
        SimulatorUtils.drawElements(bi, ufomoObject.octagon, octagonPixels);
        SimulatorUtils.drawElements(bi, ufomoObject.beam, beamPixels);
        SimulatorUtils.drawElements(bi, ufomoObject.lines, linePixels);
    }
}
