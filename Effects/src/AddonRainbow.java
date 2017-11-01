public class AddonRainbow extends AddonEffect {

    @Override
    public void apply(HSBColor[] array, double timePercent) {
        for(int i=0; i<array.length; i++) {
            double locPercent = ((double) i ) / (array.length - 1);
            array[i].hue = locPercent;
            array[i].brightness = 1.0;
            array[i].saturation = 1.0;
        }
    }

}
