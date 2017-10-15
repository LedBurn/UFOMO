import java.awt.*;

public class HSBColor {

    public double hue;
    public double saturation;
    public double brightness;

    public HSBColor() {
        this(0, 0, 0);
    }

    public HSBColor(double hue, double saturation, double brightness) {
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
    }

    public RGBColor toRGBColor() {
        int rgbAsInt = toRGBInt();
        RGBColor rgb = new RGBColor();
        rgb.r = (byte)((rgbAsInt>>16)&0xFF);
        rgb.g = (byte)((rgbAsInt>>8)&0xFF);
        rgb.b = (byte)((rgbAsInt>>0)&0xFF);
        return rgb;
    }

    public int toRGBInt() {
       return Color.HSBtoRGB((float)hue, (float)saturation, (float)(brightness * brightness));
    }

    // TODO: does java supports it in some way?
    public void copyFromOther(HSBColor other) {
        hue = other.hue;
        saturation = other.saturation;
        brightness = other.brightness;
    }

    public static final HSBColor BLACK = new HSBColor(0,0,0);
}
