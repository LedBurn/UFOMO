import java.awt.image.BufferedImage;

public class SimulatedTotem implements ISimulatedLEDObject {

    private Totem totem;
    public SimulatedTotem(Totem totem) {
        this.totem = totem;
    }

    @Override
    public int getWidth() {
        return 71;
    }

    @Override
    public int getHeight() {
        return 610;
    }

    @Override
    public void draw(BufferedImage bi) {
        //left
        for (int i = 0; i < 110; i++) {
            int color = totem.pixels[i].toRGBInt();
            int[] xs = LEDObject.CreateIndexRange(30, 34);
            int[] ys = LEDObject.CreateIndexRange(30 + i * 5, 30 + i * 5 + 5);
            for (int x : xs) {
                for (int y : ys) {
                    bi.setRGB(x, y, color);
                }
            }
        }

        //right
        for (int i = 109; i >= 0; i--) {
            int color = totem.pixels[219-i].toRGBInt();
            int[] xs = LEDObject.CreateIndexRange(36, 40);
            int[] ys = LEDObject.CreateIndexRange(30 + i * 5, 30 + i * 5 + 5);
            for (int x : xs) {
                for (int y : ys) {
                    bi.setRGB(x, y, color);
                }
            }
        }
    }
}
