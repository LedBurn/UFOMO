import java.util.Map;

public class HomeMidToCornerAnimation extends HomeAnimation {
    public HomeMidToCornerAnimation(HomeObject homeObject, Map<String, String> userInput) {
        super(homeObject, userInput);

        double chunkSize = new Double(userInput.get("chunk-size"));
        double chunkSpeed = new Double(userInput.get("chunk-speed"));
        double fadeSize = new Double(userInput.get("fade-size"));
        String colorStr = userInput.get("color");
        HSBColor color = HSBColor.hsbColorFromHex(colorStr);
        MidToSidesChunkAnimation.FADE_STYLE fadeStyle = MidToSidesChunkAnimation.FADE_STYLE.BEGINNING_AND_END;

        for (IPixelsArray pixelsArray : homeObject.all) {
            this.compoundAnimation.addAnimation(new MidToSidesChunkAnimation(pixelsArray,
                    chunkSize, chunkSpeed,
                    fadeStyle, fadeSize, color));
        }
    }
}
