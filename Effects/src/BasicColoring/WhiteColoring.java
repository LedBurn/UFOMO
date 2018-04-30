
// simple all white coloring
public class WhiteColoring extends Coloring {
    private HSBColor color;

    public WhiteColoring() {
        this.color = new HSBColor(0,0, 1.0);
    }

    @Override
    public void color(LEDObject ledObject) {
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            ledObject.setColor(i, color);
        }
    }
}
