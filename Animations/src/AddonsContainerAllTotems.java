import java.util.List;
import java.util.concurrent.Callable;

public class AddonsContainerAllTotems extends AddonsContainerTotems {

    public AddonsContainerAllTotems(Totem totems[], List<Callable<AddonEffect>> effects) {

        for(Totem t : totems) {
            this.addSegment(t, t.leftIndexes, effects);
            this.addSegment(t, t.rightIndexes, effects);
        }
    }

}
