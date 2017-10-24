public class AddonFadeOut extends AddonEffect {

    public AddonFadeOut(double destBrightness) {
        this.destBrightness = destBrightness;
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {

        double factor = this.destBrightness + (1.0 - timePercent) * (1.0 - this.destBrightness);
        for(HSBColor c : array) {
            c.brightness = c.brightness * factor;
        }
    }

    private double destBrightness;
}
