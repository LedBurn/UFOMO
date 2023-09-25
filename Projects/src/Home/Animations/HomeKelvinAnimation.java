import java.util.Map;

public class HomeKelvinAnimation extends HomeAnimation {

    private final KelvinAnimation[] animations = new KelvinAnimation[13];

    public HomeKelvinAnimation(HomeObject homeObject, Map<String,String> userInput) {
        super(homeObject, userInput);

        animations[0] = new KelvinAnimation(this.homeObject.ceiling[0], 0, 0);
        animations[1] = new KelvinAnimation(this.homeObject.ceiling[1], 0, 0);
        animations[2] = new KelvinAnimation(this.homeObject.ceilingReversed[2], 0, 0);
        animations[3] = new KelvinAnimation(this.homeObject.ceiling[3], 0, 0);

        animations[4] = new KelvinAnimation(this.homeObject.frontExternal[0], 0, 0);
        animations[5] = new KelvinAnimation(this.homeObject.frontExternal[1], 0, 0);
        animations[6] = new KelvinAnimation(this.homeObject.frontExternal[2], 0, 0);;
        animations[7] = new KelvinAnimation(this.homeObject.frontExternalReversed[3], 0, 0);
        animations[8] = new KelvinAnimation(this.homeObject.frontExternal[4], 0, 0);

        animations[9] = new KelvinAnimation(this.homeObject.frontInternal[0], 0, 0);
        animations[10] = new KelvinAnimation(this.homeObject.frontInternal[1], 0, 0);
        animations[11] = new KelvinAnimation(this.homeObject.frontInternalReversed[2], 0, 0);
        animations[12] = new KelvinAnimation(this.homeObject.frontInternal[3], 0, 0);


        for (KelvinAnimation animation : animations) {
            this.compoundAnimation.addAnimation(animation);
        }
        this.userInputUpdated(userInput);

    }

    @Override
    public void userInputUpdated(Map<String, String> userInput) {
        double center = Double.parseDouble(userInput.get("center"));
        double range = Double.parseDouble(userInput.get("range"));

        // the ceiling take 1/4 of the range
        double ceilingHighPoint = center + range / 2;
        double ceilingLowPoint = center + range / 2;

        // the front takes 3/4 of the range
        double frontHighPoint = center + range / 2;
        double frontLowPoint = center - range / 2;

        // the middle front start after 0.32 from the front top and 0.25 from the bottom
        double frontInternalHighPoint = frontHighPoint - (frontHighPoint - frontLowPoint) * 0.32;
        double fronInternalLowPoint = frontLowPoint + (frontHighPoint - frontLowPoint) * 0.25;

        animations[0].updateParams(ceilingLowPoint, ceilingHighPoint);
        animations[1].updateParams(ceilingHighPoint, ceilingHighPoint);
        animations[2].updateParams(ceilingLowPoint, ceilingHighPoint);
        animations[3].updateParams(ceilingLowPoint, ceilingLowPoint);

        animations[4].updateParams(frontLowPoint, frontLowPoint);
        animations[5].updateParams(frontLowPoint, frontHighPoint);
        animations[6].updateParams(frontHighPoint, frontHighPoint);
        animations[7].updateParams(frontLowPoint, frontHighPoint);
        animations[8].updateParams(frontLowPoint, frontLowPoint);

        animations[9].updateParams(fronInternalLowPoint, frontInternalHighPoint);
        animations[10].updateParams(frontInternalHighPoint, frontInternalHighPoint);
        animations[11].updateParams(fronInternalLowPoint, frontInternalHighPoint);
        animations[12].updateParams(fronInternalLowPoint, fronInternalLowPoint);
    }
}
