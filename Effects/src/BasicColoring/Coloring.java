
// This abstract class is the start point of all the animations. it sets the base color of each pixel.
public abstract class Coloring {

    public static final Coloring RAINBOW_COLORING = new RainbowColoring(false);
    public static final Coloring RAINBOW_REVERESED_COLORING = new RainbowColoring(true);

    public static final Coloring BLACK_COLORING = new ConstColoring(LEDColor.BLACK);
    public static final Coloring GRAY_COLORING = new ConstColoring(LEDColor.GRAY);


    // colors the array
    public abstract void color(IPixelsArray ledObject);
}
