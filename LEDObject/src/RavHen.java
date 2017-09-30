public class RavHen extends LEDObject {

    public HSBColor pixels[] = new HSBColor[300];
    public int pixels1[] = this.CreateIndexRange(0, 100);

    public HSBColor[] GetAllPixels() {
        return this.pixels;
    }

}
