import java.awt.*;

public class HSBColor {

    public double hue;
    public double saturation;
    public double brightness;

    public RGBColor toRGBColor() {
        int rgbAsInt = Color.HSBtoRGB((float)hue, (float)saturation, (float)brightness);
        RGBColor rgb = new RGBColor();
        rgb.r = (byte)((rgbAsInt>>16)&0xFF);
        rgb.g = (byte)((rgbAsInt>>8)&0xFF);
        rgb.b = (byte)((rgbAsInt>>0)&0xFF);
        return rgb;
    }

    // TODO: does java supports it in some way?
    public void copyFromOther(HSBColor other) {
        hue = other.hue;
        saturation = other.saturation;
        brightness = other.brightness;
    }
}
