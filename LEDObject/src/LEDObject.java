import java.util.Random;

/**
 * An abstract class of a physical LED object
 * */
abstract public class LEDObject {
    abstract public HSBColor[] GetAllPixels();
    public int GetPixelsNumber() { return this.GetAllPixels().length; }

    public void clear() {
        HSBColor[] allPixels = GetAllPixels();
        for(HSBColor c : allPixels) {
            c.copyFromOther(HSBColor.BLACK);
        }
    }

    public static int[] CreateIndexRange(int firstIndex, int lastIndex) {
        int allIndexes[] = new int[lastIndex - firstIndex + 1];
        for(int i=firstIndex; i <= lastIndex; i++) {
            allIndexes[i-firstIndex] = i;
        }
        return allIndexes;
    }

    public static HSBColor[] CreateHSBArray(int numOfPixels) {
        HSBColor pixels[] = new HSBColor[numOfPixels];
        Random rand = new Random();
        for(int i=0; i<numOfPixels; i++) {
            pixels[i] = new HSBColor();
            pixels[i].brightness = 1.0f;
            pixels[i].saturation = 1.0f;
            pixels[i].hue = rand.nextFloat();
        }
        return pixels;
    }

    public RGBColor[] GetRGBColors(int begin, int end) {
        // TODO: handle bad input
        int numberOfPixels = end-begin;
        HSBColor pixels[] = GetAllPixels();
        RGBColor rgbArr[] = new RGBColor[numberOfPixels];
        for(int i=0; i<numberOfPixels; i++) {
            rgbArr[i] = pixels[begin + i].toRGBColor();
        }
        return rgbArr;
    }

    public int[] reverseArray(int[] arr) {
        int revArr[] = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            revArr[i] = arr[arr.length - i - 1];
        }
        return revArr;
    }
}
