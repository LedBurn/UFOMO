/*
    will overrun the hue property of the source effect to create a rainbow, from start hue to end hue
 */
public class ContinuousRainbowEffect extends ContinuousEffect {

    public ContinuousRainbowEffect(ContinuousEffect sourceEffect) {
        this.sourceEffect = sourceEffect;
        this.configure(0.0, 1.0);
    }

    public void configure(double hueStart, double hueEnd) {
        //TODO: check that hueEnd >= hueStart
        this.hueStart = hueStart;
        this.hueEnd = hueEnd;
        this.hueDiff = this.hueEnd - this.hueStart;
    }

    public HSBColor getColor(double timePercent, double location) {
        HSBColor origColor = sourceEffect.getColor(timePercent, location);
        origColor.brightness = 1.0;
        origColor.saturation = 1.0;
        origColor.hue = this.hueStart + this.hueDiff * location;
        return origColor;
    }

    private ContinuousEffect sourceEffect;

    private double hueStart, hueEnd, hueDiff;
}
