import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class AddonsContainerPartialTotem extends AddonsContainerTotems {

    public void addTotemSegment(Totem totems[], List<Callable<AddonEffect>> effects, double locStart, double locEnd, Boolean sideLeft) {

        for(Totem t : totems) {
            if(sideLeft == null || sideLeft == true) {
                this.addIndexes(t, effects, locStart, locEnd, t.leftIndexes);
            }
            if(sideLeft == null || sideLeft == false) {
                this.addIndexes(t, effects, locStart, locEnd, t.rightIndexes);
            }
        }
    }

    private void addIndexes(Totem totem, List<Callable<AddonEffect>> effects, double locStart, double locEnd, int indexes[]) {

        int indexStart = (int)(locStart * indexes.length);
        int indexEnd = (int)(locEnd * indexes.length);
        int relevantArray[];
        if(indexStart <= indexEnd) {
            relevantArray = Arrays.copyOfRange(indexes, indexStart, indexEnd);
        }
        else { // the buffer is reversed! indexStart > indexEnd

            // TODO: there must be a more reasonable way of doing this
            relevantArray = new int[indexStart - indexEnd];

            int dstArrayIndex = 0;
            for(int i=indexStart - 1; i >= indexEnd; i--, dstArrayIndex++) {
                relevantArray[dstArrayIndex] = indexes[i];
            }
        }
        this.addSegment(totem, relevantArray, effects, null);
    }

}
