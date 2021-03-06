
// simple all white coloring
public class ConstColoring extends Coloring {
    private HSBColor color;

    public ConstColoring() {
        this.color = new HSBColor(Math.random(),1.0, 1.0);
    }

    public ConstColoring(HSBColor color) {
        this.color = color;
    }

    public void changeColor(HSBColor color) {
        this.color = color;
    }

    @Override
    public void color(IPixelsArray ledObject) {
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            ledObject.setColor(i, HSBColor.copy(color));
        }
    }
}
