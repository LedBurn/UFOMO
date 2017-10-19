import java.util.concurrent.ThreadLocalRandom;

public class AddonConfetti extends AddonEffect {

    /*
    good configuration is:
    - pixelsPercentToShine = 5.0
    - midLifeTime = 0.05
     */
    public AddonConfetti(double pixelsPercentToShine, double midLifeTime) {
        this.pixelsPercentToShine = pixelsPercentToShine;
        this.midLifeTime = midLifeTime;
    }
1
    @Override
    public void setNumberOfPixels(int numberOfPixels) {
        this.birthTime = new double[numberOfPixels];
        for(int i=0; i < numberOfPixels; i++) {
            this.birthTime[i] = -1000000; // very small number - so the exponent will be very small as well
        }
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {

        this.shine(timePercent, array.length);

        for(int i=0; i<array.length; i++) {
            HSBColor c = array[i];
            double timeFromBirth = timePercent - this.birthTime[i];
            double midTimeFactor = -0.693 / this.midLifeTime;
            double brightFactor = Math.exp(midTimeFactor * timeFromBirth);
            c.brightness = c.brightness * brightFactor;
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

        int expectedShinePixels = (int)(timePercent * this.pixelsPercentToShine * numberOfPixels);
        for(int i=this.totalShinedPixels; i < expectedShinePixels; i++) {
            int index = ThreadLocalRandom.current().nextInt(0, numberOfPixels - 1);
            this.birthTime[index] = timePercent;
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
