import java.util.concurrent.ThreadLocalRandom;

public class DiscreteConfettiEffect extends DiscreteEffect
{

    public DiscreteConfettiEffect(int numberOfPixels, DiscreteEffect e) {
        super(numberOfPixels);
        this.birthTime = new double[numberOfPixels];
        for(int i=0; i<numberOfPixels; i++) {
            this.birthTime[i] = -1000000; // very small number - so the exponent will be very small as well
        }
        this.e = e;
        configure(3.0, 0.05);
    }

    public void configure(double pixelsPercentToShine, double midLifeTime) {
        this.pixelsPercentToShine = pixelsPercentToShine;
        this.midLifeTime = midLifeTime;
    }

    @Override
    public LEDColor getColor(double timePercent, int index) {
        shine(timePercent);
        LEDColor c = e.getColor(timePercent, index);
        double timeFromBirth = timePercent - this.birthTime[index];
        double midTimeFactor = -0.693 / this.midLifeTime;
        double brightFactor = Math.exp(midTimeFactor * timeFromBirth);
        c.brightness = c.brightness * brightFactor;
        return c;
    }

    private void shine(double timePercent) {
        double timeFromLastShine = timePercent - this.lastShineTime;
        if(timeFromLastShine == 0.0) {
            return;
        }
        if(timeFromLastShine < 0.0) {
            this.totalShinedPixels = 0;
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

    private double pixelsPercentToShine;
    private double midLifeTime;

    private int totalShinedPixels = 0;

    private DiscreteEffect e;
    private double birthTime[];
}

