public class ContinuousConstColorEffect extends ContinuousEffect {

    public ContinuousConstColorEffect(LEDColor c) {
        configure(c);
    }

    public void configure(LEDColor c) {
        this.c = c;
    }

    public LEDColor getColor(double timePercent, double location) {
        LEDColor colorCopy = new LEDColor();
        colorCopy.copyFromOther(this.c);
        return colorCopy;
    }

    LEDColor c;
}

