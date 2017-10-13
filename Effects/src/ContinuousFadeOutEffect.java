public class ContinuousFadeOutEffect extends ContinuousEffect {

    public ContinuousFadeOutEffect(ContinuousEffect sourceEffect) {
        this.sourceEffect = sourceEffect;
    }

    @Override
    public HSBColor getColor(double timePercent, double location) {
        HSBColor c = this.sourceEffect.getColor(timePercent, location);
        c.brightness = c.brightness * (1.0 - timePercent);
        return c;
    }

    private ContinuousEffect sourceEffect;
}
