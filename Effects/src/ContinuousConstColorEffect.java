public class ContinuousConstColorEffect extends ContinuousEffect {

    public ContinuousConstColorEffect(HSBColor c) {
        configure(c);
    }

    public void configure(HSBColor c) {
        this.c = c;
    }

    public HSBColor getColor(double timePercent, double location) {
        HSBColor colorCopy = new HSBColor();
        colorCopy.copyFromOther(this.c);
        return colorCopy;
    }

    HSBColor c;
}

