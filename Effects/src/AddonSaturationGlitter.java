import java.util.concurrent.ThreadLocalRandom;

public class AddonSaturationGlitter extends AddonEffect {

    public AddonSaturationGlitter(double pixelsPercentToShine, double midLifeTime) {
        this.pixelsPercentToShine = pixelsPercentToShine;
        this.midLifeTime = midLifeTime;
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {

        if(this.birthTime == null) {
            this.birthTime = new double[array.length];
            for(int i=0; i < array.length; i++) {
                this.birthTime[i] = -1000000; // very small number - so the exponent will be very small as well
            }
        }
        this.shine(timePercent, array.length);

        for(int i=0; i<array.length; i++) {
            HSBColor c = array[i];
            double timeFromBirth = timePercent - this.birthTime[i];
            double midTimeFactor = -0.693 / this.midLifeTime;
            double brightFactor = Math.exp(midTimeFactor * timeFromBirth);
            c.saturation *= (1.0 - brightFactor);
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

        int minBound = Math.max((int)(numberOfPixels * timePercent) - 5, 0);
        int maxBound = Math.min((int)(numberOfPixels * timePercent) + 5, numberOfPixels - 1);

        int expectedShinePixels = (int)(timePercent * this.pixelsPercentToShine * numberOfPixels);
        for(int i=this.totalShinedPixels; i < expectedShinePixels; i++) {
            int index = ThreadLocalRandom.current().nextInt(minBound, maxBound);
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
