public class Sheep extends LEDObject {

    public HSBColor pixels[] = CreateHSBArray(302);
    public int[] headIndexes = CreateIndexRange(27, 77);

    public int[][] bodyIndexes = new int[][] { CreateIndexRange(81, 94), CreateIndexRange(133, 145),
            CreateIndexRange(147, 160), CreateIndexRange(200, 210), CreateIndexRange(213, 224),
            CreateIndexRange(227, 239), CreateIndexRange(242, 255), CreateIndexRange(257, 270),
            CreateIndexRange(271, 287), CreateIndexRange(288, 299)};
    public int[] allBodyIndexes = combineArrays(bodyIndexes);

    public int[] leg1LeftIndexes = CreateIndexRange(97, 110);
    public int[] leg1RightIndexes = CreateIndexRange(117, 130);
    public int[] leg2LeftIndexes = CreateIndexRange(163, 177);
    public int[] leg2RightIndexes = CreateIndexRange(184, 197);
    public int[] allLegsIndexes = combineArrays(new int[][]{leg1LeftIndexes, leg1RightIndexes, leg2LeftIndexes, leg2RightIndexes});

    public int[] leftEyeIndexes = CreateIndexRange(301, 301);
    public int[] rightEyesIndexes = CreateIndexRange(300, 300);
    public int[] allEyesIndexes = combineArrays(new int[][]{leftEyeIndexes, rightEyesIndexes});

    public int[] allActivePixels = combineArrays(new int[][] {
       headIndexes, allBodyIndexes, allLegsIndexes, allEyesIndexes
    });

    @Override
    public HSBColor[] GetAllPixels() {
        return this.pixels;
    }
}
