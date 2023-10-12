public class AddonSetConstColor extends AddonEffect {

    public AddonSetConstColor(LEDColor c) {
        this.c = c;
    }

    @Override
    public void apply(LEDColor[] array, double timePercent) {
        for(LEDColor currColor: array) {
            currColor.copyFromOther(this.c);
        }
    }

    private LEDColor c;

}
