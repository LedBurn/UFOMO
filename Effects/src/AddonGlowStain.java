/*
will create a stain over the buffer array.
the stain will have a bright center, and darker margins.
It will start to glow on time 0.0, be max bright at 0.25 until 0.75, and the fade until 1.0.
It can be set on top of other colors. in that case, the colors will blend nicely
*/
public class AddonGlowStain extends AddonEffect {

    public AddonGlowStain(Double stainHue, double maxBrightness) {
        this.stainHue = stainHue;
        this.maxBrightness = maxBrightness;
    }

    @Override
    public void apply(LEDColor[] array, double timePercent) {
        for(int i=0; i<array.length; i++) {
            double relLocation = ((double)i) / (array.length - 1);
            double stainBrightness = this.getBrightness(timePercent, relLocation);
            double origBrightness = array[i].brightness;
            array[i].brightness = Math.min(origBrightness + stainBrightness, 1.0);
            if(this.stainHue != null) {
                array[i].hue = LEDColor.combineHues(this.stainHue, stainBrightness * 2, array[i].hue, origBrightness);
            }
            array[i].saturation = this.combineSat(1.0, stainBrightness, array[i].saturation, origBrightness);
        }
    }

    private double combineSat(double sat1, double amount1, double sat2, double amount2) {
        return (sat1 * amount1 + sat2 * amount2) / (amount1 + amount2);
    }

    private double getBrightness(double timePercent, double relLocation) {
        double exp = (-10*(relLocation - 0.5) * (relLocation - 0.5));
        double fullBrightness = Math.exp(exp) * this.maxBrightness;
        if(timePercent < 0.25) {
            return fullBrightness * (timePercent / 0.25);
        }
        else if (timePercent > 0.75) {
            return fullBrightness * ((1.0 - timePercent) / 0.25);
        }
        return fullBrightness;
    }

    private Double stainHue;
    private double maxBrightness;
}
