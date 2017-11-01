public class AddonChangeHue extends AddonEffect {

    public AddonChangeHue(double startHue, double endHue) {
        this.startHue = startHue;
        this.endHue = endHue;
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {

        double currHue = this.combineHues(this.startHue, 1.0 - timePercent, this.endHue, timePercent);

        for(HSBColor c: array) {
            c.hue = currHue;
            c.brightness = 1.0;
            c.saturation = 1.0;
        }
    }

    private double combineHues(double hue1, double amount1, double hue2, double amount2) {
        double x = amount1 * Math.cos(hue1 * 2 * Math.PI) + amount2 * Math.cos(hue2 * 2 * Math.PI);
        double y = amount1 * Math.sin(hue1 * 2 * Math.PI) + amount2 * Math.sin(hue2 * 2 * Math.PI);
        double combinesAngle = Math.atan2(y, x);
        double averagePercent = combinesAngle / ( 2.0 * Math.PI);
        return averagePercent % 1.0;
    }

    private double startHue, endHue;
}
