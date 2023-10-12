import java.util.Map;

public class HomeMidToCornerAnimation extends HomeAnimation {

    private final MidToSidesChunkAnimation[] animations;

    public HomeMidToCornerAnimation(HomeObject homeObject, Map<String, String> userInput) {
        super(homeObject, userInput);

        this.animations = new MidToSidesChunkAnimation[(homeObject.all.length)];
        for (int i = 0; i < this.animations.length; i++) {
            animations[i] = new MidToSidesChunkAnimation(homeObject.all[i],
                    0, 0,
                    MidToSidesChunkAnimation.FADE_STYLE.BEGINNING_AND_END, 0, null);
            this.compoundAnimation.addAnimation(animations[i]);
        }
        this.userInputUpdated(userInput);
    }

    @Override
    public void userInputUpdated(Map<String, String> userInput) {
        double chunkSize =  Double.parseDouble(userInput.get("chunk-size"));
        double chunkSpeed =  Double.parseDouble(userInput.get("chunk-speed"));
        double fadeSize =  Double.parseDouble(userInput.get("fade-size"));
        String colorStr = userInput.get("color");
        LEDColor color = LEDColor.hsbColorFromHex(colorStr);
        MidToSidesChunkAnimation.FADE_STYLE fadeStyle = MidToSidesChunkAnimation.FADE_STYLE.BEGINNING_AND_END;

        for (MidToSidesChunkAnimation animation : this.animations) {
            animation.updateParams(chunkSize, chunkSpeed, fadeStyle, fadeSize, color);
        }
    }
}
