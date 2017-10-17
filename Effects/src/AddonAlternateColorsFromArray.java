public class AddonAlternateColorsFromArray extends AddonEffect {

    public AddonAlternateColorsFromArray(HSBColor colors[], int pixelsPerSegment) {
        this.colors = colors;
        this.pixelsPerSegment = pixelsPerSegment;
    }

    private HSBColor colors[];
    private int pixelsPerSegment;

    @Override
    public void apply(HSBColor[] array, double timePercent) {

        for(int i=0; i<array.length; i++) {
            int currColorIndex = (i / this.pixelsPerSegment) % this.colors.length;
            array[i].copyFromOther(this.colors[currColorIndex]);
        }

    }
}
