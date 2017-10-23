import java.util.List;
import java.util.concurrent.Callable;

public class EntireSheep extends AddonsContainer {

    public EntireSheep(Sheep sheep, List<Callable<AddonEffect>> effects) {

        this.sheep = sheep;
        this.arrayForEffects = new HSBColor[sheep.allActivePixels.length];

        this.effects = new AddonEffect[effects.size()];
        int i=0;
        for(Callable<AddonEffect> effectCallable : effects) {
            try {
                this.effects[i] = effectCallable.call();
            }
            catch (Exception e) {
                System.out.println("Badddd " + e.toString());
            }
            i++;
        }
    }

    @Override
    public void apply(double timePercent) {
        for(int i=0; i < this.sheep.allActivePixels.length; i++) {
            this.arrayForEffects[i] = this.sheep.GetAllPixels()[this.sheep.allActivePixels[i]];
        }
        for(AddonEffect e: this.effects) {
            e.apply(this.arrayForEffects, timePercent);
        }
    }

    private Sheep sheep;
    private AddonEffect effects[];
    private HSBColor arrayForEffects[];
}
