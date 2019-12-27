public class SimonFailedAnimation extends SimonAnimation {

    Coloring coloring;
    Addon addon;

    public SimonFailedAnimation() {
        super(2000);
        coloring = new ConstColoring(HSBColor.RED);
        addon = new BlurAddon();
    }

    @Override
    public void apply(SignLEDObject sign, double timePercent) {
        for (IPixelsArray finger: sign.gameFingers) {
            coloring.color(finger);
            addon.change(finger, timePercent);
        }
    }
}
