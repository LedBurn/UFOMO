
/**
 * An abstract class of a physical LED object
 * */
abstract public class LEDObject {
    abstract public HSBColor[] GetAllPixels();
    public int GetPixelsNumber() { return this.GetAllPixels().length; }

    static int[] CreateIndexRange(int firstIndex, int lastIndex) {
        int allIndexes[] = new int[lastIndex - firstIndex + 1];
        for(int i=firstIndex; i<= lastIndex; i++) {
            allIndexes[i-firstIndex] = i;
        }
        return allIndexes;
    }
}
