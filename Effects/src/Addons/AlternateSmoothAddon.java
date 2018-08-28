public class AlternateSmoothAddon extends Addon {


    private double hue1, hue2;
    private int pixelsPerSegment;

    public AlternateSmoothAddon(double hue1, double hue2, int pixelsPerSegment) {
        this.hue1 = hue1;
        this.hue2 = hue2;
        this.pixelsPerSegment = pixelsPerSegment;
    }

    // use this method to change the hue while the animation is running
    public void changeHue(double hue1, double hue2){
        this.hue1 = hue1;
        this.hue2 = hue2;
    }

    @Override
    public void change(IPixelsArray ledObject, double level, boolean newBeat, boolean isOn, int[] eq) {
        /*
        hue 1 is centered at pixelsPerSegment * 0, pixelsPerSegment * 2, pixelsPerSegment * 4, ...
        hue 2 is centered at pixelsPerSegment * 1, pixelsPerSegment * 3, pixelsPerSegment * 5, ...

        so after we have an equal segments of size pixelsPerSegment * 2. lets it a name - fullSegment
        we will modulo each index to (pixelsPerSegment * 2) to get it's location in the fullSegment.

        now we need to mix the two hues together for that index.
        we will measure the distance of the index from the two hue centers, and that will be our weight.

         */

        for(int i=0; i<ledObject.numOfPixels(); i++) {

            int fullSegmentLocation = i % (2 * this.pixelsPerSegment);

            int distToHue1, distToHue2;
            if(fullSegmentLocation < this.pixelsPerSegment) {
                distToHue1 = fullSegmentLocation;
                distToHue2 = this.pixelsPerSegment - fullSegmentLocation;
            }
            else {
                distToHue1 = 2 * pixelsPerSegment - fullSegmentLocation;
                distToHue2 = fullSegmentLocation - pixelsPerSegment;
            }

            double currHue = HSBColor.combineHues(this.hue1, this.pixelsPerSegment - distToHue1, this.hue2, this.pixelsPerSegment - distToHue2);
            ledObject.setColor(i, new HSBColor(currHue, 1, 1));
        }
    }
}
