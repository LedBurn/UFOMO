
// full hue circle coloring
public class RainbowColoring extends Coloring {

    private boolean reversed = false;

    public RainbowColoring(boolean reversed) {
        this.reversed = reversed;
    }

    @Override
    public void color(IPixelsArray ledObject) {
        for (int i = 0; i < ledObject.numOfPixels(); i++) {

            double hue = (double)i / ledObject.numOfPixels();
            if (reversed) hue = 1 - hue;
            HSBColor color = new HSBColor(hue, 1.0, 1.0);
            ledObject.setColor(i, color);
        }
    }
}
