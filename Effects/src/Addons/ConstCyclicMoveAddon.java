
// shifts all the pixels until a complete round in a linear way.
public class ConstCyclicMoveAddon extends Addon {

    private boolean oppositeDirection = false;

    public ConstCyclicMoveAddon(boolean oppositeDirection) {
        this.oppositeDirection = oppositeDirection;
    }

    @Override
    public void change(LEDObject ledObject, double level, boolean newBeat) {

        int indexDiff = (int)(level * ledObject.numOfPixels());
        if (oppositeDirection) {
            indexDiff = -1 * indexDiff;
        }

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
