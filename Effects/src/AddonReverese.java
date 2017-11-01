public class AddonReverese extends AddonEffect {

    @Override
    public void apply(HSBColor[] array, double timePercent) {
        HSBColor tempForCopy = new HSBColor();
        for(int i=0; i<array.length / 2; i++) {
            tempForCopy.copyFromOther(array[i]);
            array[i].copyFromOther(array[array.length - 1 - i]);
            array[array.length - 1 - i].copyFromOther(tempForCopy);
        }
    }
}
