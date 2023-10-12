public class AlternateColoring extends Coloring {

    private LEDColor color1;
    private LEDColor color2;
    private int numOfPixels;

    public AlternateColoring() {
        this.color1 = new LEDColor(Math.random(),1.0, 1.0);
        this.color2 = new LEDColor(color1.hue + 0.5,1.0, 1.0);
        this.numOfPixels = 5;
    }

    public AlternateColoring(LEDColor color1) {
        this.color1 = color1;
        this.color2 = new LEDColor(color1.hue + 0.5,1.0, 1.0);
        this.numOfPixels = 5;
    }

    public AlternateColoring(LEDColor color1, int numOfPixels) {
        this.color1 = color1;
        this.color2 = new LEDColor(color1.hue + 0.5,1.0, 1.0);
        this.numOfPixels = numOfPixels;
    }

    public AlternateColoring(LEDColor color1, LEDColor color2, int numOfPixels) {
        this.color1 = color1;
        this.color2 = color2;
        this.numOfPixels = numOfPixels;
    }

    @Override
    public void color(IPixelsArray ledObject) {
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            int locationInSegment = i % (2 * this.numOfPixels);
            LEDColor color;
            if(locationInSegment < this.numOfPixels) {
                color = color1;
            }
            else {
                color = color2;
            }
            ledObject.setColor(i, color);
        }
    }
}
