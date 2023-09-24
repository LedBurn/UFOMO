public abstract class Animation {

    protected IPixelsArray ledObject;
//    protected long startingTime = 0; // in millis
//    protected long startingTime = 0; // in millis

    public Animation(IPixelsArray ledObject) {
        this.ledObject = ledObject;
    }
    public abstract void animate(long cycleNum, double cycleTimePercent);
}
