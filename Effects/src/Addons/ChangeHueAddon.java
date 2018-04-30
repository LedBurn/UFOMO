
// changes each pixel hue for a full hue loop
public class ChangeHueAddon extends Addon {

    @Override
    public void change(LEDObject ledObject, double level) {
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            double currHue = ledObject.getColor(i).hue;
            double newHue = currHue + level;
            if (newHue + level >= 1) newHue -=1;
            ledObject.getColor(i).hue = level;
        }
    }
}
