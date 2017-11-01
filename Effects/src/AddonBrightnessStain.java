public class AddonBrightnessStain extends AddonEffect {

    @Override
    public void apply(HSBColor[] array, double timePercent) {
        for(int i=0; i<array.length; i++) {

            double relLocation = ((double) i) / (array.length - 1);
            array[i].brightness = array[i].brightness * this.getBrightness(relLocation);
        }
    }

    private double getBrightness(double relLocation) {
        double exp = (-10*(relLocation - 0.5) * (relLocation - 0.5));
        return Math.exp(exp);
    }

}
