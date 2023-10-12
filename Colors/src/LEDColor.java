import java.awt.*;

public class LEDColor {

    public double hue;
    public double saturation;
    public double brightness;

    public LEDColor() {
        this(0, 0, 0);
    }

    public LEDColor(double hue) {
        this(hue, 1.0, 1.0);
    }

    public LEDColor(double hue, double saturation, double brightness) {
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;

        while (this.hue < 0) this.hue += 1;
        while (this.hue >=1) this.hue -= 1;
        this.saturation = Math.max(0.0, Math.min(1.0, this.saturation));
        this.brightness = Math.max(0.0, Math.min(1.0, this.brightness));
    }

    public static LEDColor hsbColorFromHex(String hex) {
        if (!hex.startsWith("#")) {
            hex = "#" + hex;
        }
        Color colorObj = Color.decode(hex);
        float[] hsbvals = new float[3];
        Color.RGBtoHSB(colorObj.getRed(), colorObj.getGreen(), colorObj.getBlue(), hsbvals);
        return new LEDColor(hsbvals[0], hsbvals[1], hsbvals[2]);
    }

    public static LEDColor randomHue() {
        return new LEDColor(Math.random(), 1.0, 1.0);
    }

    public RGBColor toRGBColor() {
        return toRGBColor(1.0);
    }

    public RGBColor toRGBColor(double brightnessLevel) {
        int rgbAsInt = toRGBInt(brightnessLevel);
        RGBColor rgb = new RGBColor();
        rgb.r = (byte)((rgbAsInt>>16)&0xFF);
        rgb.g = (byte)((rgbAsInt>>8)&0xFF);
        rgb.b = (byte)((rgbAsInt>>0)&0xFF);
        return rgb;
    }

    public int toRGBInt() {
        return toRGBInt(1.0);
    }

    public int toRGBInt(double brightnessLevel) {
        return Color.HSBtoRGB((float)hue, (float)saturation, (float)(brightness * brightnessLevel));
    }

    public static LEDColor copy(LEDColor color) {
        return new LEDColor(color.hue, color.saturation, color.brightness);
    }

    // TODO: does java supports it in some way?
    public void copyFromOther(LEDColor other) {
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

    public static LEDColor mixColors(LEDColor c1, double amount1, LEDColor c2, double amount2) {
        double hue = LEDColor.combineHues(c1.hue, amount1, c2.hue, amount2);
        double brightness = (c1.brightness * amount1 + c2.brightness * amount2) / (amount1 + amount2);
        double saturation = (c1.saturation * amount1 + c2.saturation * amount2) / (amount1 + amount2);
        return new LEDColor(hue, saturation, brightness);
    }

    public enum BLEND_TYPE {
        ADDITIVE,
        SUBTRACTIVE
    }
    public static LEDColor blendColors(LEDColor c1, LEDColor c2, BLEND_TYPE type) {
        if (type == BLEND_TYPE.SUBTRACTIVE) {
            RGBColor rgb1 = c1.toRGBColor();
            RGBColor rgb2 = c2.toRGBColor();

            int r = Math.min(255, Math.max(0, rgb1.r & 0xFF) + (rgb2.r & 0xFF) / 2);
            int g = Math.min(255, Math.max(0, rgb1.g & 0xFF) + (rgb2.g & 0xFF) / 2);
            int b = Math.min(255, Math.max(0, rgb1.b & 0xFF) + (rgb2.b & 0xFF) / 2);

            float[] hsbvals = new float[3];
            Color.RGBtoHSB(r, g, b, hsbvals);

            return new LEDColor(hsbvals[0], hsbvals[1], hsbvals[2]);

        } else {
            RGBColor rgb1 = c1.toRGBColor();
            RGBColor rgb2 = c2.toRGBColor();

            int r = Math.min(255, (rgb1.r & 0xFF) + (rgb2.r & 0xFF));
            int g = Math.min(255, (rgb1.g & 0xFF) + (rgb2.g & 0xFF));
            int b = Math.min(255, (rgb1.b & 0xFF) + (rgb2.b & 0xFF));

            float[] hsbvals = new float[3];
            Color.RGBtoHSB(r, g, b, hsbvals);

            return new LEDColor(hsbvals[0], hsbvals[1], hsbvals[2]);
        }
    }

    /**
     * linear averaged of two colors
     * @param c1 color1
     * @param c2 color2
     * @param ratio 0-1 ratio between c1 & c2. ratio=0 means c1, ratio=1 means c2
     * @return the new averaged color
     */
    public static LEDColor averageColors(LEDColor c1, LEDColor c2, double ratio) {
        RGBColor rgb1 = c1.toRGBColor();
        RGBColor rgb2 = c2.toRGBColor();

        int r = (int) Math.min(255, Math.max(0, Math.round(rgb1.r & 0xFF) * (1 - ratio) + (rgb2.r & 0xFF) * ratio));
        int g = (int) Math.min(255, Math.max(0, Math.round(rgb1.g & 0xFF) * (1 - ratio) + (rgb2.g & 0xFF) * ratio));
        int b = (int) Math.min(255, Math.max(0, Math.round(rgb1.b & 0xFF) * (1 - ratio) + (rgb2.b & 0xFF) * ratio));

        float[] hsbvals = new float[3];
        Color.RGBtoHSB(r, g, b, hsbvals);

        return new LEDColor(hsbvals[0], hsbvals[1], hsbvals[2]);
    }

    public static LEDColor colorCorrection(LEDColor c, ColorCorrectionValues correction) {
        RGBColor rgb = c.toRGBColor();

        int r = (int) Math.min(255, Math.max(0, Math.round(rgb.r & 0xFF) * correction.redMax));
        int g = (int) Math.min(255, Math.max(0, Math.round(rgb.g & 0xFF) * correction.greenMax));
        int b = (int) Math.min(255, Math.max(0, Math.round(rgb.b & 0xFF) * correction.blueMax));

        float[] hsbvals = new float[3];
        Color.RGBtoHSB(r, g, b, hsbvals);

        return new LEDColor(hsbvals[0], hsbvals[1], hsbvals[2]);
    }

    public static double mixHue(double h1, double h2, double level) {
        return h1 * (1 - level) + h2 * level;
    }

    public static final LEDColor WHITE = new LEDColor(0,0,1.0);
    public static final LEDColor BLACK = new LEDColor(0,0,0);
    public static final LEDColor RED = new LEDColor(0.0/3.0,1.0,1.0);
    public static final LEDColor GREEN = new LEDColor(1.0/3.0,1.0,1.0);
    public static final LEDColor BLUE = new LEDColor(2.0/3.0,1.0,1.0);
    public static final LEDColor SKYBLUE = new LEDColor(0.6,0.8,0.8);
    public static final LEDColor YELLOW = new LEDColor(1.0/6.0,1.0,1.0);
    public static final LEDColor ORANGE = new LEDColor(1.0/12.0,1.0,1.0);
    public static final LEDColor BROWN = new LEDColor(23.0/255.0,0.9,0.5);
    public static final LEDColor GRAY = new LEDColor(0.0,0.0,0.2);
    public static final LEDColor PINK = new LEDColor(0.9,1.0,1.0);
    public static final LEDColor PURPLE = new LEDColor(0.83,1.0,1.0);
}
