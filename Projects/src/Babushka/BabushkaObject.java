public class BabushkaObject implements ILEDObject<BabushkaObject> {

    public static int[] SIZES = {60, 53, 47, 39, 31};

    public IPixelsArray[] babushkas = new IPixelsArray[SIZES.length];

    public BabushkaObject() {
        this.clear();
    }

    @Override
    public void clear() {
        for (int i = 0; i < babushkas.length; i++) {
            babushkas[i] = new PixelsArrayImp(SIZES[i]);
        }
    }

    @Override
    public void copy(BabushkaObject other) {
        for (int i = 0; i < babushkas.length; i++) {
            babushkas[i].copy(other.babushkas[i]);
        }
    }

    @Override
    public void mergeAndCopy(BabushkaObject other1, BabushkaObject other2, double percent) {
        for (int i = 0; i < babushkas.length; i++) {
            babushkas[i].mergeAndCopy(other1.babushkas[i], other2.babushkas[i], percent);
        }
    }

    public int randomBabushka() {
        return (int)Math.floor(Math.random() * babushkas.length);
    }
}