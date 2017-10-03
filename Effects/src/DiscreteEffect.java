
abstract public class DiscreteEffect {

    public DiscreteEffect(int numberOfPixels) {
        this.numberOfPixels = numberOfPixels;
    }

    abstract public HSBColor getColor(double timePercent, int index);

    public int numberOfPixels;
}
