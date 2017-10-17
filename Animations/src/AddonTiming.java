public class AddonTiming {

    private AddonsContainer addonsContainer;
    private double fromTime;
    private double toTime;


    public AddonTiming(AddonsContainer addonsContainer, double fromTime, double toTime) {
        this.addonsContainer = addonsContainer;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public void apply(double currentPos) {

        if (currentPos < fromTime || currentPos >= toTime) {
            return;
        }

        double timePercent = (currentPos - fromTime) / (toTime - fromTime);
        addonsContainer.apply(timePercent);
    }

}
