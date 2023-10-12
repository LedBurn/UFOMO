
public class BlurColoring extends Coloring {


    private LEDColor[] rand;
    public BlurColoring(LEDColor baseColor, int numOfPixels) {
        rand = new LEDColor[numOfPixels];

        for (int i = 0; i < numOfPixels; i++) {
            double hue = baseColor.hue + Math.random() * 0.1 - 0.05;
            double saturation = baseColor.saturation + Math.random() * 0.1 - 0.05;
            double brightness = baseColor.brightness + Math.random() * 0.8 - 0.4;
            rand[i] = new LEDColor(hue, saturation, brightness);
        }
    }

    @Override
    public void color(IPixelsArray ledObject) {
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            ledObject.setColor(i, rand[i]);
        }
    }
}
