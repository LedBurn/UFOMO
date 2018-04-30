
// This abstract class is the start point of all the animations. it sets the base color of each pixel.
public abstract class Coloring {

    public static final Coloring RAINBOW_COLORING = new RainbowColoring();

    // colors the led object
    public abstract void color(LEDObject ledObject);
}
