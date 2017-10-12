
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Totem extends LEDObject implements ISimulatedLEDObject {

    public HSBColor pixels[] = CreateHSBArray(220);
    public int[] leftIndexes = CreateIndexRange(0, 109);
    public int[] rightIndexes = reverseArray(CreateIndexRange(110, 219));

    @Override
    public HSBColor[] GetAllPixels() {
        return this.pixels;
    }


    //region Simulator
    //----------------
    @Override
    public int getWidth() {
        return 71;
    }

    @Override
    public int getHeight() {
        return 610;
    }

    @Override
    public void draw(BufferedImage bi) {
        //left
        for (int i = 0; i < 110; i++) {
            int color = pixels[i].toRGBInt();
            int[] xs = CreateIndexRange(30, 34);
            int[] ys = CreateIndexRange(30 + i * 5, 30 + i * 5 + 5);
            for (int x : xs) {
                for (int y : ys) {
                    bi.setRGB(x, y, color);
                }
            }
        }

        //right
        for (int i = 109; i >= 0; i--) {
            int color = pixels[219-i].toRGBInt();
            int[] xs = CreateIndexRange(36, 40);
            int[] ys = CreateIndexRange(30 + i * 5, 30 + i * 5 + 5);
            for (int x : xs) {
                for (int y : ys) {
                    bi.setRGB(x, y, color);
                }
            }
        }
    }
    //endregion
}
