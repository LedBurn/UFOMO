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

        Totem totem = totems[0];
        int numOfPixels = totem.leftIndexes.length + totem.rightIndexes.length;

        Animation blackAnimation = new Animation();
        DiscreteConstColorEffect blackEffect = new DiscreteConstColorEffect(numOfPixels);
        blackEffect.configure(HSBColor.BLACK);
        blackAnimation.addMapper(new EffectToObjectMapper(blackEffect, totem.GetAllPixels(), totem.leftIndexes));
        blackAnimation.addMapper(new EffectToObjectMapper(blackEffect, totem.GetAllPixels(), totem.rightIndexes));
        timings.add(new AnimationTiming(blackAnimation, 0, 0.3));

        Animation firstAnimation = new Animation();
        firstAnimation.addAnimation(firstAnimation(true), 0.0 ,0.5);
        firstAnimation.addAnimation(firstAnimation(false), 0.5 ,1.0);
        timings.add(new AnimationTiming(firstAnimation, 0.3, 14.38));
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
