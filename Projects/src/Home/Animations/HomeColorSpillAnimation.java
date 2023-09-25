import java.util.Map;

public class HomeColorSpillAnimation extends HomeAnimation {

    public HomeColorSpillAnimation(HomeObject homeObject, Map<String, String> userInput) {
        super(homeObject, userInput);
        this.compoundAnimation.addAnimation(new ColorSpillAnimation2D(homeObject.pixelArray_2D));
    }

    @Override
    public void userInputUpdated(Map<String, String> userInput) {
    }
}
