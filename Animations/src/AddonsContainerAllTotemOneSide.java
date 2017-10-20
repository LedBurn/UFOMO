import java.util.List;
import java.util.concurrent.Callable;

public class AddonsContainerAllTotemOneSide extends AddonsContainerTotems {

    public AddonsContainerAllTotemOneSide(Totem totems[], List<Callable<AddonEffect>> effects, boolean isLeft) {

        for(Totem t : totems) {
            if(isLeft) {
                this.addSegment(t, t.leftIndexes, effects, null);
            }
            else {
                this.addSegment(t, t.rightIndexes, effects, null);
            }
        }
    }

}

