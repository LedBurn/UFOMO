/*
this can be the source of other continuous effects that needs a neutral color to start do there magic
 */
public class ContinuousWhiteEffect extends ContinuousEffect {
    public LEDColor getColor(double timePercent, double location) {
        LEDColor c = new LEDColor();
        c.brightness = 1.0;
        c.saturation = 1.0;
        return c;
    }
}
