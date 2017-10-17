import java.util.concurrent.ThreadLocalRandom;

public class AddonRandomColorFromList extends AddonEffect {

    public AddonRandomColorFromList(HSBColor sourceColors[]) {
        this.sourceColors = sourceColors;
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {
        this.setColors(array.length);
        for(int i=0; i<array.length; i++) {
            array[i].copyFromOther(this.colors[i]);
        }
    }

    private void setColors(int numOfPixels) {
        if(this.colors == null) {
            this.colors = new HSBColor[numOfPixels];
            for(int i=0; i<numOfPixels; i++) {
                this.colors[i] = new HSBColor();
                this.colors[i].copyFromOther(this.sourceColors[ThreadLocalRandom.current().nextInt(this.sourceColors.length)]);
            }
        }
    }

    private HSBColor sourceColors[];

    private HSBColor colors[];
}
