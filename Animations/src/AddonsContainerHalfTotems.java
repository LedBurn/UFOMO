import java.util.List;
import java.util.concurrent.Callable;

public class AddonsContainerHalfTotems extends AddonsContainerTotems {

    public AddonsContainerHalfTotems(Totem totems[], List<Callable<AddonEffect>> effects, boolean isEven) {

        int i=0;
        if(!isEven) {
            i=1;
        }
        for(; i<totems.length; i+=2) {
            Totem t = totems[i];
            this.addSegment(t, t.leftIndexes, effects, null);
            this.addSegment(t, t.rightIndexes, effects, null);
        }
    }

}
