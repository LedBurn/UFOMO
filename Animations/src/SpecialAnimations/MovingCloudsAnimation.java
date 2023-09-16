public class MovingCloudsAnimation extends PixelsArrayAnimation {

    public MovingCloudsAnimation(IPixelsArray ledObject, Coloring coloring, Addon[] addons) {
        this(ledObject);
    }

    public MovingCloudsAnimation(IPixelsArray ledObject) {
        super(ledObject, null, new Addon[]{});
    }

    public void apply(double level, boolean newBeat, boolean isOn, int[] eq) {

    }
}
