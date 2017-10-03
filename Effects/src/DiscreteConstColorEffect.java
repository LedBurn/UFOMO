public class DiscreteConstColorEffect extends DiscreteEffect {

    public DiscreteConstColorEffect(int numberOfPixels) {
        super(numberOfPixels);
        HSBColor black = new HSBColor();
        black.brightness = 0.0;
        configure(black);
    }

    public void configure(HSBColor color) {
        this.color = color;
    }

    public HSBColor getColor(double timePercent, int index) {
        return color;
    }

    private HSBColor color;
}
