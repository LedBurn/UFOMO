import java.util.Map;

public class HomePropellerAnimation extends HomeAnimation {


    PropellerAnimation2D animation;

    public HomePropellerAnimation(HomeObject homeObject, Map<String, String> userInput) {
        super(homeObject, userInput);

        this.animation = new PropellerAnimation2D(homeObject.pixelArray_2D, 171.0, 99.0, null, null, 0);
        this.compoundAnimation.addAnimation(this.animation);
        this.userInputUpdated(userInput);
    }

    @Override
    public void userInputUpdated(Map<String, String> userInput) {
        double fadeSize = Double.parseDouble(userInput.get("fade-size"));
        LEDColor color1 = LEDColor.hsbColorFromHex(userInput.get("color1"));
        LEDColor color2 = LEDColor.hsbColorFromHex(userInput.get("color2"));

        this.animation.updateParams(color1, color2, fadeSize);
    }
}
