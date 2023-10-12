/*
clear side to side effect.
this effect will clear the array (by setting the brightness to 0.0, starting from the first index when
time percent = 0.0, and up until the last index when timePercent = 1.0
 */
public class AddonClearS2SEffect extends AddonEffect {

    @Override
    public void apply(LEDColor[] array, double timePercent) {

        int clearIndex = (int)(timePercent * array.length);
        for(int i=0; i < clearIndex; i++) {
            array[i].brightness = 0.0;
        }
    }
}
