public class LinesObject implements ILEDObject<LinesObject> {

    public IPixelsArray[] lines = new IPixelsArray[16];

    public LinesObject() {
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new PixelsArrayImp(240/3);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new PixelsArrayImp(240/3);
        }
    }

    @Override
    public void copy(LinesObject other) {
        for (int i = 0; i < lines.length; i++) {
            lines[i].copy(other.lines[i]);
        }
    }

    @Override
    public void mergeAndCopy(LinesObject other1, LinesObject other2, double percent) {
        for (int i = 0; i < lines.length; i++) {
            lines[i].mergeAndCopy(other1.lines[i], other2.lines[i], percent);
        }
    }
}

