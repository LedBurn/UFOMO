
// shifts all the pixels - one time - without level
public class OneTimeCyclicMoveAddon extends Addon {

    private double changePercent = 0;

    public OneTimeCyclicMoveAddon(double percent) {
        changePercent = percent;
    }

    @Override
    public void change(LEDObject ledObject, double level, boolean newBeat) {

        int indexDiff = (int)(changePercent * ledObject.numOfPixels());

        // create new pixels array after the shift
        HSBColor[] newPixels;
        newPixels = new HSBColor[ledObject.numOfPixels()];
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            int newPosition = (ledObject.numOfPixels() + i + indexDiff) % ledObject.numOfPixels();
            newPixels[i] = ledObject.getColor(newPosition);
        }

        // copy
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            ledObject.setColor(i, newPixels[i]);
        }
    }
}
