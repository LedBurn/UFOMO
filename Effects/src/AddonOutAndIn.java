public class AddonOutAndIn extends AddonEffect {

    public AddonOutAndIn(double minBrightness) {
        this.minBrightness = minBrightness;
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {

        double factor;
        if(timePercent < 0.5) {
            factor = (1.0 - (timePercent * 2.0));
        }
        else {
            factor = (1.0 - ((1.0 - timePercent) * 2.0));
        }
        double brightnessMul = this.minBrightness + factor * (1.0 - this.minBrightness);
        for(HSBColor c : array) {
            c.brightness = c.brightness * brightnessMul;
        }
    }

    private double minBrightness;

}
