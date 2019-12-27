public abstract class SimonAnimation {

    public long startTime;
    public double totalTime;

    public SimonAnimation(double totalTime){
        this.totalTime = totalTime;
    }

    public abstract void apply(SignLEDObject sign, double timePercent);
}
