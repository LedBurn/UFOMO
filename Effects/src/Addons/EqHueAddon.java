
// changes the hue
public class EqHueAddon extends Addon {

    private double keepMax = 1.0;
    private int eqIndex = 7;

    public EqHueAddon() {
    }

    public EqHueAddon(int eqIndex) {
        this.eqIndex = eqIndex;
    }

    @Override
    public void change(LEDObject ledObject, double level, boolean newBeat, boolean isOn, int[] eq) {
        double hue =  eq[eqIndex] / 128.0;
        if (hue >= keepMax) {
            keepMax = hue;
        } else {
            keepMax = Math.max(0, keepMax - 0.03);
        }
        hue = Math.max(hue, keepMax);
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            HSBColor color = ledObject.getColor(i);
            HSBColor newColor = new HSBColor(color.hue + hue, color.saturation, color.brightness);
            ledObject.setColor(i, newColor);
        }
    }
}
