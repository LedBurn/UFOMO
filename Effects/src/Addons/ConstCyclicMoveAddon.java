
// shifts all the pixels until a complete round in a linear way.
public class ConstCyclicMoveAddon extends Addon {

    private boolean oppositeDirection;
    private double startingPosition;

    public ConstCyclicMoveAddon(boolean oppositeDirection) {
        this(oppositeDirection, 0);
    }

    public ConstCyclicMoveAddon(boolean oppositeDirection, double startingPosition) {
        this.oppositeDirection = oppositeDirection;
        this.startingPosition = startingPosition;
    }

    @Override
    public void change(IPixelsArray ledObject, double level, boolean newBeat, boolean isOn, int[] eq) {

        int indexDiff = (int)((level + this.startingPosition) * ledObject.numOfPixels());
        if (oppositeDirection) {
            indexDiff = -1 * indexDiff;
        }

        // create new pixels array after the shift
        HSBColor[] newPixels;
        newPixels = new HSBColor[ledObject.numOfPixels()];
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            int newPosition = (ledObject.numOfPixels() + i + indexDiff) % ledObject.numOfPixels();
            if (newPosition < 0) newPosition += ledObject.numOfPixels();
            newPixels[i] = ledObject.getColor(newPosition);
        }

        // copy
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            ledObject.setColor(i, newPixels[i]);
        }
    }
}
