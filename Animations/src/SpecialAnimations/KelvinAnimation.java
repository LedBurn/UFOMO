public class KelvinAnimation extends Animation {

    private final KelvinScale kelvinScale = new KelvinScale();

    private final double lowPoint;
    private final double highPoint;

    public KelvinAnimation(IPixelsArray ledObject, double lowPoint, double highPoint) {
        super(ledObject);
        this.lowPoint = lowPoint;
        this.highPoint = highPoint;
    }

    @Override
    public void animate(long cycleNum, double cycleTimePercent) {



        for (int i = 0; i < this.ledObject.numOfPixels(); i++) {
            double pixelScale = i / (double) this.ledObject.numOfPixels();
            pixelScale = this.lowPoint + pixelScale * (this.highPoint - this.lowPoint);
            this.ledObject.setColor(i, this.kelvinScale.getColorForLocation(pixelScale));
        }
    }
}
