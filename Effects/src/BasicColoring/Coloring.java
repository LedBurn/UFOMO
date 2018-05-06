
// This abstract class is the start point of all the animations. it sets the base color of each pixel.
public abstract class Coloring {

    public static final Coloring RAINBOW_COLORING = new RainbowColoring(false);
    public static final Coloring RAINBOW_REVERESED_COLORING = new RainbowColoring(true);

    // colors the led object
    public abstract void color(LEDObject ledObject);
}
