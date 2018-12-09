import java.awt.image.BufferedImage;

public class BabushkaSimulated implements ISimulatedLEDObject<BabushkaObject> {
    private int MARGIN = 50;
    private int RADIUS = 600;
    private int RADIUS_DIFF = 80;

    private int numOfElements = BabushkaObject.SIZES.length;
    private Pixel[][] pixels = new Pixel[numOfElements][];

    public BabushkaSimulated() {
        for (int i = 0; i < numOfElements; i++) {
            int radius = RADIUS - i * RADIUS_DIFF;
            pixels[i] = SimulatorUtils.getPixelsForCircle(BabushkaObject.SIZES[i]*3, MARGIN + RADIUS, MARGIN + RADIUS, radius, 0, 180);
        }
    }

    @Override
    public int getHeight() {
        return RADIUS + 2 * MARGIN;
    }

    @Override
    public int getWidth() {
        return RADIUS * 2 + 2 * MARGIN;
    }

    @Override
    public void draw(BabushkaObject babushkaObject, BufferedImage bi) {
        for (int i=0; i<pixels.length; i++) {
            for (int j=0; j<pixels[i].length/3; j++) {
                SimulatorUtils.drawPixel(bi, pixels[i][j*3+0].x, pixels[i][j*3+0].y, babushkaObject.babushkas[i].getColorRGBInt(j));
                SimulatorUtils.drawPixel(bi, pixels[i][j*3+1].x, pixels[i][j*3+1].y, babushkaObject.babushkas[i].getColorRGBInt(j));
                SimulatorUtils.drawPixel(bi, pixels[i][j*3+2].x, pixels[i][j*3+2].y, babushkaObject.babushkas[i].getColorRGBInt(j));
            }
        }
    }
}