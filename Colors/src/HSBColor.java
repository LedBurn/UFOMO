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

    /*
    the larger the amount, the more weight it will contribute the aggregated hue.
    the amounts don't have to be in the range [0-1], it can have any scale.
     */
    public static double combineHues(double hue1, double amount1, double hue2, double amount2) {
        double x = amount1 * Math.cos(hue1 * 2 * Math.PI) + amount2 * Math.cos(hue2 * 2 * Math.PI);
        double y = amount1 * Math.sin(hue1 * 2 * Math.PI) + amount2 * Math.sin(hue2 * 2 * Math.PI);
        double combinesAngle = Math.atan2(y, x);
        double averagePercent = combinesAngle / ( 2.0 * Math.PI);
        return averagePercent % 1.0;
    }

    public static HSBColor mixColors(HSBColor c1, double amount1, HSBColor c2, double amount2) {
        double hue = HSBColor.combineHues(c1.hue, amount1, c2.hue, amount2);
        double brightness = (c1.brightness * amount1 + c2.brightness * amount2) / (amount1 + amount2);
        double saturation = (c1.saturation * amount1 + c2.saturation * amount2) / (amount1 + amount2);
        return new HSBColor(hue, saturation, brightness);
    }

    public static final HSBColor BLACK = new HSBColor(0,0,0);
    public static final HSBColor RED = new HSBColor(0.0/3.0,1.0,1.0);
    public static final HSBColor GREEN = new HSBColor(1.0/3.0,1.0,1.0);
    public static final HSBColor BLUE = new HSBColor(2.0/3.0,1.0,1.0);
    public static final HSBColor SKYBLUE = new HSBColor(0.6,0.8,0.8);
    public static final HSBColor YELLOW = new HSBColor(1.0/6.0,1.0,1.0);
    public static final HSBColor ORANGE = new HSBColor(1.0/12.0,1.0,1.0);
    public static final HSBColor BROWN = new HSBColor(23.0/255.0,0.9,0.5);
    public static final HSBColor GRAY = new HSBColor(0.0,0.0,0.2);
    public static final HSBColor PINK = new HSBColor(0.9,1.0,1.0);
    public static final HSBColor PURPLE = new HSBColor(0.83,1.0,1.0);
}
