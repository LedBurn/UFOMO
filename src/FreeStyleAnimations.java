public class FreeStyleAnimations {


    private static final double CYCLE_TIME = 3000.0; //milliseconds
    private static final int NUM_OF_CYCLES = 10;

    private long currentAnimationStartTime = 0; // milliseconds
    private long nextAnimationStartTime = 0;

    private UFOMOAnimation currentAnimation;
    private UFOMOAnimation nextAnimation;

    private UFOMOAnimation[] highAnimations = {
            RainbowAnimations.RAINBOW_ANIMATION1,
            RainbowAnimations.RAINBOW_ANIMATION2,
            RainbowAnimations.RAINBOW_ANIMATION3,
            RainbowAnimations.RAINBOW_ANIMATION4,
            DandooAnimations.DANDOO_ANIMATION2,
            AlternateAnimations.ALTERNATE_ANIMATION2
    };
    private UFOMOAnimation[] lowAnimations = {
            DandooAnimations.DANDOO_ANIMATION1,
            DandooAnimations.DANDOO_ANIMATION3
    };


    // all is random
    private UFOMOAnimation randomNewAnimation() {
        return RoundRobinAnimations.ROUND_ROBIN_ANIMATION1;

//        int num = (int)Math.floor(Math.random() * highAnimations.length + lowAnimations.length);
//        if (num < highAnimations.length) {
//            return highAnimations[num];
//        }
//        return lowAnimations[num - highAnimations.length];
    }


    public void apply(UFOMOObject ufomoObject, boolean newBeat, double percentToNextBeat) {

        long currentTime = System.currentTimeMillis();
        int currentCycleNum = getCycleNum(currentTime, currentAnimationStartTime);
        int nextCycleNum = getCycleNum(currentTime, nextAnimationStartTime);
        System.out.println("current cycle - " + currentCycleNum + "             next cycle - " + nextCycleNum);


        if (currentAnimation == null) { // fresh start
            System.out.println("New Animation - fresh start");
            currentAnimation = randomNewAnimation();
            currentAnimationStartTime = currentTime;
            nextAnimation = null;
            nextAnimationStartTime = 0;

        } else if (currentCycleNum > NUM_OF_CYCLES) { // current animation is over
            if (nextCycleNum > NUM_OF_CYCLES || nextCycleNum < 0) { // next animation is over or not started - fresh animation
                System.out.println("New Animation - cycles are over");
                currentAnimation = randomNewAnimation();
                currentAnimationStartTime = currentTime;
                nextAnimation = null;
                nextAnimationStartTime = 0;

            } else { // next animation is running
                System.out.println("Fade out is done");
                currentAnimation = nextAnimation;
                currentAnimationStartTime = nextAnimationStartTime;
                nextAnimation = null;
                nextAnimationStartTime = 0;
            }
        } else if (currentCycleNum == NUM_OF_CYCLES && nextAnimation == null) { // last cycle
                System.out.println("New Animation");
                nextAnimation = randomNewAnimation();
                nextAnimationStartTime = currentTime;
        }


        if (nextAnimation == null) { // apply current only
            if (newBeat) currentAnimation.newBeat();
            currentAnimation.apply(((currentTime - currentAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME, newBeat);
            ufomoObject.copy(currentAnimation.ufomoObject);
        } else { // apply both animations
            if (newBeat) currentAnimation.newBeat();
            if (newBeat) nextAnimation.newBeat();
            currentAnimation.apply(((currentTime - currentAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME, newBeat);
            nextAnimation.apply(((currentTime - nextAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME, newBeat);
            double fadePercent = 1 - ((currentTime - currentAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME;
            //System.out.println("fadePercent-"+fadePercent);
            ufomoObject.mergeAndCopy(currentAnimation.ufomoObject, nextAnimation.ufomoObject, fadePercent);
        }
    }

    private int getCycleNum(long currentTime, long animationStartTime) {
        return (int)Math.floor((currentTime - animationStartTime) / CYCLE_TIME);
    }
}
