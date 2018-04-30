public class FreeStyleAnimations {


    private static final double CYCLE_TIME = 5000; //milliseconds
    private static final int NUM_OF_CYCLES = 3;

    private long currentAnimationStartTime = 0; // milliseconds
    private long nextAnimationStartTime = 0;

    private UFOMOAnimation currentAnimation;
    private UFOMOAnimation nextAnimation;



    // all is random
    private UFOMOAnimation randomNewAnimation() {
        int num = (int)Math.floor(Math.random() * 3);
        switch (num) {
            case 0: return RainbowAnimations.RAINBOW_ANIMATION1;
            case 1: return RainbowAnimations.RAINBOW_ANIMATION2;
            case 2: return RainbowAnimations.RAINBOW_ANIMATION3;
        }
        return null;
    }


    public void apply(UFOMOObject ufomoObject) {

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
            currentAnimation.apply(((currentTime - currentAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME);
            ufomoObject.copy(currentAnimation.ufomoObject);
        } else { // apply both animations
            currentAnimation.apply(((currentTime - currentAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME);
            nextAnimation.apply(((currentTime - nextAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME);
            double fadePercent = 1 - ((currentTime - currentAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME;
            System.out.println("fadePercent-"+fadePercent);
            ufomoObject.mergeAndCopy(currentAnimation.ufomoObject, nextAnimation.ufomoObject, fadePercent);
        }
    }

    private int getCycleNum(long currentTime, long animationStartTime) {
        return (int)(currentTime - animationStartTime) / (int)CYCLE_TIME;
    }
}
