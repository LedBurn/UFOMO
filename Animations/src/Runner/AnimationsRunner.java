

// Runs random animations.
// You must:
// 1. pass an IAnimationsProvider in the constructor, and provide animation.
// 2. call apply in the main loop.
// You can call setNewAnimation anytime to force a new animation.
public class AnimationsRunner {

    private static final double CYCLE_TIME = 3000.0; //milliseconds
    private static final int NUM_OF_CYCLES = 5;

    private long currentAnimationStartTime = 0; // milliseconds
    private long nextAnimationStartTime = 0;

    private Animation currentAnimation;
    private Animation nextAnimation;

    private IAnimationsProvider provider;

    public AnimationsRunner(IAnimationsProvider provider) {
        this.provider = provider;
    }

    // force a new animation to start now
    public void setNewAnimation(Animation animation) {
        currentAnimation = animation;
        currentAnimationStartTime = System.currentTimeMillis();
        nextAnimation = null;
        nextAnimationStartTime = 0;
    }


    // the main apply method
    public void apply(ILEDObject ledObject, boolean newBeat, boolean isOn, int[] eq) {

        long currentTime = System.currentTimeMillis();
        int currentCycleNum = getCycleNum(currentTime, currentAnimationStartTime);
        int nextCycleNum = getCycleNum(currentTime, nextAnimationStartTime);

        if (currentAnimation == null) { // fresh start
            setNewAnimation(provider.randomNewAnimation());
            System.out.println("New Animation - " + currentAnimation.getClass().getName());

        } else if (currentCycleNum > NUM_OF_CYCLES) { // current animation is over
            if (nextCycleNum > NUM_OF_CYCLES || nextCycleNum < 0) { // next animation is over or not started - fresh animation
                System.out.println("New PixelsArrayAnimation - cycles are over");
                setNewAnimation(provider.randomNewAnimation());
                System.out.println("New Animation - " + currentAnimation.getClass().getName());

            } else { // next animation is running
                currentAnimation = nextAnimation;
                currentAnimationStartTime = nextAnimationStartTime;
                nextAnimation = null;
                nextAnimationStartTime = 0;
            }

        } else if (currentCycleNum == NUM_OF_CYCLES && nextAnimation == null) { // last cycle
            nextAnimation = provider.randomNewAnimation();
            nextAnimationStartTime = currentTime;
            System.out.println("New Animation - " + nextAnimation.getClass().getName());
        }

        if (nextAnimation == null) { // apply current only
            if (newBeat) currentAnimation.newBeat();
            currentAnimation.apply(((currentTime - currentAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME, newBeat, isOn, eq);
            ledObject.copy(currentAnimation.ledObject);

        } else { // apply both animations
            if (newBeat) currentAnimation.newBeat();
            if (newBeat) nextAnimation.newBeat();
            currentAnimation.apply(((currentTime - currentAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME, newBeat, isOn, eq);
            nextAnimation.apply(((currentTime - nextAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME, newBeat, isOn, eq);
            double fadePercent = 1 - ((currentTime - currentAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME;
            ledObject.mergeAndCopy(currentAnimation.ledObject, nextAnimation.ledObject, fadePercent);
        }
    }

    private int getCycleNum(long currentTime, long animationStartTime) {
        return (int)Math.floor((currentTime - animationStartTime) / CYCLE_TIME);
    }
}
