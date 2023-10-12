/**
 * Takes a number of pixel arrays into one long pixel array
 */
public class PixlesArrayUnionImp implements IPixelsArray {

    private final IPixelsArray[] originalArrays;

    public PixlesArrayUnionImp(IPixelsArray[] originalArrays) {
        this.originalArrays = originalArrays;
    }
    @Override
    public int numOfPixels() {
        int count = 0;
        for (IPixelsArray pixelsArray : this.originalArrays) {
            count += pixelsArray.numOfPixels();
        }
        return count;
    }

    @Override
    public LEDColor getColor(int pixelNum) {
        if (pixelNum < 0 || pixelNum >= this.numOfPixels()) {
            System.out.println("ERROR: pixel is out of range. pixel " + pixelNum + " isn't in 0-" + this.numOfPixels() + " range");
            return LEDColor.BLACK;
        }

        for (IPixelsArray pixelsArray : this.originalArrays) {
            if (pixelNum < pixelsArray.numOfPixels()) {
                return pixelsArray.getColor(pixelNum);
            }
            pixelNum -= pixelsArray.numOfPixels();

        }
        return null;
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
        if (pixelNum < 0 || pixelNum >= this.numOfPixels()) {
            System.out.println("ERROR: pixel is out of range. pixel " + pixelNum + " isn't in 0-" + this.numOfPixels() + " range");
            return;
        }

        for (IPixelsArray pixelsArray : this.originalArrays) {
            if (pixelNum < pixelsArray.numOfPixels()) {
                pixelsArray.setColor(pixelNum, color);
                return;
            }
            pixelNum -= pixelsArray.numOfPixels();

        }
    }
    @Override
    public void blendColor(int pixelNum, LEDColor color, LEDColor.BLEND_TYPE blendType) {
        LEDColor blendColor = LEDColor.blendColors(this.getColor(pixelNum), color, blendType);
        this.setColor(pixelNum, blendColor);
    }

    @Override
    public void clear() {
        for (IPixelsArray pixelsArray : this.originalArrays) {
            pixelsArray.clear();
        }
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
        for (IPixelsArray pixelsArray : this.originalArrays) {
            pixelsArray.reduceBrightness(brightnessLevel);
        }
    }

    @Override
    public void colorCorrection(ColorCorrectionValues correction) {
        for (IPixelsArray pixelsArray : this.originalArrays) {
            pixelsArray.colorCorrection(correction);
        }
    }
}
