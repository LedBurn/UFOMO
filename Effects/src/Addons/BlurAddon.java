import java.util.concurrent.ThreadLocalRandom;

// blur for each pixel each apply
public class BlurAddon extends Addon {

    @Override
    public void change(IPixelsArray ledObject, double level) {

        for (int i=0; i<ledObject.numOfPixels(); i++) {
            double satChange = ThreadLocalRandom.current().nextDouble(-0.1, 0.1);
            double brightnessChange = ThreadLocalRandom.current().nextDouble(-0.05, 0.05);
            double hueChange = ThreadLocalRandom.current().nextDouble(-0.02, 0.02);

            HSBColor c = ledObject.getColor(i);
            c.hue += hueChange;
            c.brightness = Math.min(Math.max(c.brightness + brightnessChange, 0.0), 1.0);
            c.saturation = Math.min(Math.max(c.saturation + satChange, 0.0), 1.0);

            ledObject.setColor(i, c);
        }
    }
}
