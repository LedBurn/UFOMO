
// This class takes a led object, colors it and apply addons.
public class PixelsArrayAnimation {

    protected IPixelsArray ledObject;
    private Coloring coloring;
    protected Addon[] addons;
    private int speed = 1;

    private int cycleNum = 0;
    private double lastLevel = 0;

    public PixelsArrayAnimation(IPixelsArray ledObject, Coloring coloring, Addon[] addons) {
        this.ledObject = ledObject;
        this.coloring = coloring;
        this.addons = addons;
    }

    public PixelsArrayAnimation(IPixelsArray ledObject, Coloring coloring, Addon[] addons, int speed) {
        this.ledObject = ledObject;
        this.coloring = coloring;
        this.addons = addons;
        this.speed = speed;
    }

    public void apply(double level, boolean newBeat, boolean isOn, int[] eq) {
        if (level < lastLevel) cycleNum++;
        if (cycleNum == speed) cycleNum = 0;
        double newLevel = (level + cycleNum) / speed;

        // basic coloring
        if (coloring != null) {
            coloring.color(ledObject);
        }

        // add ons
        for (Addon addon : addons) {
            addon.change(ledObject, newLevel, newBeat, isOn, eq);
        }

        lastLevel = level;
    }
}
