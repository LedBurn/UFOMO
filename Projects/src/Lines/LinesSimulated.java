import java.awt.image.BufferedImage;

public class LinesSimulated implements ISimulatedLEDObject<LinesObject> {

    private static final int NUM_OF_LINES = 16;
    private static final int PIXELS_IN_LINE = 240/3;

    private static final int X_MARGIN = 20;
    private static final int Y_MARGIN = 30;
    private static final int DIFF = 50;
    private static final int DIFF_FOR_PIXEL = 4;


    private Pixel[][] pixels = new Pixel[NUM_OF_LINES][]; // 20 arrays of pixels

    public LinesSimulated() {
        int x = X_MARGIN;
        for (int i = 0; i < NUM_OF_LINES; i++) {
            pixels[i] = new Pixel[PIXELS_IN_LINE * 3 * 2];

            int y = Y_MARGIN;
            for (int j = 0; j < PIXELS_IN_LINE; j++) {
                pixels[i][j*6] = new Pixel(x,y);
                pixels[i][j*6+1] = new Pixel(x,y);
                pixels[i][j*6+2] = new Pixel(x,y);
                pixels[i][j*6+3] = new Pixel(x,getHeight() - y);
                pixels[i][j*6+4] = new Pixel(x,getHeight() - y);
                pixels[i][j*6+5] = new Pixel(x,getHeight() - y);
                y += DIFF_FOR_PIXEL + 1;
            }

            x += DIFF + 1;
        }
    }

    @Override
    public int getWidth() {
        return X_MARGIN * 2 + (NUM_OF_LINES - 1) * DIFF + NUM_OF_LINES;
    }

    @Override
    public int getHeight() {
        return Y_MARGIN * 2 + (PIXELS_IN_LINE * 2 + 1) * DIFF_FOR_PIXEL + PIXELS_IN_LINE * 2;
    }

    @Override
    public void draw(LinesObject linesObject, BufferedImage bi) {
        for (int i=0; i<pixels.length; i++) {
            for (int j=0; j<pixels[i].length/6; j++) {
                SimulatorUtils.drawPixel(bi, pixels[i][j*6].x, pixels[i][j*6].y, linesObject.lines[i].getColorRGBInt(j));
                SimulatorUtils.drawPixel(bi, pixels[i][j*6+1].x, pixels[i][j*6+1].y, linesObject.lines[i].getColorRGBInt(j));
                SimulatorUtils.drawPixel(bi, pixels[i][j*6+2].x, pixels[i][j*6+2].y, linesObject.lines[i].getColorRGBInt(j));
                SimulatorUtils.drawPixel(bi, pixels[i][j*6+3].x, pixels[i][j*6+3].y, linesObject.lines[i].getColorRGBInt(j));
                SimulatorUtils.drawPixel(bi, pixels[i][j*6+4].x, pixels[i][j*6+4].y, linesObject.lines[i].getColorRGBInt(j));
                SimulatorUtils.drawPixel(bi, pixels[i][j*6+5].x, pixels[i][j*6+5].y, linesObject.lines[i].getColorRGBInt(j));
            }
        }
    }
}
