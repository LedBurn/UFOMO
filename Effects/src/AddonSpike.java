public class AddonSpike extends AddonEffect {

    public AddonSpike(double headStartPos, double headEndPos, double tailLength) {
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
    public void apply(HSBColor[] array, double timePercent) {

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

        for(int i=0; i < array.length; i++) {
            HSBColor currColor = array[i];
            double relLocation = ((double)i) / array.length;
            if(relLocation <= lowerPos || relLocation > higherPos) {
                currColor.brightness = 0.0;
            }
            else {
                currColor.brightness = 1.0 -  ((currHeadStart - relLocation) / this.tailLength);
            }
        }

    }

    private double headStartPos, headEndPos, tailLength;

}
