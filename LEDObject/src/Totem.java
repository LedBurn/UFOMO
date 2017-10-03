public class Totem extends LEDObject {

    public HSBColor pixels[] = CreateHSBArray(220);

    @Override
    public HSBColor[] GetAllPixels() {
        return this.pixels;
    }
}
