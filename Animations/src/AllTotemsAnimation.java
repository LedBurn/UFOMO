import java.util.ArrayList;

public class AllTotemsAnimation extends AnimationAmir {

    public AllTotemsAnimation(Totem totems[], DiscreteEffect sourceEffect, boolean useLeft, boolean useRight) {

        for(int i=0; i < totems.length; i++) {
            Totem currTotem = totems[i];
            if(useLeft) {
                mappers.add(new EffectToObjectMapper(sourceEffect, currTotem.GetAllPixels(), currTotem.leftIndexes));
            }
            if(useRight) {
                mappers.add(new EffectToObjectMapper(sourceEffect, currTotem.GetAllPixels(), currTotem.rightIndexes));
            }
        }
    }

    @Override
    public void apply(double timePercent) {
        for(EffectToObjectMapper mapper: this.mappers) {
            mapper.apply(timePercent);
        }
    }

    ArrayList<EffectToObjectMapper> mappers = new ArrayList();

}

