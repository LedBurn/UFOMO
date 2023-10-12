
/*
Will create a canvas which is brawn at the bottom, green at the center, and blue at the top.
Meant to look like a jungle (ground, forest, sky).
The connection points should be changed in a small blend
 */
public class AddonNature extends AddonEffect {

    @Override
    public void setNumberOfPixels(int numberOfPixels) {
    }

    @Override
    public void apply(LEDColor[] array, double timePercent) {
        int brownToGreen = array.length / 6;
        int greenToBlue = array.length * 5 / 6;
        for(int i=0; i < brownToGreen; i++) {
            array[i].copyFromOther(LEDColor.BROWN);
        }

        array[brownToGreen + 0].copyFromOther(LEDColor.mixColors(LEDColor.BROWN, 0.75, LEDColor.GREEN, 0.25));
        array[brownToGreen + 1].copyFromOther(LEDColor.mixColors(LEDColor.BROWN, 0.5, LEDColor.GREEN, 0.5));
        array[brownToGreen + 2].copyFromOther(LEDColor.mixColors(LEDColor.BROWN, 0.25, LEDColor.GREEN, 0.75));

        for(int i=brownToGreen + 3; i < greenToBlue; i++) {
            array[i].copyFromOther(LEDColor.GREEN);
        }

        array[greenToBlue + 0].copyFromOther(LEDColor.mixColors(LEDColor.GREEN, 0.75, LEDColor.SKYBLUE, 0.25));
        array[greenToBlue + 1].copyFromOther(LEDColor.mixColors(LEDColor.GREEN, 0.5, LEDColor.SKYBLUE, 0.5));
        array[greenToBlue + 2].copyFromOther(LEDColor.mixColors(LEDColor.GREEN, 0.25, LEDColor.SKYBLUE, 0.75));

        for(int i=greenToBlue + 3; i < array.length; i++) {
            array[i].copyFromOther(LEDColor.SKYBLUE);
        }
    }


}
