public class DiscreteConstColorEffect extends DiscreteEffect {

    public DiscreteConstColorEffect(int numberOfPixels) {
        super(numberOfPixels);
        LEDColor black = new LEDColor();
        black.brightness = 0.0;
        configure(black);
    }

    public void configure(LEDColor color) {
        this.color = color;
    }

    public LEDColor getColor(double timePercent, int index) {
        return color;
    }

    private LEDColor color;
}
