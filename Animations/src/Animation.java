
// This class takes a led object, colors it and apply addons.
public class Animation {

    protected LEDObject ledObject;
    private Coloring coloring;
    protected Addon[] addons;

    public Animation(LEDObject ledObject, Coloring coloring, Addon[] addons) {
        this.ledObject = ledObject;
        this.coloring = coloring;
        this.addons = addons;
    }

    public void apply(double level, boolean newBeat, boolean isOn, int[] eq) {

        // basic coloring
        if (coloring != null) {
            coloring.color(ledObject);
        }

        // add ons
        for (Addon addon : addons) {
            addon.change(ledObject, level, newBeat, isOn, eq);
        }
    }
}
