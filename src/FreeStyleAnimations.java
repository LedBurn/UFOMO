public class FreeStyleAnimations {


    private static final double CYCLE_TIME = 3000.0; //milliseconds
    private static final int NUM_OF_CYCLES = 10;

    private long currentAnimationStartTime = 0; // milliseconds
    private long nextAnimationStartTime = 0;

    private UFOMOAnimation currentAnimation;
    private UFOMOAnimation nextAnimation;

    private String[] highAnimations = {
            "Rainbow1", "Rainbow2","Rainbow3","Rainbow4",
            "Dandoo2",
            "Alternate1","Alternate2",
            "EQ1", "EQ2", "EQ3", "EQ4"
    };
    private String[] lowAnimations = {
            "Dandoo1", "Dandoo3",
            "Confetti1", "Confetti2"
    };


    // all is random
    private UFOMOAnimation randomNewAnimation() {
        String animationName;
        int num = (int)Math.floor(Math.random() * (highAnimations.length + lowAnimations.length));
        if (num < highAnimations.length) {
            animationName = highAnimations[num];
        } else {
            animationName = lowAnimations[num - highAnimations.length];
        }
//        animationName = "Dandoo3";

        System.out.println("New Animation - " + animationName);
        return getAnimation(animationName);
    }

    private UFOMOAnimation getAnimation(String name) {
        switch (name) {
            case "Rainbow1": return new RainbowAnimations.RainbowAnimation1(); // eq separate
            case "Rainbow2": return new RainbowAnimations.RainbowAnimation2(); // eq
            case "Rainbow3": return new RainbowAnimations.RainbowAnimation3();
            case "Rainbow4": return new RainbowAnimations.RainbowAnimation4();
            case "Dandoo1": return new DandooAnimations.DandooUFOMOAnimation1();
            case "Dandoo2": return new DandooAnimations.DandooUFOMOAnimation2();
            case "Dandoo3": return new DandooAnimations.DandooUFOMOAnimation3();
            case "Alternate1": return new AlternateAnimations.AlternateAnimation1(); // beat
            case "Alternate2": return new AlternateAnimations.AlternateAnimation2(); // beat
            case "Confetti1" : return new ConfettiAnimations.ConfettiAnimation1();
            case "Confetti2" : return new ConfettiAnimations.ConfettiAnimation2(); // beat
            case "EQ1": return new EqAnimations.EqAnimation1(); // eq separate
            case "EQ2": return new EqAnimations.EqAnimation2(); // eq
            case "EQ3": return new EqAnimations.EqAnimation3(); // eq separate
            case "EQ4": return new EqAnimations.EqAnimation4(); // eq separate
        }
        return null;
    }


    public void apply(UFOMOObject ufomoObject, boolean newBeat, boolean isOn, double percentToNextBeat, int[] eq) {

        long currentTime = System.currentTimeMillis();
        int currentCycleNum = getCycleNum(currentTime, currentAnimationStartTime);
        int nextCycleNum = getCycleNum(currentTime, nextAnimationStartTime);

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
            currentAnimation.apply(((currentTime - currentAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME, newBeat, isOn, eq);
            ufomoObject.copy(currentAnimation.ufomoObject);
        } else { // apply both animations
            if (newBeat) currentAnimation.newBeat();
            if (newBeat) nextAnimation.newBeat();
            currentAnimation.apply(((currentTime - currentAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME, newBeat, isOn, eq);
            nextAnimation.apply(((currentTime - nextAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME, newBeat, isOn, eq);
            double fadePercent = 1 - ((currentTime - currentAnimationStartTime) % CYCLE_TIME) / CYCLE_TIME;
            //System.out.println("fadePercent-"+fadePercent);
            ufomoObject.mergeAndCopy(currentAnimation.ufomoObject, nextAnimation.ufomoObject, fadePercent);
        }
    }

    private int getCycleNum(long currentTime, long animationStartTime) {
        return (int)Math.floor((currentTime - animationStartTime) / CYCLE_TIME);
    }
}
