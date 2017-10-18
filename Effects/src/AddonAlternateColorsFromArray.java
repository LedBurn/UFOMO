public class AddonAlternateColorsFromArray extends AddonEffect {

    /*
    numberOfChanges is the number of times that the colors change there position.
    if you choose 0, they will stay in place during the entire time.
    you will probably want to numberOfChanges to be a multiply of colors.length because its pretty, but you don't have to.
     */
    public AddonAlternateColorsFromArray(HSBColor colors[], int pixelsPerSegment, int numberOfChanges) {
        this.colors = colors;
        this.pixelsPerSegment = pixelsPerSegment;
        this.numberOfChanges = numberOfChanges;
    }

    private HSBColor colors[];
    private int pixelsPerSegment;
    private int numberOfChanges;

    @Override
    public void apply(HSBColor[] array, double timePercent) {

        int colorsMovementFactor = (int) (timePercent * this.numberOfChanges);

        for(int i=0; i<array.length; i++) {
            int currColorIndex = (i / this.pixelsPerSegment) % this.colors.length;
            int indexAfterMovement = (currColorIndex + colorsMovementFactor) % this.colors.length;
            array[i].copyFromOther(this.colors[indexAfterMovement]);
        }

    }
}
