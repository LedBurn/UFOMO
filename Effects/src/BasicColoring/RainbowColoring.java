
// full hue circle coloring
public class RainbowColoring extends Coloring {

    @Override
    public void color(LEDObject ledObject) {
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            HSBColor color = new HSBColor((double)i / ledObject.numOfPixels(), 1.0, 1.0);
            ledObject.setColor(i, color);
        }
    }
}
