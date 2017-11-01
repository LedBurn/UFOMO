import java.util.concurrent.ThreadLocalRandom;

public class DiscreteTwoConstColorsAlternateEffect extends DiscreteEffect {

    public DiscreteTwoConstColorsAlternateEffect(int numberOfPixels) {
        super(numberOfPixels);

        c1 = new DiscreteConstColorEffect(numberOfPixels);
        c2 = new DiscreteConstColorEffect(numberOfPixels);
        e1 = new DiscreteAlternateEffect(numberOfPixels, c1, c2);
        setColors(ThreadLocalRandom.current().nextDouble(0.0, 1.0));
    }

    private void setColors(double hueChange) {

        HSBColor prevColor = this.c1.getColor(0.0, 0);

        double nextHue = prevColor.hue + hueChange;
        this.c1.configure(new HSBColor(nextHue, 1.0, 1.0));
        this.c2.configure(new HSBColor(nextHue + 0.5, 1.0, 1.0));
    }

    @Override
    public HSBColor getColor(double timePercent, int index) {
        boolean newBeat = beatsCounter.newTimePercent(timePercent);
        if(newBeat) {
            setColors(ThreadLocalRandom.current().nextDouble(0.2, 0.5));
        }
        return e1.getColor(timePercent, index);
    }

    DiscreteConstColorEffect c1, c2;
    DiscreteAlternateEffect e1;
    BeatsCounter beatsCounter = new BeatsCounter();
}
