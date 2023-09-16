import java.awt.*;
import java.awt.image.BufferedImage;

public class HomeSimulated implements ISimulatedLEDObject<HomeObject> {

    private static final int NUM_OF_LINES = 8;
    private static final int PIXELS_IN_LINE = 300;

    private static final int DIFF = 50;
    private static final int DIFF_FOR_PIXEL = 4;


//    private static final int H

    // Height
    private static final int MARGIN = 50;
    private static final int MULTIPLIER = 2;
    // 8
    // 144 * 2
    // 8
    // 144 * 2
    // 8

    // Width
    // 8
    // 210 * 2
    // 8








    private Pixel[][] pixels = new Pixel[NUM_OF_LINES][]; // 20 arrays of pixels


    public HomeSimulated() {
//        int x = X_MARGIN;
//        for (int i = 0; i < NUM_OF_LINES; i++) {
//            pixels[i] = new Pixel[PIXELS_IN_LINE * 3 * 2];
//
//            int y = Y_MARGIN;
//            for (int j = 0; j < PIXELS_IN_LINE; j++) {
//                pixels[i][j*6] = new Pixel(x,y);
//                pixels[i][j*6+1] = new Pixel(x,y);
//                pixels[i][j*6+2] = new Pixel(x,y);
//                pixels[i][j*6+3] = new Pixel(x,getHeight() - y);
//                pixels[i][j*6+4] = new Pixel(x,getHeight() - y);
//                pixels[i][j*6+5] = new Pixel(x,getHeight() - y);
//                y += DIFF_FOR_PIXEL + 1;
//            }
//
//            x += DIFF + 1;
//        }
    }

    @Override
    public int getWidth() {
        return MARGIN + HomeObject.EXTERNAL_FRONT_NUM_OF_LEDS[2] * MULTIPLIER + MARGIN;
    }

    @Override
    public int getHeight() {
        return MARGIN + HomeObject.CEILING_NUM_OF_LEDS[0] * MULTIPLIER + MARGIN +
                HomeObject.EXTERNAL_FRONT_NUM_OF_LEDS[1] * MULTIPLIER + MARGIN;
    }

    @Override
    public void draw(HomeObject homeObject, BufferedImage bi) {

        int ceiling_start_x = (getWidth() - (homeObject.ceiling[1].numOfPixels() * MULTIPLIER))/2;
        for (int i = homeObject.ceiling[0].numOfPixels() - 1; i >= 0; i--) {
            SimulatorUtils.drawPixel(bi, ceiling_start_x + homeObject.ceiling[1].numOfPixels() * MULTIPLIER, MARGIN + i * MULTIPLIER , homeObject.ceiling[0].getColorRGBInt(homeObject.ceiling[0].numOfPixels() - 1-i));
        }
        for (int i = homeObject.ceiling[1].numOfPixels() - 1; i >= 0; i--) {
            SimulatorUtils.drawPixel(bi, ceiling_start_x + i * MULTIPLIER, MARGIN , homeObject.ceiling[1].getColorRGBInt(homeObject.ceiling[1].numOfPixels() - 1-i));
        }
        for (int i = 0; i < homeObject.ceiling[2].numOfPixels(); i++) {
            SimulatorUtils.drawPixel(bi, ceiling_start_x, MARGIN + i * MULTIPLIER , homeObject.ceiling[2].getColorRGBInt(i));
        }
        for (int i = 0; i < homeObject.ceiling[3].numOfPixels(); i++) {
            SimulatorUtils.drawPixel(bi, ceiling_start_x + i * MULTIPLIER, MARGIN + homeObject.ceiling[0].numOfPixels() * MULTIPLIER , homeObject.ceiling[3].getColorRGBInt(i));
        }

        int external_start_y = MARGIN + HomeObject.CEILING_NUM_OF_LEDS[0] * MULTIPLIER + MARGIN;
        for (int i = homeObject.frontExternal[0].numOfPixels() - 1; i >= 0; i--) {
            SimulatorUtils.drawPixel(bi, MARGIN + i * MULTIPLIER, getHeight() - MARGIN , homeObject.frontExternal[0].getColorRGBInt(homeObject.frontExternal[0].numOfPixels() - 1-i));
        }
        for (int i = homeObject.frontExternal[1].numOfPixels() - 1; i >= 0; i--) {
            SimulatorUtils.drawPixel(bi, MARGIN, external_start_y + i * MULTIPLIER , homeObject.frontExternal[1].getColorRGBInt(homeObject.frontExternal[1].numOfPixels() - 1-i));
        }
        for (int i = 0; i< homeObject.frontExternal[2].numOfPixels(); i++) {
            SimulatorUtils.drawPixel(bi, MARGIN + i * MULTIPLIER, external_start_y , homeObject.frontExternal[2].getColorRGBInt(i));
        }
        for (int i = 0; i< homeObject.frontExternal[3].numOfPixels(); i++) {
            SimulatorUtils.drawPixel(bi, getWidth() - MARGIN, + external_start_y + i * MULTIPLIER , homeObject.frontExternal[3].getColorRGBInt(i));
        }
        for (int i = 0; i< homeObject.frontExternal[4].numOfPixels(); i++) {
            SimulatorUtils.drawPixel(bi, getWidth() - MARGIN - i * MULTIPLIER, getHeight() - MARGIN, homeObject.frontExternal[4].getColorRGBInt(i));
        }

        int internal_start_x = (getWidth() - (homeObject.frontInternal[1].numOfPixels() * MULTIPLIER))/2;
        int internal_start_y = external_start_y + 100;
        for (int i = homeObject.frontInternal[0].numOfPixels() - 1; i >= 0; i--) {
            SimulatorUtils.drawPixel(bi, internal_start_x, internal_start_y + i * MULTIPLIER , homeObject.frontInternal[0].getColorRGBInt(homeObject.frontInternal[0].numOfPixels() - 1-i));
        }
        for (int i = 0; i< homeObject.frontInternal[1].numOfPixels(); i++) {
            SimulatorUtils.drawPixel(bi, internal_start_x + i * MULTIPLIER, internal_start_y , homeObject.frontInternal[1].getColorRGBInt(i));
        }
        for (int i = 0; i< homeObject.frontInternal[2].numOfPixels(); i++) {
            SimulatorUtils.drawPixel(bi, internal_start_x + homeObject.frontInternal[1].numOfPixels() * MULTIPLIER, internal_start_y + i * MULTIPLIER , homeObject.frontInternal[2].getColorRGBInt(i));
        }
        for (int i = homeObject.frontInternal[3].numOfPixels() - 1; i >= 0; i--) {
            SimulatorUtils.drawPixel(bi, internal_start_x + i * MULTIPLIER, internal_start_y + homeObject.frontInternal[0].numOfPixels() * MULTIPLIER , homeObject.frontInternal[3].getColorRGBInt(homeObject.frontInternal[3].numOfPixels() - 1-i));
        }




//        for (int i = homeObject.ceiling[0].numOfPixels()-1; i >= 0; i--) {
//
//        }
////        SimulatorUtils.getPixelsForLine(homeObject.ceiling[0].numOfPixels(), )
//
//        for (int i=0; i<pixels.length; i++) {
//            for (int j=0; j<pixels[i].length/6; j++) {
//                SimulatorUtils.drawPixel(bi, pixels[i][j*6].x, pixels[i][j*6].y, linesObject.lines[i].getColorRGBInt(j));
//                SimulatorUtils.drawPixel(bi, pixels[i][j*6+1].x, pixels[i][j*6+1].y, linesObject.lines[i].getColorRGBInt(j));
//                SimulatorUtils.drawPixel(bi, pixels[i][j*6+2].x, pixels[i][j*6+2].y, linesObject.lines[i].getColorRGBInt(j));
//                SimulatorUtils.drawPixel(bi, pixels[i][j*6+3].x, pixels[i][j*6+3].y, linesObject.lines[i].getColorRGBInt(j));
//                SimulatorUtils.drawPixel(bi, pixels[i][j*6+4].x, pixels[i][j*6+4].y, linesObject.lines[i].getColorRGBInt(j));
//                SimulatorUtils.drawPixel(bi, pixels[i][j*6+5].x, pixels[i][j*6+5].y, linesObject.lines[i].getColorRGBInt(j));
//            }
//        }
    }
}
