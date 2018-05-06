
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
        bigCircle = new LEDObject(1920/3);
        mediumCircle = new LEDObject(1260/3);
        smallCircle = new LEDObject(840/3);
        for (int i = 0; i < 8; i++) {
            octagon[i] = new LEDObject(51/3);
        }
        for (int i = 0; i < 16; i++) {
            lines[i] = new LEDObject(240/3);
        }
        for (int i = 0; i < 8; i++) {
            beam[i] = new LEDObject(204/3);
        }
    }

    public void clear() {
        bigCircle.clear();
        mediumCircle.clear();
        smallCircle.clear();
        for (int i = 0; i < 8; i++) {
            octagon[i].clear();
        }
        for (int i = 0; i < 16; i++) {
            lines[i].clear();
        }
        for (int i = 0; i < 8; i++) {
            beam[i].clear();
        }
    }

    // copy all the pixels form another object
    public void copy(UFOMOObject other) {
        bigCircle.copy(other.bigCircle);
        mediumCircle.copy(other.mediumCircle);
        smallCircle.copy(other.smallCircle);
        for (int i = 0; i < 8; i++) {
            octagon[i].copy(other.octagon[i]);
        }
        for (int i = 0; i < 16; i++) {
            lines[i].copy(other.lines[i]);
        }
        for (int i = 0; i < 8; i++) {
            beam[i].copy(other.beam[i]);
        }
    }

    // merge 2 other objects (pixel by pixel merge) and copy it the this object
    public void mergeAndCopy(UFOMOObject other1, UFOMOObject other2, double percent) {
        bigCircle.mergeAndcopy(other1.bigCircle, other2.bigCircle, percent);
        mediumCircle.mergeAndcopy(other1.mediumCircle, other2.mediumCircle, percent);
        smallCircle.mergeAndcopy(other1.smallCircle, other2.smallCircle, percent);
        for (int i = 0; i < 8; i++) {
            octagon[i].mergeAndcopy(other1.octagon[i], other2.octagon[i], percent);
        }
        for (int i = 0; i < 16; i++) {
            lines[i].mergeAndcopy(other1.lines[i], other2.lines[i], percent);
        }
        for (int i = 0; i < 8; i++) {
            beam[i].mergeAndcopy(other1.beam[i], other2.beam[i], percent);
        }
    }
}
