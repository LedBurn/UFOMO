public class AlternateColoring extends Coloring {

    private HSBColor color1;
    private HSBColor color2;
    private int numOfPixels;

    public AlternateColoring() {
        this.color1 = new HSBColor(Math.random(),1.0, 1.0);
        this.color2 = new HSBColor(color1.hue + 0.5,1.0, 1.0);
        this.numOfPixels = 5;
    }

    public AlternateColoring(HSBColor color1) {
        this.color1 = color1;
        this.color2 = new HSBColor(color1.hue + 0.5,1.0, 1.0);
        this.numOfPixels = 5;
    }

    public AlternateColoring(HSBColor color1, HSBColor color2, int numOfPixels) {
        this.color1 = color1;
        this.color2 = color2;
        this.numOfPixels = numOfPixels;
    }

    @Override
    public void color(LEDObject ledObject) {
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            if ((i % numOfPixels) % 2 == 0) {
                ledObject.setColor(i, color1);
            } else {
                ledObject.setColor(i, color2);
            }
        }
    }
}
