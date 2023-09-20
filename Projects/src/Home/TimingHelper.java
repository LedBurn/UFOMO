/**
 * This class helps to do animation timing.
 * It enables smooth changes in speed :)
 */
public class TimingHelper {

    private long cycleTime = 5000; //milliseconds
    private long lastFrameCycleNum = 0;
    private long lastFrameTimestamp;
    private double lastFrameCycleProgress;

    public TimingHelper() {

    }

    public long getCycleNum() {
        return this.lastFrameCycleNum;
    }
    public double getCycleProgress() {
        return this.lastFrameCycleProgress;
    }

    public long getCycleTime() {
        return this.cycleTime;
    }

    public void setCycleTime(long cycleTime) {
        this.cycleTime = cycleTime;
    }

    public void newFrame() {
        long currentTime = System.currentTimeMillis();
        double diffBetweenFrames = ( currentTime - this.lastFrameTimestamp) / (double) this.cycleTime;
        this.lastFrameCycleProgress += diffBetweenFrames;
        while (this.lastFrameCycleProgress >= 1) {
            this.lastFrameCycleNum++;
            this.lastFrameCycleProgress -= 1;
        }
        this.lastFrameTimestamp = currentTime;
    }

    public void newAnimation() {
        this.lastFrameCycleProgress = 0;
        this.lastFrameTimestamp = System.currentTimeMillis();
        this.lastFrameCycleNum = 0;
    }
}
