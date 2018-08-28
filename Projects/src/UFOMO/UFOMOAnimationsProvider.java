

public class UFOMOAnimationsProvider implements IAnimationsProvider {

    private String[] highAnimations = {
            "Rainbow1", "Rainbow2","Rainbow3","Rainbow4",
            "Dandoo2",
            "Alternate1","Alternate2",
            "EQ1", "EQ2", "EQ3", "EQ4", "EQ5",
            "EQHue1", "EQHue2",
            "Spikes3",
            "Random1"
    };
    private String[] lowAnimations = {
            "Dandoo1", "Dandoo3",
            "Confetti1", "Confetti2", "Confetti3",
            "Spikes1", "Spikes2", "Spikes4", "Spikes5"
    };

    private Animation<UFOMOObject> getAnimationByName(String name) {
        switch (name) {
            case "Rainbow1":
                return new RainbowAnimations.RainbowAnimation1(new UFOMOObject()); // eq separate
            case "Rainbow2":
                return new RainbowAnimations.RainbowAnimation2(new UFOMOObject()); // eq
            case "Rainbow3":
                return new RainbowAnimations.RainbowAnimation3(new UFOMOObject());
            case "Rainbow4":
                return new RainbowAnimations.RainbowAnimation4(new UFOMOObject());
            case "Dandoo1":
                return new DandooAnimations.DandooUFOMOAnimation1(new UFOMOObject()); // eq
            case "Dandoo2":
                return new DandooAnimations.DandooUFOMOAnimation2(new UFOMOObject()); // eq
            case "Dandoo3":
                return new DandooAnimations.DandooUFOMOAnimation3(new UFOMOObject()); // eq
            case "Alternate1":
                return new AlternateAnimations.AlternateAnimation1(new UFOMOObject()); // beat
            case "Alternate2":
                return new AlternateAnimations.AlternateAnimation2(new UFOMOObject()); // beat
            case "Confetti1":
                return new ConfettiAnimations.ConfettiAnimation1(new UFOMOObject());
            case "Confetti2":
                return new ConfettiAnimations.ConfettiAnimation2(new UFOMOObject()); // beat
            case "Confetti3":
                return new ConfettiAnimations.ConfettiAnimation3(new UFOMOObject()); // beat
            case "EQ1":
                return new EqAnimations.EqAnimation1(new UFOMOObject()); // eq separate
            case "EQ2":
                return new EqAnimations.EqAnimation2(new UFOMOObject()); // eq
            case "EQ3":
                return new EqAnimations.EqAnimation3(new UFOMOObject()); // eq separate
            case "EQ4":
                return new EqAnimations.EqAnimation4(new UFOMOObject()); // eq separate
            case "EQ5":
                return new EqAnimations.EqAnimation5(new UFOMOObject()); // eq separate
            case "EQHue1":
                return new EQHueAnimations.EqHueAnimation1(new UFOMOObject()); // eq;
            case "EQHue2":
                return new EQHueAnimations.EqHueAnimation2(new UFOMOObject()); // eq;
            case "Spikes1":
                return new SpikesAnimations.SpikeAnimation1(new UFOMOObject()); // beat;
            case "Spikes2":
                return new SpikesAnimations.SpikeAnimation2(new UFOMOObject()); // beat;
            case "Spikes3":
                return new SpikesAnimations.SpikeAnimation3(new UFOMOObject()); // beat;
            case "Spikes4":
                return new SpikesAnimations.SpikeAnimation4(new UFOMOObject()); // eq separate;
            case "Spikes5":
                return new SpikesAnimations.SpikeAnimation5(new UFOMOObject()); // eq;
            case "Random1":
                return new RandomAnimations.RandomAnimation1(new UFOMOObject());
        }
        return null;
    }


    @Override
    public Animation randomNewAnimation() {
        String animationName;
        int num = (int)Math.floor(Math.random() * (highAnimations.length + lowAnimations.length));
        if (num < highAnimations.length) {
            animationName = highAnimations[num];
        } else {
            animationName = lowAnimations[num - highAnimations.length];
        }

        System.out.println("New PixelsArrayAnimation - " + animationName);
        return getAnimationByName(animationName);
    }

//    public void newAnimation(String type) {
//        System.out.println("New PixelsArrayAnimation - code");
//        UFOMOAnimation animation = randomNewAnimation();
//        if (type != null && type.equals("High")) {
//            animation = getAnimation(highAnimations[(int)Math.floor(Math.random() * highAnimations.length)]);
//        } else if (type != null && type.equals("Low")) {
//            animation = getAnimation(lowAnimations[(int)Math.floor(Math.random() * lowAnimations.length)]);
//        } else if (type != null) {
//            animation = getAnimation(type) != null ? getAnimation(type) : animation;
//        }
//    }
}
