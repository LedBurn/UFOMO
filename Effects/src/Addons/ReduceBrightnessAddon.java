public class ReduceBrightnessAddon extends Addon {

    private double minLevel = 0.2;

    @Override
    public void change(IPixelsArray ledObject, double level) {

        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            ledObject.getColor(i).brightness = Math.max(0.2, 1.0 - Math.pow(level, 3));
        }
    }
}
