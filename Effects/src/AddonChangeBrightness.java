public class AddonChangeBrightness extends AddonEffect {

    public AddonChangeBrightness(double newBrightness) {
        this.newBrightness = newBrightness;
    }

    @Override
    public void apply(LEDColor[] array, double timePercent) {
        for(LEDColor c : array) {
            c.brightness = this.newBrightness;
        }
    }

    private double newBrightness;
}
