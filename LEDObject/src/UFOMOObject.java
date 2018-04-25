
// This class holds the pixels of all the UFOMO leds.
public class UFOMOObject {
    public LEDObject bigCircle;
    public LEDObject mediumCircle;
    public LEDObject smallCircle;
    public LEDObject[] octagon = new LEDObject[8];
    public LEDObject[] lines = new LEDObject[16];
    public LEDObject[] beam = new LEDObject[8];

    public UFOMOObject() {
        // TODO verify final number of pixels
        bigCircle = new LEDObject(2000);
        mediumCircle = new LEDObject(1260);
        smallCircle = new LEDObject(840);
        for (int i = 0; i < 8; i++) {
            octagon[i] = new LEDObject(33);
        }
        for (int i = 0; i < 16; i++) {
            lines[i] = new LEDObject(240);
        }
        for (int i = 0; i < 8; i++) {
            beam[i] = new LEDObject(400);
        }
    }
}
