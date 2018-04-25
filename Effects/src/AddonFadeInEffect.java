public class AddonFadeInEffect extends AddonEffect {

    @Override
    public void apply(HSBColor[] array, double timePercent) {

        for(HSBColor c : array) {
            c.brightness = c.brightness * timePercent;
        }
    }
}
