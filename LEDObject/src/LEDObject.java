
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
            System.out.println("ERROR: pixel is out of range. pixel " + pixelNum + "isn't in 0-" + pixels.length + " range");
            return HSBColor.BLACK;
        }
        return this.pixels[pixelNum];
    }

    // get color of a specific pixel as RGB integer (best for sending on network)
    public RGBColor getColorRGB(int pixelNum) {
        return getColor(pixelNum).toRGBColor();
    }

    // get color of a specific pixel as RGB integer (best for simulator)
    public int getColorRGBInt(int pixelNum) {
        return getColor(pixelNum).toRGBInt();
    }

    // set color of a specific pixel
    public void setColor(int pixelNum, HSBColor color) {
        if (pixelNum < 0 || pixelNum >= pixels.length) {
            System.out.println("ERROR: pixel is out of range. pixel " + pixelNum + "isn't in 0-" + pixels.length + " range");
            return;
        }
        this.pixels[pixelNum] = color;
    }
}
