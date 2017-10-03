public class ContinuousToDiscrete extends DiscreteEffect{

    public ContinuousToDiscrete(int numberOfPixels, ContinuousEffect contEffect) {
        super(numberOfPixels);
        this.contEffect = contEffect;
    }

    public HSBColor getColor(double timePercent, int index) {
        double location = ((double)index) / (numberOfPixels - 1);
        return contEffect.getColor(timePercent, location);
    }

    private ContinuousEffect contEffect;
}
