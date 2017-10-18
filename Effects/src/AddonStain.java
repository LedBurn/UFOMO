public class AddonStain extends AddonEffect {

    public AddonStain(Double stainHue, double maxBrightness) {
        this.stainHue = stainHue;
        this.maxBrightness = maxBrightness;
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {
        for(int i=0; i<array.length; i++) {
            double relLocation = ((double)i) / (array.length - 1);
            double stainBrightness = this.getBrightness(relLocation);
            double origBrightness = array[i].brightness;
            array[i].brightness = Math.min(origBrightness + stainBrightness, 1.0);
            if(this.stainHue != null) {
                array[i].hue = this.combineHues(this.stainHue, stainBrightness, array[i].hue, origBrightness);
            }
            array[i].saturation = this.combineSat(1.0, stainBrightness, array[i].saturation, origBrightness);
        }
    }

    private double getBrightness(double relLocation) {
        double exp = (-10*(relLocation - 0.5) * (relLocation - 0.5));
        return Math.exp(exp) * this.maxBrightness;
    }

    private double combineSat(double sat1, double amount1, double sat2, double amount2) {
        return (sat1 * amount1 + sat2 * amount2) / (amount1 + amount2);
    }

    private double combineHues(double hue1, double amount1, double hue2, double amount2) {
        double x = amount1 * Math.cos(hue1 * 2 * Math.PI) + amount2 * Math.cos(hue2 * 2 * Math.PI);
        double y = amount1 * Math.sin(hue1 * 2 * Math.PI) + amount2 * Math.sin(hue2 * 2 * Math.PI);
        double combinesAngle = Math.atan2(y, x);
        double averagePercent = combinesAngle / ( 2.0 * Math.PI);
        return averagePercent % 1.0;
    }

    private Double stainHue;
    private double maxBrightness;

}
