import java.util.Map;

public class HomeAnimation extends Animation {

    protected final HomeObject homeObject;
    protected final Map<String,String> userInput;

    protected final CompoundAnimation compoundAnimation = new CompoundAnimation();

    public HomeAnimation(HomeObject homeObject, Map<String,String> userInput) {
        super(null);
        this.homeObject = homeObject;
        this.userInput = userInput;
    }

    @Override
    public void animate(long cycleNum, double cycleTimePercent) {
        this.compoundAnimation.animate(cycleNum, cycleTimePercent);
    }
}
