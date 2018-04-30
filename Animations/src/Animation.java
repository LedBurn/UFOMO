
// This class takes a led object, colors it and apply addons.
public class Animation {

    private LEDObject ledObject;
    private Coloring coloring;
    private Addon[] addons;

    public Animation(LEDObject ledObject, Coloring coloring, Addon[] addons) {
        this.ledObject = ledObject;
        this.coloring = coloring;
        this.addons = addons;
    }

    public void apply(double level) {

        // basic coloring
        coloring.color(ledObject);

        // add ons
        for (Addon addon : addons) {
            addon.change(ledObject, level);
        }
    }
}
