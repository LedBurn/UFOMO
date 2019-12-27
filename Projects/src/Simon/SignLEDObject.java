public class SignLEDObject implements ILEDObject<SignLEDObject> {

    public IPixelsArray finger1;
    public IPixelsArray finger2;
    public IPixelsArray finger3;
    public IPixelsArray finger4;
    public IPixelsArray finger5;
    public IPixelsArray[] gameFingers;

    public SignLEDObject() {
        clear();
    }

    @Override
    public void clear() {
        finger1 = new PixelsArrayImp(26); //
        finger2 = new PixelsArrayImp(28); //left side up to down, down led 11, up led
        finger3 = new PixelsArrayImp(31);
        finger4 = new PixelsArrayImp(29);
        finger5 = new PixelsArrayImp(18);
        gameFingers = new IPixelsArray[]{ finger1, finger2, finger3, finger4 };
    }

    @Override
    public void copy(SignLEDObject other) {
        for (int i = 0; i < gameFingers.length; i++) {
            gameFingers[i].copy(other.gameFingers[i]);
        }
    }

    @Override
    public void mergeAndCopy(SignLEDObject other1, SignLEDObject other2, double percent) {
        for (int i = 0; i < gameFingers.length; i++) {
            gameFingers[i].mergeAndCopy(other1.gameFingers[i], other2.gameFingers[i], percent);
        }
    }

}
