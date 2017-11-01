public class AddonChangeHueOverLocation extends AddonEffect {

    public AddonChangeHueOverLocation(HSBColor cStart, HSBColor cEnd) {
        this.cStart = cStart;
        this.cEnd = cEnd;
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {

        double hueCurrStart = 2.0 * timePercent;
        double hueCurrEnd = 2.0 * timePercent - 1.0;

        for(int i=0; i<array.length; i++) {
            HSBColor c = array[i];
            double locI = ((double)(i)) / (array.length - 1);
            if(locI > hueCurrStart) {
                c.copyFromOther(this.cStart);
            }
            else if(locI < hueCurrEnd) {
                c.copyFromOther(this.cEnd);
            }
            else {
                double distFromStart = hueCurrStart - locI;
                double distFromEnd = locI - hueCurrEnd;
                c.copyFromOther(HSBColor.mixColors(this.cStart, 1.0 - distFromStart, this.cEnd, 1.0 - distFromEnd));
            }
        }
    }

    private HSBColor cStart, cEnd;
}
