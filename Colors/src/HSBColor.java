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

    public static final HSBColor BLACK = new HSBColor(0,0,0);
    public static final HSBColor RED = new HSBColor(0.0/3.0,1.0,1.0);
    public static final HSBColor GREEN = new HSBColor(1.0/3.0,1.0,1.0);
    public static final HSBColor BLUE = new HSBColor(2.0/3.0,1.0,1.0);
    public static final HSBColor YELLOW = new HSBColor(1.0/6.0,1.0,1.0);
    public static final HSBColor ORANGE = new HSBColor(1.0/12.0,1.0,1.0);

}
