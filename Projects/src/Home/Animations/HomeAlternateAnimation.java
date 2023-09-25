import java.util.Map;

public class HomeAlternateAnimation extends HomeAnimation {


    public HomeAlternateAnimation(HomeObject homeObject, Map<String,String> userInput) {
        super(homeObject, userInput);

        int divider = new Integer(userInput.get("divider"));
        HSBColor color1 = HSBColor.hsbColorFromHex(userInput.get("color1"));
        HSBColor color2 = HSBColor.hsbColorFromHex(userInput.get("color2"));

        this.compoundAnimation.addAnimation(new AlternateAnimation(this.homeObject.ceilingUnite, divider, color1, color2));
        this.compoundAnimation.addAnimation(new AlternateAnimation(this.homeObject.frontInternalUnite, divider, color1, color2));
        this.compoundAnimation.addAnimation(new AlternateAnimation(this.homeObject.frontExternalUnite, divider, color1, color2));


    }

    @Override
    public void userInputUpdated(Map<String, String> userInput) {

    }
}
