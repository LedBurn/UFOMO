abstract public class ContinuousEffect {
    abstract public LEDColor getColor(double timePercent, double location);

    public DiscreteEffect getAsDiscrete(int numberOfPixels) {
        return new ContinuousToDiscrete(numberOfPixels, this);
    }
}
