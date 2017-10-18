public class AddonFadeOut extends AddonEffect {
    @Override
    public void apply(HSBColor[] array, double timePercent) {

        for(HSBColor c : array) {
            c.brightness = c.brightness * (1.0 - timePercent);
        }
    }
}
