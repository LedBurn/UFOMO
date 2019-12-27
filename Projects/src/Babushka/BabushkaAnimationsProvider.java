public class BabushkaAnimationsProvider implements ISimpleRunnerAnimationsProvider {

    @Override
    public Animation randomNewAnimation() {
        int num = (int)Math.floor(Math.random() * 35);
//        num = 32;
        switch (num) {
            case 0:
                return new BabushkaRainbowAnimations.RainbowAnimationAll(new BabushkaObject());
            case 1:
                return new BabushkaRainbowAnimations.RainbowAnimationEqualDiff(new BabushkaObject());
            case 2:
            case 3:
                return new BabushkaRainbowAnimations.RainbowAnimationSmallDiff(new BabushkaObject());
            case 4:
            case 5:
                return new BabushkaRippleAnimations.Ripple1(new BabushkaObject());
            case 6:
            case 7:
                return new BabushkaRippleAnimations.Ripple2(new BabushkaObject());
            case 8:
            case 9:
                return new BabushkaConstAnimations.ConstAnimation(new BabushkaObject());
            case 10:
            case 11:
                return new BabushkaDancingDotAnimations.DancingDotMultiple(new BabushkaObject());
            case 12:
            case 13:
                return new BabushkaDancingDotAnimations.DancingDotCrazy(new BabushkaObject());
            case 14:
            case 15:
                return new BabushkaBuildingAnimation.BuildingAnimation(new BabushkaObject());
            case 16:
                return new BabushkaAlternateAnimations.AlternateAnimationHalf(new BabushkaObject());
            case 17:
                return new BabushkaAlternateAnimations.AlternateAnimationFull(new BabushkaObject());
            case 18:
                return new BabushkaAlternateAnimations.AlternateAnimationHalfMove(new BabushkaObject());
            case 19:
            case 20:
            case 35:
                return new BabushkaAlternateAnimations.AlternateAnimationHalfJump(new BabushkaObject());
            case 21:
            case 22:
                return new BabushkaRippleAnimations.RippleIn(new BabushkaObject());
            case 23:
            case 24:
                return new BabushkaRippleAnimations.RippleOut(new BabushkaObject());
            case 25:
            case 26:
                return new BabushkaRippleAnimations.RippleInOut(new BabushkaObject());
            case 27:
                return new BabushkaBuildingAnimation.BuildingAnimationHalf1(new BabushkaObject());
            case 28:
            case 29:
                return new BabushkaBuildingAnimation.BuildingAnimationHalf2(new BabushkaObject());
            case 30:
                return new BabushkaConfettiAnimations.ConfettiAnimation1(new BabushkaObject());
            case 31:
                return new BabushkaConfettiAnimations.ConfettiAnimation2(new BabushkaObject());
            case 32:
            case 33:
            case 34:
                return new BabushkaRoundRobbinAnimations.BabushkaRoundRobbinAnimation(new BabushkaObject());
        }
        return null;
    }

    @Override
    public void handleUserCode(int code) {

    }
}
