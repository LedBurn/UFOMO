public class Circle extends LEDObject {

    private HSBColor[] pixels;

    public Circle(int numOfPixels) {
        pixels = CreateHSBArray(numOfPixels);
    }

    @Override
    public HSBColor[] GetAllPixels() {
        return this.pixels;
    }
}
