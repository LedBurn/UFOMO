import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class AddonsContainerRadomSegments extends AddonsContainerTotems {

    public AddonsContainerRadomSegments(Totem totems[], List<Callable<AddonEffect>> effects, double segmentLength, int numberOfTimes) {
        for(int i=0; i<numberOfTimes; i++) {
            Totem randTotem = totems[ThreadLocalRandom.current().nextInt(totems.length)];
            int allIndexes[];
            if(ThreadLocalRandom.current().nextBoolean()) {
                allIndexes = randTotem.leftIndexes;
            }
            else {
                allIndexes = randTotem.rightIndexes;
            }

            // slice
            int segmentLengthPixels = (int)(segmentLength * allIndexes.length);
            int selectionStartPixel = ThreadLocalRandom.current().nextInt(allIndexes.length - segmentLengthPixels);
            int slicedIndexes[] = Arrays.copyOfRange(allIndexes, selectionStartPixel, selectionStartPixel + segmentLengthPixels);
            this.addSegment(randTotem, slicedIndexes, effects);
        }
    }

}
