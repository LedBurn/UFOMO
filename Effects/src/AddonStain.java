public class AddonStain extends AddonEffect {

    public AddonStain(Double stainHue, double maxBrightness, double stainSaturation) {
        this.stainHue = stainHue;
        this.maxBrightness = maxBrightness;
        this.stainSaturation = stainSaturation;
    }

    @Override
    public void apply(LEDColor[] array, double timePercent) {
        for(int i=0; i<array.length; i++) {
            double relLocation = ((double)i) / (array.length - 1);
            double stainBrightness = this.getBrightness(relLocation);
            double origBrightness = array[i].brightness;
            array[i].brightness = Math.min(origBrightness + stainBrightness, 1.0);
            if(this.stainHue != null) {
                array[i].hue = LEDColor.combineHues(this.stainHue, stainBrightness * 20, array[i].hue, origBrightness);
            }
            array[i].saturation = this.combineSat(this.stainSaturation, stainBrightness, array[i].saturation, origBrightness);
        }
    }

    private double getBrightness(double relLocation) {
        double locToCenter = 2 * (relLocation - 0.5);
        double exp = (-10 * locToCenter * locToCenter);
        return Math.exp(exp) * this.maxBrightness;
    }

    private double combineSat(double sat1, double amount1, double sat2, double amount2) {
        return (sat1 * amount1 + sat2 * amount2) / (amount1 + amount2);
    }

    private Double stainHue;
    private double maxBrightness;
    private double stainSaturation;

}
