public class DiscreteSpikeEffect extends DiscreteEffect
{

    public DiscreteSpikeEffect(int numberOfPixels, DiscreteEffect e) {
        super(numberOfPixels);
        this.e = e;
        configure(1.0, -0.3, 0.3);
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

    @Override
    public HSBColor getColor(double timePercent, int index) {

        double relPosition = ((double)index / numberOfPixels);
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

        if(relPosition <= lowerPos || relPosition > higherPos) {
            HSBColor c = new HSBColor();
            c.brightness = 0.0;
            return c;
        }
        else {
            HSBColor c = e.getColor(timePercent, index);
            c.brightness = 1.0 -  ((currHeadStart - relPosition) / this.tailLength);
            return c;
        }
    }

    private DiscreteEffect e;
    private double headStartPos, headEndPos, tailLength; // can be of any integer, not necessarily [0, numOfPixels]
}
