public class PixelsArrayReversed implements IPixelsArray {

    private final IPixelsArray originalArray;

    public PixelsArrayReversed(IPixelsArray originalArray) {
        this.originalArray = originalArray;
    }
    @Override
    public int numOfPixels() {
        return this.originalArray.numOfPixels();
    }

    @Override
    public LEDColor getColor(int pixelNum) {
        return this.originalArray.getColor(this.originalArray.numOfPixels() - 1 - pixelNum);
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
        this.originalArray.setColor(this.originalArray.numOfPixels() - 1 - pixelNum, color);
    }
    @Override
    public void blendColor(int pixelNum, LEDColor color, LEDColor.BLEND_TYPE blendType) {
        LEDColor blendColor = LEDColor.blendColors(this.getColor(pixelNum), color, blendType);
        this.setColor(pixelNum, blendColor);
    }

    @Override
    public void clear() {
        this.originalArray.clear();
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
        this.originalArray.reduceBrightness(brightnessLevel);
    }

    @Override
    public void colorCorrection(ColorCorrectionValues correction) {
        this.originalArray.colorCorrection(correction);
    }
}
