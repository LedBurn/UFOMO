
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Totem extends LEDObject {

    public HSBColor pixels[] = CreateHSBArray(220);
    public int[] leftIndexes = CreateIndexRange(0, 109);
    public int[] rightIndexes = reverseArray(CreateIndexRange(110, 219));

    @Override
    public HSBColor[] GetAllPixels() {
        return this.pixels;
    }
}
