public class EffectToObjectMapper {

    public EffectToObjectMapper(DiscreteEffect effect, HSBColor destArray[], int indexes[]) {
        this.effect = effect;
        this.destArray = destArray;
        this.indexes = indexes;
    }

    public void apply(double timePercent) {
        for(int i=0; i<this.indexes.length; i++) {
            this.destArray[this.indexes[i]] = this.effect.getColor(timePercent, i);
        }
    }

    private DiscreteEffect effect;
    private HSBColor destArray[];
    private int indexes[];
}
