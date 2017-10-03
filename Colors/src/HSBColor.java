import java.awt.*;

public class HSBColor {

    public float hue;
    public float saturation;
    public float brightness;

    public RGBColor toRGBColor() {
        int rgbAsInt = Color.HSBtoRGB(hue, saturation, brightness);
        RGBColor rgb = new RGBColor();
        rgb.r = (byte)((rgbAsInt>>16)&0xFF);
        rgb.g = (byte)((rgbAsInt>>8)&0xFF);
        rgb.b = (byte)((rgbAsInt>>0)&0xFF);
        return rgb;
    }
}
