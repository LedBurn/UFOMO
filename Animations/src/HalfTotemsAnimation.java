import java.util.ArrayList;

public class HalfTotemsAnimation extends AnimationAmir {

    public HalfTotemsAnimation(Totem totems[], DiscreteEffect sourceEffect, boolean isEven) {

        int onI;
        if(isEven) {
            onI = 0;
        }
        else {
            onI = 1;
        }
        for(; onI < totems.length; onI+=2) {
            Totem currTotem = totems[onI];
            mappers.add(new EffectToObjectMapper(sourceEffect, currTotem.GetAllPixels(), currTotem.leftIndexes));
            mappers.add(new EffectToObjectMapper(sourceEffect, currTotem.GetAllPixels(), currTotem.rightIndexes));
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
