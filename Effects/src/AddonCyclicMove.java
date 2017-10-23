public class AddonCyclicMove extends AddonEffect {

    @Override
    public void setNumberOfPixels(int numberOfPixels) {
        this.arrayForCopy = new HSBColor[numberOfPixels];
        for(int i=0; i < numberOfPixels; i++) {
            this.arrayForCopy[i] = new HSBColor();
        }
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {

        for(int i=0; i<arrayForCopy.length; i++) {
            arrayForCopy[i].copyFromOther(array[i]);
        }

        int numberOfPixels = (int)(timePercent * array.length);
        for(int i=0; i<array.length; i++) {
            int srcIndex = (i + numberOfPixels) % array.length;
            array[i].copyFromOther(arrayForCopy[srcIndex]);
        }
    }

    private HSBColor arrayForCopy[];

}
