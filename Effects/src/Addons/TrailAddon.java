public class TrailAddon extends Addon {

    int crazyDotPosition = -1;
    boolean reversed = Math.random() > 0.5;


    @Override
    public void change(IPixelsArray ledObject, double level) {
        if (crazyDotPosition == -1) {
            crazyDotPosition = (int)Math.floor(Math.random() * ledObject.numOfPixels());
        }

        // set all black
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            LEDColor color = ledObject.getColor(i);
            color.brightness = 0.0;

        }

        // add brightness
        for (int i = 0; i < 8; i++) {
            int index = crazyDotPosition + (reversed ? -i : +i);
            if (index < 0) index += ledObject.numOfPixels();
            if (index >= ledObject.numOfPixels()) index -= ledObject.numOfPixels();
            double brightness = (8-i)/8.0;

            LEDColor color = ledObject.getColor(index);
            color.brightness = brightness;
        }

        crazyDotPosition += reversed ? -1 : 1;
        if (crazyDotPosition < 0) crazyDotPosition = ledObject.numOfPixels() - 1;
        if (crazyDotPosition == ledObject.numOfPixels()) crazyDotPosition = 0;
    }
}
