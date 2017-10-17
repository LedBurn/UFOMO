public class AddonCyclicMove extends AddonEffect {

    @Override
    public void apply(HSBColor[] array, double timePercent) {

        // create a copy. i'm sure there is a cleaner way to do it :)
        HSBColor arrayForCopy[] = new HSBColor[array.length];
        for(int i=0; i<arrayForCopy.length; i++) {
            arrayForCopy[i] = new HSBColor();
            arrayForCopy[i].copyFromOther(array[i]);
        }

        int numberOfPixels = (int)(timePercent * array.length);
        for(int i=0; i<array.length; i++) {
            int srcIndex = (i + numberOfPixels) % array.length;
            array[i].copyFromOther(arrayForCopy[srcIndex]);
        }
    }

}
