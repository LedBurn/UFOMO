public class ContinuousGlowEffect extends ContinuousEffect {

    public ContinuousGlowEffect(ContinuousEffect sourceEffect) {
        this.sourceEffect = sourceEffect;
        configure(0.5, 1.0, 0.5);
    }

    public void configure(double middleLocation, double maxMidBrightness, double glowDiameter) {
        this.middleLocation = middleLocation;
        this.maxMidBrightness = maxMidBrightness;
        this.glowDiameter = glowDiameter;
    }

    @Override
    public LEDColor getColor(double timePercent, double location) {
        LEDColor c = this.sourceEffect.getColor(timePercent, location);
        double distFromMiddle = Math.abs(location - this.middleLocation);
        double middleBrightness;
        if(timePercent <= 0.5) {
            middleBrightness = this.maxMidBrightness * timePercent * 2.0;
        }
        else {
            middleBrightness = this.maxMidBrightness * (1.0 - timePercent) * 2.0;
        }
        double currBrightness = middleBrightness - (distFromMiddle / this.glowDiameter);
        c.brightness = c.brightness * Math.max(0.0, Math.min(1.0, currBrightness));
        return c;
    }

    private ContinuousEffect sourceEffect;
    private double middleLocation;
    private double maxMidBrightness;
    private double glowDiameter;
}

