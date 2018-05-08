
// Changes all the brightness
public class EqConstAddon extends Addon {

    private double keepMax = 1.0;
    private int eqIndex = 7;

    public EqConstAddon() {
    }

    public EqConstAddon(int eqIndex) {
        this.eqIndex = eqIndex;
    }

    @Override
    public void change(LEDObject ledObject, double level, boolean newBeat, boolean isOn, int[] eq) {
        double brightness =  eq[eqIndex] / 128.0;
        if (brightness >= keepMax) {
            keepMax = brightness;
        } else {
            keepMax = Math.max(0.2, keepMax - 0.01);
        }
        brightness = Math.max(brightness, keepMax);
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            HSBColor color = ledObject.getColor(i);
            color.brightness = brightness;
        }
    }
}


