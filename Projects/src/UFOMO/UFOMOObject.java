// This class holds the pixels of all the UFOMO leds.
public class UFOMOObject implements ILEDObject<UFOMOObject>{
    public IPixelsArray bigCircle;
    public IPixelsArray mediumCircle;
    public IPixelsArray smallCircle;
    public IPixelsArray[] octagon = new PixelsArrayImp[8];
    public IPixelsArray[] lines = new PixelsArrayImp[16];
    public IPixelsArray[] beam = new PixelsArrayImp[8];

    public UFOMOObject() {
        bigCircle = new PixelsArrayImp(1920/3);
        mediumCircle = new PixelsArrayImp(1260/3);
        smallCircle = new PixelsArrayImp(840/3);
        for (int i = 0; i < 8; i++) {
            octagon[i] = new PixelsArrayImp(51/3);
        }
        for (int i = 0; i < 16; i++) {
            lines[i] = new PixelsArrayImp(240/3);
        }
        for (int i = 0; i < 8; i++) {
            beam[i] = new PixelsArrayImp(204/3);
        }
    }

    @Override
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

    @Override
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

    @Override
    public void mergeAndCopy(UFOMOObject other1, UFOMOObject other2, double percent) {
        bigCircle.mergeAndCopy(other1.bigCircle, other2.bigCircle, percent);
        mediumCircle.mergeAndCopy(other1.mediumCircle, other2.mediumCircle, percent);
        smallCircle.mergeAndCopy(other1.smallCircle, other2.smallCircle, percent);
        for (int i = 0; i < 8; i++) {
            octagon[i].mergeAndCopy(other1.octagon[i], other2.octagon[i], percent);
        }
        for (int i = 0; i < 16; i++) {
            lines[i].mergeAndCopy(other1.lines[i], other2.lines[i], percent);
        }
        for (int i = 0; i < 8; i++) {
            beam[i].mergeAndCopy(other1.beam[i], other2.beam[i], percent);
        }
    }
}
