public class DiscreteAlternateEffect extends DiscreteEffect {

    public DiscreteAlternateEffect(int numberOfPixels, DiscreteEffect e1, DiscreteEffect e2) {
        super(numberOfPixels);
        this.e1 = e1;
        this.e2 = e2;
        configure(1, 3);
    }

    public void configure(int alternatePerBeat, int numberOfPixels) {
        this.alternatePerBeat = alternatePerBeat;
        this.changeFreequency = 1.0 / this.alternatePerBeat;
        this.numberOfPixels = numberOfPixels;
    }

    public HSBColor getColor(double timePercent, int index) {
        boolean alternateStage = (timePercent % this.changeFreequency) < (0.5 / alternatePerBeat);
        if( ((index / numberOfPixels) % 2 == 0 ) == alternateStage) {
            return this.e1.getColor(timePercent, index);
        }
        else {
            return this.e2.getColor(timePercent, index);
        }
    }

    private DiscreteEffect e1, e2;
    private int alternatePerBeat;
    private double changeFreequency;
    private int numberOfPixels;
}
