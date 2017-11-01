public class ContinuousSegmentEffect extends ContinuousEffect {

    public ContinuousSegmentEffect(ContinuousEffect sourceEffect) {
        this.sourceEffect = sourceEffect;
        this.configure(0.4, 0.6);
    }

    public void configure(double locStart, double locEnd) {
        //TODO: check that hueEnd >= hueStart
        this.locStart = locStart;
        this.locEnd = locEnd;
    }

    public HSBColor getColor(double timePercent, double location) {
        HSBColor origColor = sourceEffect.getColor(timePercent, location);
        if(location < this.locStart || location > this.locEnd) {
            origColor.brightness = 0.0;
        }
        return origColor;
    }

    private ContinuousEffect sourceEffect;

    private double locStart, locEnd;
}
