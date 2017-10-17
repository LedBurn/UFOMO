import java.util.concurrent.Callable;

public class PenPineappleApplePen extends Song {

    public PenPineappleApplePen(Network network, WavAudioSource audio, Simulator simulator, String soundsPath) {
        super(network, audio, simulator, soundsPath);


    }
    // beat 0.88
    // 0.3 * 8
    // 7.34 * 8
    // 14.38
    // 21.27


    @Override
    protected void configure() {

        int pixelsPerSide = this.totems[0].GetPixelsNumber() / 2;

        ContinuousConstColorEffect blue = new ContinuousConstColorEffect(HSBColor.BLUE);
        ContinuousConstColorEffect red = new ContinuousConstColorEffect(HSBColor.RED);
        ContinuousConstColorEffect yellow = new ContinuousConstColorEffect(HSBColor.YELLOW);

        ContinuousFadeInEffect fadeBlue = new ContinuousFadeInEffect(blue);
        ContinuousFadeInEffect fadeYellow = new ContinuousFadeInEffect(yellow);

        ContinuousSpikeEffect spikeBlue = new ContinuousSpikeEffect(blue);
        spikeBlue.configure(0.0, 2.0, 1.0);
        ContinuousSpikeEffect spikeRed = new ContinuousSpikeEffect(red);
        spikeRed.configure(1.0, -1.0, 1.0);
        ContinuousSpikeEffect spikeYellow = new ContinuousSpikeEffect(yellow);
        spikeYellow.configure(1.0, -1.0, 1.0);


        // pen apple
        timingsAmir.add(new AnimationTimingAmir(new HalfTotemsAnimation(this.totems, blue.getAsDiscrete(pixelsPerSide), true), 0.3, 2.066));
        timingsAddons.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonFadeInEffect()}), 0.3, 2.066));
        timingsAmir.add(new AnimationTimingAmir(new HalfTotemsAnimation(this.totems, red.getAsDiscrete(pixelsPerSide), false), 2.066, 3.822));
        timingsAddons.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonFadeInEffect()}), 2.066, 3.822));

        timingsAmir.add(new AnimationTimingAmir(new AllTotemsAnimation(this.totems, spikeBlue.getAsDiscrete(pixelsPerSide), true, false), 4.675, 5.146));
        timingsAmir.add(new AnimationTimingAmir(new AllTotemsAnimation(this.totems, spikeRed.getAsDiscrete(pixelsPerSide), false, true), 4.675, 5.146));

        DiscreteAlternateEffect alternateRedBlue = new DiscreteAlternateEffect(pixelsPerSide, red.getAsDiscrete(pixelsPerSide), blue.getAsDiscrete(pixelsPerSide));
        alternateRedBlue.configure(5, 2);
        timingsAmir.add(new AnimationTimingAmir(new AllTotemsAnimation(this.totems, alternateRedBlue, true, true), 5.558, 6.920));

        // pen pineapple
        timingsAmir.add(new AnimationTimingAmir(new HalfTotemsAnimation(this.totems, fadeBlue.getAsDiscrete(pixelsPerSide), true), 7.373, 9.117));
        timingsAmir.add(new AnimationTimingAmir(new HalfTotemsAnimation(this.totems, fadeYellow.getAsDiscrete(pixelsPerSide), false), 9.117, 10.886));

        timingsAmir.add(new AnimationTimingAmir(new AllTotemsAnimation(this.totems, spikeBlue.getAsDiscrete(pixelsPerSide), true, false), 11.769, 12.214));
        timingsAmir.add(new AnimationTimingAmir(new AllTotemsAnimation(this.totems, spikeYellow.getAsDiscrete(pixelsPerSide), false, true), 11.769, 12.214));

        DiscreteAlternateEffect alternateBlueYellow = new DiscreteAlternateEffect(pixelsPerSide, blue.getAsDiscrete(pixelsPerSide), yellow.getAsDiscrete(pixelsPerSide));
        alternateBlueYellow.configure(5, 2);
        timingsAmir.add(new AnimationTimingAmir(new AllTotemsAnimation(this.totems, alternateBlueYellow, true, true), 12.600, 13.971));

        // apple pen
        double penAnimationEnd = 15.689 + 0.3;
        timingsAmir.add(new AnimationTimingAmir(new AllTotemsAnimation(this.totems, alternateRedBlue, true, true), 14.412, penAnimationEnd));
        timingsAddons.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonClearS2SEffect()}), 15.689, penAnimationEnd));

        // pineapple pen
        double pineappleAnimationEnd = 17.500 + 0.3;
        timingsAmir.add(new AnimationTimingAmir(new AllTotemsAnimation(this.totems, alternateBlueYellow, true, true), 16.143, pineappleAnimationEnd));
        timingsAddons.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonClearS2SEffect()}), 17.500, pineappleAnimationEnd));

        // Ahhhh
        AddonEffect randColor = new AddonRandomColorFromList(new HSBColor[]{HSBColor.RED, HSBColor.BLUE, HSBColor.YELLOW});
        AddonRandomOnPixels randOn = new AddonRandomOnPixels(0.5);
        AddonsContainerAllTotems ahhhEffects = new AddonsContainerAllTotems(totems, new AddonEffect[]{randColor, randOn});
        timingsAddons.add(new AddonTiming(ahhhEffects, 18.820, 21.475));

        AddonCyclicMove move = new AddonCyclicMove();
        timingsAddons.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{move}), 19.715, 21.475));

        Totem totem = totems[0];
        int numOfPixels = totem.leftIndexes.length + totem.rightIndexes.length;

        Animation blackAnimation = new Animation();
        DiscreteConstColorEffect blackEffect = new DiscreteConstColorEffect(numOfPixels);
        blackEffect.configure(HSBColor.BLACK);
        blackAnimation.addMapper(new EffectToObjectMapper(blackEffect, totem.GetAllPixels(), totem.leftIndexes));
        blackAnimation.addMapper(new EffectToObjectMapper(blackEffect, totem.GetAllPixels(), totem.rightIndexes));
        timings.add(new AnimationTiming(blackAnimation, 0, 5.0));

        Animation firstAnimation = new Animation();
        firstAnimation.addAnimation(firstAnimation(true), 0.0 ,0.5);
        firstAnimation.addAnimation(firstAnimation(false), 0.5 ,1.0);
        timings.add(new AnimationTiming(firstAnimation, 5.0, 14.38));
    }

    @Override
    protected String getAudioFileName() {
        return "pen.wav";
    }


    private Animation firstAnimation(boolean forward) {
        Totem totem = totems[0];
        Animation animation = new Animation();
        ContinuousWhiteEffect cwe = new ContinuousWhiteEffect();
        ContinuousRainbowEffect cre = new ContinuousRainbowEffect(cwe);
        ContinuousConstLocationOffsetEffect rainbowWithOffset = new ContinuousConstLocationOffsetEffect(cre);
        rainbowWithOffset.configure(0.5);
        ContinuousCyclicMoveEffect cme1 = new ContinuousCyclicMoveEffect(cre);
        cme1.configure(1, forward);
        ContinuousCyclicMoveEffect cme2 = new ContinuousCyclicMoveEffect(rainbowWithOffset);
        cme2.configure(1, forward);
        ContinuousToDiscrete ctd1 = new ContinuousToDiscrete(110, cme1);
        ContinuousToDiscrete ctd2 = new ContinuousToDiscrete(110, cme2);
        DiscreteAlternateEffect dsoe = new DiscreteAlternateEffect(110, ctd1, ctd2);
        dsoe.configure(4, 5);
        animation.addMapper(new EffectToObjectMapper(dsoe, totem.GetAllPixels(), totem.leftIndexes));
        animation.addMapper(new EffectToObjectMapper(dsoe, totem.GetAllPixels(), totem.leftIndexes));
        return animation;
    }
}
