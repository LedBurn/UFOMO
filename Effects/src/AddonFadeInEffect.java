public class AddonFadeInEffect extends AddonEffect {

    @Override
    public void apply(LEDColor[] array, double timePercent) {

        for(LEDColor c : array) {
            c.brightness = c.brightness * timePercent;
        }
    }
}
