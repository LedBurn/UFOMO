import java.awt.*;
import java.awt.image.BufferedImage;

public class SimulatorUtils {

    public static Pixel[] getCirclePixelPositions(int numOfPixels, int radius, int xCenter, int yCenter) {

        Pixel[] pixels = new Pixel[numOfPixels];

        for (int i=0; i<numOfPixels; i++) {
            double angleInDegree = 266 - (i * 360.0 / numOfPixels);
            double angleInRadian = Math.toRadians(angleInDegree);

            double cos = Math.cos(angleInRadian);
            double sin = Math.sin(angleInRadian);

            int xPos = (int) Math.round(xCenter + cos * radius);
            int yPos = (int) Math.round(yCenter + sin * radius);

            pixels[i] = new Pixel(xPos, yPos);
        }

        return  pixels;
    }
    public static Pixel[] getPixelsForLine(int numOfLEDs, int startX, int startY, int endX, int endY) {
        Pixel[] pixels = new Pixel[numOfLEDs];

        for (int i=0; i<numOfLEDs; i++) {
            double percent = 1 - i / (float)numOfLEDs;
            int xPos = (int) Math.round(startX + percent * (endX - startX));
            int yPos = (int) Math.round(startY + percent * (endY - startY));

            pixels[i] = new Pixel(xPos, yPos);
        }

        return pixels;
    }


    public static void drawElement(BufferedImage bi, IPixelsArray element, Pixel[] pixels) {
        for (int i=0; i<element.numOfPixels(); i++) {
            drawPixel(bi, pixels[i].x, pixels[i].y, element.getColorRGBInt(i));
        }
    }

    public static void drawElements(BufferedImage bi, IPixelsArray[] elements, Pixel[][] pixels) {
        for (int i=0; i<pixels.length; i++) {
            for (int j=0; j<pixels[i].length; j++) {
                drawPixel(bi, pixels[i][j].x, pixels[i][j].y, elements[i].getColorRGBInt(j));
            }
        }
    }

    public static void drawPixel(BufferedImage bi, int x, int y, int color) {
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
