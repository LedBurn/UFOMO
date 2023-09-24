public class Pixel_2D {
    public final double x;
    public final double y;
    public final IPixelsArray _1DArray;
    public final int _1DIndex;

    public Pixel_2D(double x, double y, IPixelsArray _1DArray, int _1DIndex) {
        this.x = x;
        this.y = y;
        this._1DArray = _1DArray;
        this._1DIndex = _1DIndex;
    }

    public double getDistanceFromPoint(double pointX, double pointY) {
        return Math.hypot(this.x-pointX, this.y-pointY);
    }
}
