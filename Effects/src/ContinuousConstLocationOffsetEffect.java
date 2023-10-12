public class ContinuousConstLocationOffsetEffect extends ContinuousEffect {

    public ContinuousConstLocationOffsetEffect(ContinuousEffect sourceEffect) {
        this.sourceEffect = sourceEffect;
        configure(0.5);
    }

    public void configure(double constOffset) {
        this.constOffset = constOffset;
    }

    public LEDColor getColor(double timePercent, double location) {
        double newLocation = (location + this.constOffset) % 1.0;
        return this.sourceEffect.getColor(timePercent, newLocation);
    }

    private ContinuousEffect sourceEffect;

    private double constOffset;
}
