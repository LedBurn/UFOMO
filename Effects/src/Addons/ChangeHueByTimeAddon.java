
// changes each pixel hue for a full hue loop
public class ChangeHueByTimeAddon extends Addon {

    private boolean reversed = false;

    public ChangeHueByTimeAddon() {

    }

    public ChangeHueByTimeAddon(boolean reversed) {
        this.reversed = reversed;
    }

    @Override
    public void change(IPixelsArray ledObject, double level, boolean isOn, boolean newBeat, int[] eq) {
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            double currHue = ledObject.getColor(i).hue;
            double newHue = reversed ? currHue - level : currHue + level;
            if (newHue + level >= 1) newHue -=1;
            ledObject.getColor(i).hue = level;
        }
    }
}
