public class ContinuousFadeInEffect extends ContinuousEffect {

    public ContinuousFadeInEffect(ContinuousEffect sourceEffect) {
        this.sourceEffect = sourceEffect;
    }

    @Override
    public LEDColor getColor(double timePercent, double location) {
        LEDColor c = this.sourceEffect.getColor(timePercent, location);
        c.brightness = c.brightness * timePercent;
        return c;
    }

    private ContinuousEffect sourceEffect;
}
