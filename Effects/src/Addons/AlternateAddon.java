

public class AlternateAddon extends Addon {


    private double hue1, hue2;
    private int pixelsPerSegment;

    public AlternateAddon(double hue1, double hue2, int pixelsPerSegment) {
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
    public void change(IPixelsArray ledObject, double level, boolean newBeat, boolean isOn,int[] eq) {

        if (newBeat) {
            double temp = hue1;
            hue1 = hue2;
            hue2 = temp;
        }

        HSBColor color1 = new HSBColor(hue1, 1, 1);
        HSBColor color2 = new HSBColor(hue2, 1, 1);

        for(int i=0; i<ledObject.numOfPixels(); i++) {
            int locationInSegment = i % (2 * this.pixelsPerSegment);
            HSBColor color;
            if(locationInSegment < this.pixelsPerSegment) {
                color = color1;
            }
            else {
                color = color2;
            }
            ledObject.setColor(i, color);
        }
    }
}
