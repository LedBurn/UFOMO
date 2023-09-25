import java.util.Map;

public class HomeDandooAnimation extends HomeAnimation {
    public HomeDandooAnimation(HomeObject homeObject, Map<String, String> userInput) {
        super(homeObject, userInput);
        this.userInputUpdated(userInput);
    }

    @Override
    public void userInputUpdated(Map<String, String> userInput) {
        int divider = Integer.parseInt(userInput.get("divider"));
        double chunkSize = Double.parseDouble(userInput.get("chunk-size"));

        this.compoundAnimation.clear();
        this.compoundAnimation.addAnimation(new DandooAnimation(this.homeObject.ceilingUnite, divider, chunkSize));
        this.compoundAnimation.addAnimation(new DandooAnimation(this.homeObject.frontInternalUnite, divider, chunkSize));
        this.compoundAnimation.addAnimation(new DandooAnimation(this.homeObject.frontExternalUnite, divider, chunkSize));
    }
}
