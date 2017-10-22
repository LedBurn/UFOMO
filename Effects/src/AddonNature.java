
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
    public void apply(HSBColor[] array, double timePercent) {
        int brownToGreen = array.length / 6;
        int greenToBlue = array.length * 5 / 6;
        for(int i=0; i < brownToGreen; i++) {
            array[i].copyFromOther(HSBColor.BROWN);
        }

        array[brownToGreen + 0].copyFromOther(HSBColor.mixColors(HSBColor.BROWN, 0.75, HSBColor.GREEN, 0.25));
        array[brownToGreen + 1].copyFromOther(HSBColor.mixColors(HSBColor.BROWN, 0.5, HSBColor.GREEN, 0.5));
        array[brownToGreen + 2].copyFromOther(HSBColor.mixColors(HSBColor.BROWN, 0.25, HSBColor.GREEN, 0.75));

        for(int i=brownToGreen + 3; i < greenToBlue; i++) {
            array[i].copyFromOther(HSBColor.GREEN);
        }

        array[greenToBlue + 0].copyFromOther(HSBColor.mixColors(HSBColor.GREEN, 0.75, HSBColor.SKYBLUE, 0.25));
        array[greenToBlue + 1].copyFromOther(HSBColor.mixColors(HSBColor.GREEN, 0.5, HSBColor.SKYBLUE, 0.5));
        array[greenToBlue + 2].copyFromOther(HSBColor.mixColors(HSBColor.GREEN, 0.25, HSBColor.SKYBLUE, 0.75));

        for(int i=greenToBlue + 3; i < array.length; i++) {
            array[i].copyFromOther(HSBColor.SKYBLUE);
        }
    }


}
