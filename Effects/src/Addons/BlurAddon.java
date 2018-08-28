import java.util.concurrent.ThreadLocalRandom;

// blur for each pixel each apply
public class BlurAddon extends Addon {

    @Override
    public void change(IPixelsArray ledObject, double level, boolean newBeat, boolean isOn, int[] eq) {

        for (int i=0; i<ledObject.numOfPixels(); i++) {
            double satChange = ThreadLocalRandom.current().nextDouble(-0.1, 0.1);
            double brightnessChange = ThreadLocalRandom.current().nextDouble(-0.05, 0.05);
            double hueChange = ThreadLocalRandom.current().nextDouble(-0.02, 0.02);

            HSBColor c = new HSBColor();
            c.hue += hueChange;
            c.brightness = Math.min(Math.max(c.brightness + brightnessChange, 0.0), 1.0);
            c.saturation = Math.min(Math.max(c.saturation + satChange, 0.0), 1.0);

            ledObject.setColor(i, c);
        }
    }
}
