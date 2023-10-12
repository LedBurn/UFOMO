public class ContinuousCyclicMoveEffect extends ContinuousEffect {

    public ContinuousCyclicMoveEffect(ContinuousEffect sourceEffect) {
        this.sourceEffect = sourceEffect;
        configure(1, true);
    }

    public void configure(int cyclesPerBeat, boolean forward) {
        if(forward) {
            this.cyclesPerBeat = cyclesPerBeat;
        }
        else {
            this.cyclesPerBeat = -cyclesPerBeat;
        }
    }

    public LEDColor getColor(double timePercent, double location) {
        double newLocation = (location + timePercent * cyclesPerBeat) % 1.0;
        return this.sourceEffect.getColor(timePercent, newLocation);
    }

    private ContinuousEffect sourceEffect;

    private int cyclesPerBeat;
    private boolean forward;
}
