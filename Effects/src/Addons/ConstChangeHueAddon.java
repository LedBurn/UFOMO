
// changes all pixels hue - const
public class ConstChangeHueAddon extends Addon {

    private double diff = Math.random();

    public ConstChangeHueAddon() {

    }

    public ConstChangeHueAddon(double diff) {
        this.diff = diff;
    }

    @Override
    public void change(IPixelsArray ledObject, double level, boolean newBeat, boolean isOn, int[] eq) {
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            double currHue = ledObject.getColor(i).hue;
            double newHue = currHue + diff;
            ledObject.getColor(i).hue = newHue;
        }
    }
}
