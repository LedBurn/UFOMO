import java.util.Map;

public class HomeAlternate2Animation extends HomeAnimation{
    public HomeAlternate2Animation(HomeObject homeObject, Map<String, String> userInput) {
        super(homeObject, userInput);

        int divider = new Integer(userInput.get("divider"));
        HSBColor color1 = HSBColor.hsbColorFromHex(userInput.get("color1"));
        HSBColor color2 = HSBColor.hsbColorFromHex(userInput.get("color2"));

        this.compoundAnimation.addAnimation(new Alternate2Animation(this.homeObject.ceilingUnite, divider, color1, color2));
        this.compoundAnimation.addAnimation(new Alternate2Animation(this.homeObject.frontInternalUnite, divider, color1, color2));
        this.compoundAnimation.addAnimation(new Alternate2Animation(this.homeObject.frontExternalUnite, divider, color1, color2));

    }

    @Override
    public void userInputUpdated(Map<String, String> userInput) {

    }
}
