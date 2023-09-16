import java.util.Map;

public class HomeKelvinAnimation extends HomeAnimation {

    public HomeKelvinAnimation(HomeObject homeObject, Map<String,String> userInput) {
        super(homeObject, userInput);

        double center = new Double(userInput.get("center"));
        double range = new Double(userInput.get("range"));

        // the ceiling take 1/4 of the range
        double ceilingHighPoint = center + range / 2;
        double ceilingLowPoint = center + range / 2;

        // the front takes 3/4 of the range
        double frontHighPoint = center + range / 2;
        double frontLowPoint = center - range / 2;

        // the middle front start after 0.32 from the front top and 0.25 from the bottom
        double frontInternalHighPoint = frontHighPoint - (frontHighPoint - frontLowPoint) * 0.32;
        double fronInternalLowPoint = frontLowPoint + (frontHighPoint - frontLowPoint) * 0.25;

        this.compoundAnimation.addAnimation(new KelvinAnimation(this.homeObject.ceiling[0], ceilingLowPoint, ceilingHighPoint));
        this.compoundAnimation.addAnimation(new KelvinAnimation(this.homeObject.ceiling[1], ceilingHighPoint, ceilingHighPoint));
        this.compoundAnimation.addAnimation(new KelvinAnimation(this.homeObject.ceilingReversed[2], ceilingLowPoint, ceilingHighPoint));
        this.compoundAnimation.addAnimation(new KelvinAnimation(this.homeObject.ceiling[3], ceilingLowPoint, ceilingLowPoint));

        this.compoundAnimation.addAnimation(new KelvinAnimation(this.homeObject.frontExternal[0], frontLowPoint, frontLowPoint));
        this.compoundAnimation.addAnimation(new KelvinAnimation(this.homeObject.frontExternal[1], frontLowPoint, frontHighPoint));
        this.compoundAnimation.addAnimation(new KelvinAnimation(this.homeObject.frontExternal[2], frontHighPoint, frontHighPoint));
        this.compoundAnimation.addAnimation(new KelvinAnimation(this.homeObject.frontExternalReversed[3], frontLowPoint, frontHighPoint));
        this.compoundAnimation.addAnimation(new KelvinAnimation(this.homeObject.frontExternal[4], frontLowPoint, frontLowPoint));

        this.compoundAnimation.addAnimation(new KelvinAnimation(this.homeObject.frontInternal[0], fronInternalLowPoint, frontInternalHighPoint));
        this.compoundAnimation.addAnimation(new KelvinAnimation(this.homeObject.frontInternal[1], frontInternalHighPoint, frontInternalHighPoint));
        this.compoundAnimation.addAnimation(new KelvinAnimation(this.homeObject.frontInternalReversed[2], fronInternalLowPoint, frontInternalHighPoint));
        this.compoundAnimation.addAnimation(new KelvinAnimation(this.homeObject.frontInternal[3], fronInternalLowPoint, fronInternalLowPoint));

    }
}
