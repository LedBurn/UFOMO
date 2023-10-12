
// simple all white coloring
public class ConstColoring extends Coloring {
    private LEDColor color;

    public ConstColoring() {
        this.color = new LEDColor(Math.random(),1.0, 1.0);
    }

    public ConstColoring(LEDColor color) {
        this.color = color;
    }

    public void changeColor(LEDColor color) {
        this.color = color;
    }

    @Override
    public void color(IPixelsArray ledObject) {
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            ledObject.setColor(i, LEDColor.copy(color));
        }
    }
}
