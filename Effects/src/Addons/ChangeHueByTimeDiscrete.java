
// changes each pixel hue for a full hue loop
public class ChangeHueByTimeDiscrete extends Addon {

    private boolean reversed = false;
    private int numOfJumps = 8;
    private double startingHue = 0;


    public ChangeHueByTimeDiscrete(double startingHue, int numOfJumps ,boolean reversed) {
        this.startingHue = startingHue;
        this.numOfJumps = numOfJumps;
        this.reversed = reversed;;
    }

    @Override
    public void change(IPixelsArray ledObject, double level) {
        int jump = (int)Math.floor(level * numOfJumps);
        double hue = startingHue + jump / (double) numOfJumps;
        LEDColor color = new LEDColor(hue, 1, 1);
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            ledObject.setColor(i, color);
        }
    }
}

