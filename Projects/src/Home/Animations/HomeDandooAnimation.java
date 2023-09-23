import java.util.Map;

public class HomeDandooAnimation extends HomeAnimation {
    public HomeDandooAnimation(HomeObject homeObject, Map<String, String> userInput) {
        super(homeObject, userInput);
        int divider = new Integer(userInput.get("divider"));
        double chunkSize = new Double(userInput.get("chunk-size"));

        this.compoundAnimation.addAnimation(new DandooAnimation(this.homeObject.ceilingUnite, divider, chunkSize));
        this.compoundAnimation.addAnimation(new DandooAnimation(this.homeObject.frontInternalUnite, divider, chunkSize));
        this.compoundAnimation.addAnimation(new DandooAnimation(this.homeObject.frontExternalUnite, divider, chunkSize));


    }
}
