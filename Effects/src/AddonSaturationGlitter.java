import java.util.concurrent.ThreadLocalRandom;

public class AddonSaturationGlitter extends AddonEffect {

    public AddonSaturationGlitter(double pixelsPercentToShine, double midLifeTime) {
        this.pixelsPercentToShine = pixelsPercentToShine;
        this.midLifeTime = midLifeTime;
    }

    @Override
    public void setNumberOfPixels(int numberOfPixels) {
        this.birthTime = new double[numberOfPixels];
        double birthValue = -1000000; // very small number - so the exponent will be very small as well
        for(int i=0; i < numberOfPixels; i++) {
            this.birthTime[i] = birthValue;
        }
    }

    @Override
    public void apply(LEDColor[] array, double timePercent) {

        this.shine(timePercent, array.length);

        for(int i=0; i<array.length; i++) {
            LEDColor c = array[i];
            double timeFromBirth = timePercent - this.birthTime[i];
            double midTimeFactor = -0.693 / this.midLifeTime;
            double brightFactor = Math.exp(midTimeFactor * timeFromBirth);
            c.saturation = (1.0 - brightFactor);
        }
    }

    private void shine(double timePercent, int numberOfPixels) {
        double timeFromLastShine = timePercent - this.lastShineTime;
        if(timeFromLastShine == 0.0) {
            return;
        }
        if(timeFromLastShine < 0.0) {
            this.totalShinedPixels = 0;
            for(int i=0; i < this.birthTime.length; i++) {
                this.birthTime[i] -= 1.0;
            }
        }

        // at the end, we want to let some times for the glitter to disappear
        if(timePercent > (1.0 - 3 * this.midLifeTime)) {
            return;
        }
        double locPercent = timePercent / (1.0 - 3 * this.midLifeTime);

        int minBound = (int)(numberOfPixels * locPercent) - 5;
        int maxBound = (int)(numberOfPixels * locPercent) + 5;

        int expectedShinePixels = (int)(timePercent * this.pixelsPercentToShine * numberOfPixels);
        for(int i=this.totalShinedPixels; i < expectedShinePixels; i++) {
            int index = ThreadLocalRandom.current().nextInt(minBound, maxBound);
            if(index >= 0 && index < numberOfPixels) {
                this.birthTime[index] = timePercent;
            }
        }
        this.totalShinedPixels = expectedShinePixels;

        this.lastShineTime = timePercent;
    }

    private double lastShineTime = 0.0;
    private int totalShinedPixels = 0;
    private double birthTime[];

    double pixelsPercentToShine;
    double midLifeTime;

}
