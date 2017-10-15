public class AnimationTiming {

    private Animation animation;
    private double fromTime;
    private double toTime;


    public AnimationTiming(Animation animation, double fromTime, double toTime) {
        this.animation = animation;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public void apply(double currentPos) {
        if (currentPos < fromTime || currentPos >=toTime) {
            return;
        }

        double timePercent = (currentPos - fromTime) / (toTime - fromTime);
        animation.apply(timePercent);
    }
}
