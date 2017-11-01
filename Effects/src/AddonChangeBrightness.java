public class AddonChangeBrightness extends AddonEffect {

    public AddonChangeBrightness(double newBrightness) {
        this.newBrightness = newBrightness;
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {
        for(HSBColor c : array) {
            c.brightness = this.newBrightness;
        }
    }

    private double newBrightness;
}
