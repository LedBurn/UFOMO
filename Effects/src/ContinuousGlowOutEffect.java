public class ContinuousGlowOutEffect extends ContinuousEffect {

    public ContinuousGlowOutEffect(ContinuousEffect sourceEffect) {
        this.sourceEffect = sourceEffect;
        configure(0.5);
    }

    public void configure(double middleLocation) {
        this.middleLocation = middleLocation;
    }

    @Override
    public HSBColor getColor(double timePercent, double location) {
        HSBColor c = this.sourceEffect.getColor(timePercent, location);
        double distFromMiddle = Math.abs(location - this.middleLocation);
        double middleBrightness = (1.0 - timePercent) * 2.0;
        c.brightness = c.brightness * Math.max(0.0, Math.min(1.0, middleBrightness - distFromMiddle));
        return c;
    }

    private ContinuousEffect sourceEffect;
    private double middleLocation;
}
