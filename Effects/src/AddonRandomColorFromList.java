import java.util.concurrent.ThreadLocalRandom;

public class AddonRandomColorFromList extends AddonEffect {

    public AddonRandomColorFromList(LEDColor sourceColors[]) {
        this.sourceColors = sourceColors;
    }

    @Override
    public void apply(LEDColor[] array, double timePercent) {
        for(int i=0; i<array.length; i++) {
            array[i].copyFromOther(this.colors[i]);
        }
    }

    @Override
    public void setNumberOfPixels(int numberOfPixels) {
        this.colors = new LEDColor[numberOfPixels];
        for(int i=0; i < numberOfPixels; i++) {
            this.colors[i] = new LEDColor();
            this.colors[i].copyFromOther(this.sourceColors[ThreadLocalRandom.current().nextInt(this.sourceColors.length)]);
        }
    }

    private LEDColor sourceColors[];

    private LEDColor colors[];
}
