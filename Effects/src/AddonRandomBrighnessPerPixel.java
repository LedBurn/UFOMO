import java.util.concurrent.ThreadLocalRandom;

public class AddonRandomBrighnessPerPixel extends AddonEffect {

    @Override
    public void apply(LEDColor[] array, double timePercent) {
        for(int i=0; i<array.length; i++) {
            array[i].brightness = this.brightness[i];
        }
    }

    @Override
    public void setNumberOfPixels(int numberOfPixels) {
        brightness = new double[numberOfPixels];
        for(int i=0; i < numberOfPixels; i++) {
            this.brightness[i] = ThreadLocalRandom.current().nextDouble();
        }
    }

    private double brightness[];

}
