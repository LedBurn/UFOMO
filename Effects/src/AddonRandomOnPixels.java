import java.util.concurrent.ThreadLocalRandom;

public class AddonRandomOnPixels extends AddonEffect {

    public AddonRandomOnPixels(double onPercent) {
        this.onPercent = onPercent;
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {
        for(int i=0; i<array.length; i++) {
            if(this.isOn[i] == false) {
                array[i].brightness = 0.0;
            }
        }
    }

    public void setNumberOfPixels(int numberOfPixels) {
        isOn = new boolean[numberOfPixels];
        for(int i=0; i < numberOfPixels; i++) {
            isOn[i] = (ThreadLocalRandom.current().nextDouble() < this.onPercent);
        }
    }

    private boolean isOn[];
    private double onPercent;
}
