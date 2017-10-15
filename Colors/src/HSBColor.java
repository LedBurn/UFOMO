import java.awt.*;

public class HSBColor {

    public double hue;
    public double saturation;
    public double brightness;

    public static HSBColor createFromParams(double hue, double saturation, double brightness) {
        HSBColor c = new HSBColor();
        c.hue = hue;
        c.saturation = saturation;
        c.brightness = brightness;
        return c;
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
       return Color.HSBtoRGB((float)hue, (float)saturation, (float)brightness);
    }

    // TODO: does java supports it in some way?
    public void copyFromOther(HSBColor other) {
        hue = other.hue;
        saturation = other.saturation;
        brightness = other.brightness;
    }
}
