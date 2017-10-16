public class AnimationTimingAmir {

    private AnimationAmir animation;
    private double fromTime;
    private double toTime;


    public AnimationTimingAmir(AnimationAmir animation, double fromTime, double toTime) {
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
