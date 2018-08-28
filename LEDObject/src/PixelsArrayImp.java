
// The simplest LED object. contains one array of pixels.
public class PixelsArrayImp implements IPixelsArray {

    private HSBColor[] pixels;

    public PixelsArrayImp(int numOfPixels) {
        this.pixels = new HSBColor[numOfPixels];
        this.clear();
    }

    @Override
    public void clear() {
        for(int i=0; i<pixels.length; i++) {
            pixels[i] = HSBColor.BLACK;
        }
    }

    @Override
    public int numOfPixels() {
        return this.pixels.length;
    }

    @Override
    public HSBColor getColor(int pixelNum) {
        if (pixelNum < 0 || pixelNum >= pixels.length) {
            System.out.println("ERROR: pixel is out of range. pixel " + pixelNum + " isn't in 0-" + pixels.length + " range");
            return HSBColor.BLACK;
        }
        return this.pixels[pixelNum];
    }

    @Override
    public RGBColor getColorRGB(int pixelNum, double brightnessLevel) {
        return getColor(pixelNum).toRGBColor(brightnessLevel);
    }

    @Override
    public int getColorRGBInt(int pixelNum) {
        return getColor(pixelNum).toRGBInt();
    }

    @Override
    public void setColor(int pixelNum, HSBColor color) {
        if (pixelNum < 0 || pixelNum >= pixels.length) {
            System.out.println("ERROR: pixel is out of range. pixel " + pixelNum + " isn't in 0-" + pixels.length + " range");
            return;
        }
        this.pixels[pixelNum] = color;
    }

    @Override
    public void copy(IPixelsArray other) {
        if (numOfPixels() != other.numOfPixels()) {
            System.out.println("ERROR: can't copy led object with " + other.numOfPixels()+  " pixels to a led object with " + numOfPixels() + " pixels");
            return;
        }

        for (int i = 0; i < numOfPixels(); i++) {
            setColor(i, other.getColor(i));
        }
    }

    @Override
    public void mergeAndCopy(IPixelsArray other1, IPixelsArray other2, double fadePercent) {
        if (numOfPixels() != other1.numOfPixels() || numOfPixels() != other2.numOfPixels()) {
            System.out.println("ERROR: can't copy led objects with " + other1.numOfPixels()+ " and " + other2.numOfPixels() + " pixels to a led object with " + numOfPixels() + " pixels");
            return;
        }

        for (int i = 0; i < numOfPixels(); i++) {
            setColor(i, HSBColor.mixColors(other1.getColor(i), fadePercent, other2.getColor(i), 1-fadePercent));
        }
    }
}
