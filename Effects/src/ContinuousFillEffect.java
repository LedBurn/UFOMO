public class ContinuousFillEffect extends ContinuousEffect {

    public ContinuousFillEffect(ContinuousEffect sourceEffect) {
        this.sourceEffect = sourceEffect;
        configure(0.0, 1.0);
    }

    public void configure(double locFrom, double locTo) {
        this.locFrom = locFrom;
        this.locTo = locTo;
    }

    @Override
    public HSBColor getColor(double timePercent, double location) {
        HSBColor c = this.sourceEffect.getColor(timePercent, location);

        double lowerBound, upperBound;
        if(this.locFrom < this.locTo) {
            lowerBound = this.locFrom;
            upperBound = this.locFrom + (this.locTo - this.locFrom) * timePercent;
        }
        else {
            lowerBound = this.locFrom - (this.locFrom - this.locTo) * timePercent;
            upperBound = this.locFrom;
        }

        if(location < lowerBound || location > upperBound) {
            c.brightness = 0.0;
        }
        return c;
    }

    private ContinuousEffect sourceEffect;
    private double locFrom, locTo;
}

