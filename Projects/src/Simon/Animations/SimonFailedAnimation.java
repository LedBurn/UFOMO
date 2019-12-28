import com.sun.tools.javac.util.ArrayUtils;

public class SimonFailedAnimation extends SimonAnimation {

    Coloring coloring;
    Coloring nocoloring;
    Addon addon;

    public SimonFailedAnimation() {
        super(2500);
        coloring = new ConstColoring(HSBColor.RED);
        nocoloring = new ConstColoring(HSBColor.BLACK);
        addon = new BlurAddon();
    }

    @Override
    public void apply(SignLEDObject sign, double timePercent) {
        for (IPixelsArray obj : sign.all) {
            nocoloring.color(obj);
        }


        for (IPixelsArray palmObj : sign.palm) {
            coloring.color(palmObj);
            addon.change(palmObj, timePercent);
        }
        coloring.color(sign.finger3);
        addon.change(sign.finger3, timePercent);
    }
}
