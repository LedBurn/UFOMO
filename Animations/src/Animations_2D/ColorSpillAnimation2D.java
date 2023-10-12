public class ColorSpillAnimation2D extends Animation_2D {

    private LEDColor previousColor = LEDColor.BLACK;

    private LEDColor color = LEDColor.BLACK;
    private double centerX;
    private double centerY;

    private final double maxRadius;

    private long lastCycleNum = -1;

    private final double fadePercent = 0.2;

    private final double minHueChange = 0.2;


    public ColorSpillAnimation2D(PixelArray_2D ledObject) {
        super(ledObject);

        double maxRadiusX = ledObject.maxX + 2 * this.fadePercent * ledObject.maxX;
        double maxRadiusY = ledObject.maxY + 2 * this.fadePercent * ledObject.maxY;
        this.maxRadius = Math.hypot(maxRadiusX, maxRadiusY);
    }

    @Override
    public void animate(long cycleNum, double cycleTimePercent) {

        if (cycleNum != this.lastCycleNum) {
            this.lastCycleNum = cycleNum;

            // random color
            this.previousColor = this.color;
            this.color = new LEDColor(this.color.hue + this.minHueChange + Math.random() * (1 - 2 * this.minHueChange));

            // add diff and random a new center
            this.centerX = randomPoint(0, this.ledObject.maxX, 0.05);
            this.centerY = randomPoint(0, this.ledObject.maxY, 0.05);
        }

        for (Pixel_2D pixel : this.ledObject.pixels) {
            double distance = pixel.getDistanceFromPoint(this.centerX, this.centerY);
            double distancePercent = distance / this.maxRadius;

                if (distancePercent > cycleTimePercent) { // previous
                    pixel._1DArray.setColor(pixel._1DIndex, previousColor);
                } else if (distancePercent > cycleTimePercent - 0.1) { // fade
                    double brightness = (cycleTimePercent - distancePercent) * 10;
                    pixel._1DArray.setColor(pixel._1DIndex, LEDColor.averageColors(this.previousColor, this.color, brightness));
                } else { // new
                    pixel._1DArray.setColor(pixel._1DIndex, color);
                }
        }
//        for (int i = 0; i < this.ledObject.pixels.size(); i++) {
//            Pixel_2D pixel = this.ledObject.pixels.get(i);
//            pixel._1DArray.setColor(pixel._1DIndex, new HSBColor((pixel.x + pixel.y) / 400.0));
//        }
    }

    private double randomPoint(double start, double end, double buffer) {
        double size = end - start +  (end - start) * buffer * 2;
        return start - buffer + Math.random() * size;
    }
}
