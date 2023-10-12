public class RandomColoring extends Coloring{

    private LEDColor[] rand;
    public RandomColoring(int numOfPixels) {
        rand = new LEDColor[numOfPixels];
        for (int i = 0; i < numOfPixels; i++) {
            rand[i] = new LEDColor(Math.random(), 1.0, 1.0);
        }
    }

    @Override
    public void color(IPixelsArray ledObject) {
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            ledObject.setColor(i, rand[i]);
        }
    }
}
