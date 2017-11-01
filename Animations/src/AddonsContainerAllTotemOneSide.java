import java.util.List;
import java.util.concurrent.Callable;

public class AddonsContainerAllTotemOneSide extends AddonsContainerTotems {

    public AddonsContainerAllTotemOneSide(Totem totems[], List<Callable<AddonEffect>> effects, boolean isLeft) {

        for(int i=0; i<totems.length; i++) {
            Totem t =  totems[i];
            boolean isEven = ( (i % 2) == 0);
            if( (isLeft && isEven) || (!isEven && !isEven) ) { // support for boolean xor?
                this.addSegment(t, t.leftIndexes, effects, null);
            }
            else {
                this.addSegment(t, t.rightIndexes, effects, null);
            }
        }
    }

}

