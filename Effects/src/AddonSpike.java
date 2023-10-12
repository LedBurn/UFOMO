public class AddonSpike extends AddonEffect {

    public AddonSpike(double headStartPos, double headEndPos, double tailLength, boolean erase) {
        this.headStartPos = headStartPos;
        this.headEndPos = headEndPos;
        if(headStartPos < headEndPos) {
            this.tailLength = tailLength;
        }
        else { // spike is going from up to down, so tail should be flipped as well
            this.tailLength = -tailLength;
        }
        this.erase = erase;
    }

    @Override
    public void apply(LEDColor[] array, double timePercent) {

        final double currHeadStart = (this.headStartPos + ( (this.headEndPos - this.headStartPos) * timePercent));
        final double currTailEnd = currHeadStart - this.tailLength;

        boolean goingDownUp = currHeadStart > currTailEnd;

        for(int i=0; i < array.length; i++) {

            LEDColor currColor = array[i];
            double relLocation = ((double)i) / array.length;

            if(goingDownUp) {
                if(relLocation < currTailEnd || (!this.erase && relLocation >= currHeadStart)) {
                    currColor.brightness = 0.0;
                }
                else if(relLocation >= currTailEnd && relLocation < currHeadStart) {
                    currColor.brightness *= 1.0 -  ((currHeadStart - relLocation) / this.tailLength);
                }
            }
            else {
                if((!this.erase && relLocation <= currHeadStart) || relLocation > currTailEnd) {
                    currColor.brightness = 0.0;
                }
                else if(relLocation > currHeadStart && relLocation <= currTailEnd) {
                    currColor.brightness *= 1.0 -  ((currHeadStart - relLocation) / this.tailLength);
                }
            }
        }

    }

    private double headStartPos, headEndPos, tailLength;
    private boolean erase;

}
