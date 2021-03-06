public class CrazyDotsAddon extends Addon {

    int crazyDotPosition = -1;
    HSBColor color = HSBColor.BLACK;
    boolean reversed = Math.random() > 0.5;


    @Override
    public void change(IPixelsArray ledObject, double level, boolean newBeat, boolean isOn, int[] eq) {
        if (crazyDotPosition == -1) {
            crazyDotPosition = (int)Math.floor(Math.random() * ledObject.numOfPixels());
        }

        ledObject.setColor(crazyDotPosition, color);

        crazyDotPosition += reversed ? -1 : 1;
        if (crazyDotPosition < 0) crazyDotPosition = ledObject.numOfPixels() - 1;
        if (crazyDotPosition == ledObject.numOfPixels()) crazyDotPosition = 0;
    }
}
