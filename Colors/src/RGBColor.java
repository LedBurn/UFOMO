
public class RGBColor {
    public byte r;
    public byte g;
    public byte b;
    public void fixBrightness() {

        int r = fixBrightness(Byte.toUnsignedInt(this.r));
        int g = fixBrightness(Byte.toUnsignedInt(this.g));
        int b = fixBrightness(Byte.toUnsignedInt(this.b));

        this.r = (byte)(r&0xFF);
        this.g = (byte)(g&0xFF);
        this.b = (byte)(b&0xFF);
    }

    private int fixBrightness(int element) {
        double scale = element / (double) 256;
        int newElement = (int)Math.floor(Math.pow(scale, 3) * 256);
        return newElement;
    }
}
