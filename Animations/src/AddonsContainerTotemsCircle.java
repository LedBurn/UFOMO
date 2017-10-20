import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.UnaryOperator;

public class AddonsContainerTotemsCircle extends AddonsContainerTotems {

    public AddonsContainerTotemsCircle(Totem totems[], List<Callable<AddonEffect>> effects) {
        for(int i=0; i<totems.length; i++) {
            double totemPercent = ((double)i) / (totems.length - 1);
            UnaryOperator<Double> calcTimePercent = (Double timePercent) -> 2.0 * timePercent - totemPercent;
            Totem t = totems[i];
            this.addSegment(t, t.leftIndexes, effects, calcTimePercent);
            this.addSegment(t, t.rightIndexes, effects, calcTimePercent);
        }
    }
}
