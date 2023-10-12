public class ContinuousSpikeEffect extends ContinuousEffect {

    public ContinuousSpikeEffect(ContinuousEffect sourceEffect) {
        this.sourceEffect = sourceEffect;
        configure(0.0, 1.3, 0.3);
    }

    public void configure(double headStartPos, double headEndPos, double tailLength) {
        this.headStartPos = headStartPos;
        this.headEndPos = headEndPos;
        if(headStartPos < headEndPos) {
            this.tailLength = tailLength;
        }
        else { // spike is going from up to down, so tail should be flipped as well
            this.tailLength = -tailLength;
        }
    }

    public LEDColor getColor(double timePercent, double location) {
        double currHeadStart = (this.headStartPos + ( (this.headEndPos - this.headStartPos) * timePercent));
        double currTailEnd = currHeadStart - this.tailLength;

        double lowerPos, higherPos;
        if(currHeadStart < currTailEnd) {
            lowerPos = currHeadStart;
            higherPos = currTailEnd;
        }
        else {
            lowerPos = currTailEnd;
            higherPos = currHeadStart;
        }

        if(location <= lowerPos || location > higherPos) {
            LEDColor c = new LEDColor();
            c.brightness = 0.0;
            return c;
        }
        else {
            LEDColor c = this.sourceEffect.getColor(timePercent, location);
            c.brightness = 1.0 -  ((currHeadStart - location) / this.tailLength);
            return c;
        }
    }

    private ContinuousEffect sourceEffect;

    private double headStartPos, headEndPos, tailLength;
}

