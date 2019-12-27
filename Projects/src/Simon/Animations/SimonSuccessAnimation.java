public class SimonSuccessAnimation extends SimonAnimation {

    Coloring coloring;
    Addon addon;

    public SimonSuccessAnimation() {
        super(1000);
        coloring = Coloring.RAINBOW_COLORING;
        addon = new ChangeHueByTimeAddon();
    }

    @Override
    public void apply(SignLEDObject sign, double timePercent) {
        for (IPixelsArray finger: sign.gameFingers) {
            coloring.color(finger);
            addon.change(finger, timePercent);
        }
    }
}
