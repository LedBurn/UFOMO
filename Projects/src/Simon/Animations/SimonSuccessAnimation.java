public class SimonSuccessAnimation extends SimonAnimation {

    Coloring coloring;
    Addon addon;

    public SimonSuccessAnimation() {
        super(2500);
        coloring = Coloring.RAINBOW_COLORING;
        addon = new ChangeHueByTimeAddon();
    }

    @Override
    public void apply(SignLEDObject sign, double timePercent) {
        for (IPixelsArray obj : sign.all) {
            Coloring.RAINBOW_COLORING.color(obj);
        }

        for (IPixelsArray obj : sign.hand) {
            Coloring.GRAY_COLORING.color(obj);
        }

        for (IPixelsArray palmObj : sign.palm) {
            coloring.color(palmObj);
            addon.change(palmObj, timePercent);
        }
        coloring.color(sign.finger1);
        addon.change(sign.finger1, timePercent);

        coloring.color(sign.finger4);
        addon.change(sign.finger4, timePercent);

        coloring.color(sign.finger5);
        addon.change(sign.finger5, timePercent);

        for (IPixelsArray obj : sign.campledburn) {
            coloring.color(obj);
            addon.change(obj, timePercent);
        }
    }
}
