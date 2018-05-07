// show only the first leds accrdoing to the eq
public class EqAddon extends Addon {

    private int keepMax = 0;

    @Override
    public void change(LEDObject ledObject, double level, boolean newBeat, boolean isOn, int[] eq) {
        int index = (int)Math.floor(ledObject.numOfPixels() * eq[7] / 128.0);
        if (index >= keepMax) {
            keepMax = index;
        } else {
            keepMax -= 1;
        }
        index = Math.max(index, keepMax);
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            if (i>=index) {
                HSBColor color = ledObject.getColor(i);
                // TODO - lower it;
                color.brightness *= 0.6;
            }
        }
    }
}
