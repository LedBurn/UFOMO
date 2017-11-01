import sun.nio.cs.ext.MacHebrew;

import java.util.concurrent.ThreadLocalRandom;

public class AddonBlur extends AddonEffect {

    @Override
    public void setNumberOfPixels(int numberOfPixels) {

        this.satChange = new double[numberOfPixels];
        this.hueChange = new double[numberOfPixels];
        this.brightnessChange = new double[numberOfPixels];

        for(int i=0; i<numberOfPixels; i++) {
            this.satChange[i] = ThreadLocalRandom.current().nextDouble(-0.1, 0.1);
            this.brightnessChange[i] = ThreadLocalRandom.current().nextDouble(-0.05, 0.05);
            this.hueChange[i] = ThreadLocalRandom.current().nextDouble(-0.02, 0.02);
        }
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {
        for(int i=0; i<array.length; i++) {
            HSBColor c = array[i];
            c.hue += hueChange[i];
            c.brightness = Math.min(Math.max(c.brightness + brightnessChange[i], 0.0), 1.0);
            c.saturation = Math.min(Math.max(c.saturation + satChange[i], 0.0), 1.0);
        }
    }

    private double satChange[];
    private double hueChange[];
    private double brightnessChange[];
}
