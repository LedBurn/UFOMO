
// The simplest LED object. contains one array of pixels.
public class PixelsArrayImp implements IPixelsArray {

    private LEDColor[] pixels;

    public PixelsArrayImp(int numOfPixels) {
        this.pixels = new LEDColor[numOfPixels];
        this.clear();
    }

    @Override
    public void clear() {
        for(int i=0; i<pixels.length; i++) {
            pixels[i] = LEDColor.BLACK;
        }
    }

    @Override
    public int numOfPixels() {
        return this.pixels.length;
    }

    @Override
    public LEDColor getColor(int pixelNum) {
        if (pixelNum < 0 || pixelNum >= pixels.length) {
            System.out.println("ERROR: pixel is out of range. pixel " + pixelNum + " isn't in 0-" + pixels.length + " range");
            return LEDColor.BLACK;
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
    public void setColor(int pixelNum, LEDColor color) {
        if (pixelNum < 0 || pixelNum >= pixels.length) {
            System.out.println("ERROR: pixel is out of range. pixel " + pixelNum + " isn't in 0-" + pixels.length + " range");
            return;
        }
        this.pixels[pixelNum] = color;
    }

    @Override
    public void blendColor(int pixelNum, LEDColor color, LEDColor.BLEND_TYPE blendType) {
        LEDColor blendColor = LEDColor.blendColors(this.getColor(pixelNum), color, blendType);
        this.setColor(pixelNum, blendColor);
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
            setColor(i, LEDColor.mixColors(other1.getColor(i), fadePercent, other2.getColor(i), 1-fadePercent));
        }
    }

    @Override
    public void reduceBrightness(double brightnessLevel) {
        for (int i = 0; i < numOfPixels(); i++) {
            LEDColor color = getColor(i);
            setColor(i, new LEDColor(color.hue, color.saturation, color.brightness * brightnessLevel));
        }
    }

    @Override
    public void colorCorrection(ColorCorrectionValues correction) {
        for (int i = 0; i < numOfPixels(); i++) {
            LEDColor color = getColor(i);
            setColor(i, LEDColor.colorCorrection(color, correction));
        }
    }
}
