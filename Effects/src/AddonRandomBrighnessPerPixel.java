import java.util.concurrent.ThreadLocalRandom;

public class AddonRandomBrighnessPerPixel extends AddonEffect {

    @Override
    public void apply(HSBColor[] array, double timePercent) {
        this.checkBrightnessArray(array.length);
        for(int i=0; i<array.length; i++) {
            array[i].brightness = this.brightness[i];
        }
    }

    private void checkBrightnessArray(int numOfPixels) {
        if(this.brightness == null) {
            brightness = new double[numOfPixels];
            for(int i=0; i<numOfPixels; i++) {
                this.brightness[i] = ThreadLocalRandom.current().nextDouble();
                System.out.println(this.brightness[i]);
            }
        }
    }

    private double brightness[];

}
