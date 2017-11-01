import java.util.ArrayList;

public class Animation {

    private ArrayList<EffectToObjectMapper> mappers = new ArrayList<>();

    private ArrayList<Animation> animations = new ArrayList<>();
    private ArrayList<Double> fromPercents = new ArrayList<>();
    private ArrayList<Double> toPercents = new ArrayList<>();

    public void addMapper(EffectToObjectMapper mapper) {
        mappers.add(mapper);
    }

    public void addAnimation(Animation animation) {
        addAnimation(animation, 0.0, 1.0);
    }

    public void addAnimation(Animation animation, double fromPercent, double toPercent) {
        animations.add(animation);
        fromPercents.add(fromPercent);
        toPercents.add(toPercent);
    }

    public void apply(double timePercent) {
        for (EffectToObjectMapper mapper : mappers) {
            mapper.apply(timePercent);
        }

        for (int i=0; i<animations.size(); i++) {
            Animation animation = animations.get(i);
            double fromPercent = fromPercents.get(i);
            double toPercent = toPercents.get(i);
            if (timePercent < fromPercent || timePercent > toPercent) {
                continue;
            }

            double subPercent = (timePercent - fromPercent) / (toPercent - fromPercent);
            animation.apply(subPercent);
        }
    }
}
