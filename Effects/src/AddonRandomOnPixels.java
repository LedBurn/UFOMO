import java.util.concurrent.ThreadLocalRandom;

public class AddonRandomOnPixels extends AddonEffect {

    public AddonRandomOnPixels(double onPercent) {
        this.onPercent = onPercent;
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {
        this.initializeOnPixels(array.length);
        for(int i=0; i<array.length; i++) {
            if(this.isOn[i] == false) {
                array[i].brightness = 0.0;
            }
        }
    }

    private void initializeOnPixels(int numOfPixels) {
        if(isOn == null) {
            isOn = new boolean[numOfPixels];
            for(int i=0; i<numOfPixels; i++) {
                isOn[i] = (ThreadLocalRandom.current().nextDouble() < this.onPercent);
            }
        }
    }

    private boolean isOn[];
    private double onPercent;
}
