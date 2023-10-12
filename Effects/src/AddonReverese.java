public class AddonReverese extends AddonEffect {

    @Override
    public void apply(LEDColor[] array, double timePercent) {
        LEDColor tempForCopy = new LEDColor();
        for(int i=0; i<array.length / 2; i++) {
            tempForCopy.copyFromOther(array[i]);
            array[i].copyFromOther(array[array.length - 1 - i]);
            array[array.length - 1 - i].copyFromOther(tempForCopy);
        }
    }
}
