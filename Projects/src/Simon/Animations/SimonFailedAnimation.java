public class SimonFailedAnimation extends SimonAnimation {

    Coloring coloring;
    Addon addon;

    public SimonFailedAnimation() {
        super(2500);
        coloring = new ConstColoring(LEDColor.RED);
        addon = new BlurAddon();
    }

    @Override
    public void apply(SignLEDObject sign, double timePercent) {
        for (IPixelsArray obj : sign.all) {
            Coloring.GRAY_COLORING.color(obj);
        }

        for (IPixelsArray palmObj : sign.palm) {
            coloring.color(palmObj);
            addon.change(palmObj, timePercent);
        }
        coloring.color(sign.finger3);
        addon.change(sign.finger3, timePercent);
    }
}
