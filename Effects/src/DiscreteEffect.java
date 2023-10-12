
abstract public class DiscreteEffect {

    public DiscreteEffect(int numberOfPixels) {
        this.numberOfPixels = numberOfPixels;
    }

    abstract public LEDColor getColor(double timePercent, int index);

    public int numberOfPixels;
}
