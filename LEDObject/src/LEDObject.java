
// Abstract class that holds the pixels of a led object
public class LEDObject {

    private HSBColor[] pixels;

    public LEDObject(int numOfPixels) {
        this.pixels = new HSBColor[numOfPixels];
        this.clear();
    }

    public void clear() {
        for(int i=0; i<pixels.length; i++) {
            pixels[i] = HSBColor.BLACK;
        }
    }

    public int numOfPixels() {
        return this.pixels.length;
    }

    // get color of a specific pixel
    public HSBColor getColor(int pixelNum) {
        if (pixelNum < 0 || pixelNum >= pixels.length) {
            System.out.println("ERROR: pixel is out of range. pixel " + pixelNum + " isn't in 0-" + pixels.length + " range");
            return HSBColor.BLACK;
        }
        return this.pixels[pixelNum];
    }

    // get color of a specific pixel as RGB integer (best for sending on network)
    public RGBColor getColorRGB(int pixelNum, double brightnessLevel) {
        return getColor(pixelNum).toRGBColor(brightnessLevel);
    }

    // get color of a specific pixel as RGB integer (best for simulator)
    public int getColorRGBInt(int pixelNum) {
        return getColor(pixelNum).toRGBInt();
    }

    // set color of a specific pixel
    public void setColor(int pixelNum, HSBColor color) {
        if (pixelNum < 0 || pixelNum >= pixels.length) {
            System.out.println("ERROR: pixel is out of range. pixel " + pixelNum + " isn't in 0-" + pixels.length + " range");
            return;
        }
        this.pixels[pixelNum] = color;
    }

    // copy pixels array from another led object
    public void copy(LEDObject other) {
        if (numOfPixels() != other.numOfPixels()) {
            System.out.println("ERROR: can't copy led object with " + other.numOfPixels()+  " pixels to a led object with " + numOfPixels() + " pixels");
            return;
        }

        for (int i = 0; i < numOfPixels(); i++) {
            setColor(i, other.getColor(i));
        }
    }

    // merge 2 other pixels array and copy it the this object
    public void mergeAndcopy(LEDObject other1, LEDObject other2, double fadePercent) {
        if (numOfPixels() != other1.numOfPixels() || numOfPixels() != other2.numOfPixels()) {
            System.out.println("ERROR: can't copy led objects with " + other1.numOfPixels()+ " and " + other2.numOfPixels() + " pixels to a led object with " + numOfPixels() + " pixels");
            return;
        }

        for (int i = 0; i < numOfPixels(); i++) {
            setColor(i, HSBColor.mixColors(other1.getColor(i), fadePercent, other2.getColor(i), 1-fadePercent));
        }
    }
}
