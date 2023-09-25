public class PropellerAnimation extends Animation_2D {

    private HSBColor color1;
    private HSBColor color2;
    private double fadeSize;

    private final double centerX;
    private final double centerY;



    public PropellerAnimation(PixelArray_2D ledObject, double centerX, double centerY, HSBColor color1, HSBColor color2, double fadeSize) {
        super(ledObject);
        this.centerX = centerX;
        this.centerY = centerY;
        this.color1 = color1;
        this.color2 = color2;
        this.fadeSize = fadeSize;
    }

    public void updateParams(HSBColor color1, HSBColor color2, double fadeSize) {
        this.color1 = color1;
        this.color2 = color2;
        this.fadeSize = fadeSize;
    }

    @Override
    public void animate(long cycleNum, double cycleTimePercent) {
        double angleRadians = Math.PI * (2 * cycleTimePercent + 0.5) ;
        double ratio = Math.tan(angleRadians);

        for(Pixel_2D pixel : this.ledObject.pixels) {
            double yForLine = ratio * (pixel.x - this.centerX) + this.centerY;

            HSBColor color;
            HSBColor otherColor;
            if ((pixel.y > yForLine && cycleTimePercent < 0.5) ||
                    pixel.y < yForLine && cycleTimePercent > 0.5) {
                color = color1;
                otherColor = color2;
            } else {
                color = color2;
                otherColor = color1;
            }

            double distanceToLine = Math.abs(Math.cos(angleRadians) * (this.centerY - pixel.y) - Math.sin(angleRadians) * (this.centerX - pixel.x));
            double distancePercent = distanceToLine / this.ledObject.maxDistance();
            if (distancePercent < this.fadeSize / 2.0 ) {
                pixel._1DArray.setColor(pixel._1DIndex, HSBColor.averageColors(color, otherColor, 0.5 - distancePercent / this.fadeSize));
            } else {
                pixel._1DArray.setColor(pixel._1DIndex, color);
            }
        }
    }
}
