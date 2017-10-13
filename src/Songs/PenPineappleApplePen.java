public class PenPineappleApplePen extends Song {

    private EffectToObjectMapper mapperLeft;
    private EffectToObjectMapper mapperRight;

    public PenPineappleApplePen(Network network, WavAudioSource audio, Simulator simulator, String soundsPath) {
        super(network, audio, simulator, soundsPath);
    }

    @Override
    protected void configure() {
        ContinuousWhiteEffect cwe = new ContinuousWhiteEffect();
        ContinuousFadeOutEffect cfie = new ContinuousFadeOutEffect(cwe);
        ContinuousRainbowEffect cre = new ContinuousRainbowEffect(cfie);
        ContinuousConstLocationOffsetEffect rainbowWithOffset = new ContinuousConstLocationOffsetEffect(cre);
        rainbowWithOffset.configure(0.5);
        ContinuousCyclicMoveEffect cme1 = new ContinuousCyclicMoveEffect(cre);
        cme1.configure(1, true);
        ContinuousCyclicMoveEffect cme2 = new ContinuousCyclicMoveEffect(rainbowWithOffset);
        cme2.configure(1, true);
        ContinuousToDiscrete ctd1 = new ContinuousToDiscrete(110, cme1);
        ContinuousToDiscrete ctd2 = new ContinuousToDiscrete(110, cme2);

        DiscreteConstColorEffect blackDiscrete = new DiscreteConstColorEffect(110);
        DiscreteAlternateEffect dsoe = new DiscreteAlternateEffect(110, ctd1, ctd2);
        dsoe.configure(1, 2);

        DiscreteConfettiEffect dce = new DiscreteConfettiEffect(110, ctd1);
        mapperLeft = new EffectToObjectMapper(ctd1, totems[0].GetAllPixels(), totems[0].leftIndexes);
        mapperRight = new EffectToObjectMapper(ctd1, totems[0].GetAllPixels(), totems[0].rightIndexes);
    }

    @Override
    protected String getAudioFileName() {
        return "pen.wav";
    }

    @Override
    protected void apply(double currentPos) {
        double timePercent = (currentPos % 10.0) / 10.0;
        if(timePercent < this.lastTimePercent) {
            configure();
        }
        this.lastTimePercent = timePercent;
        mapperLeft.apply(timePercent);
        mapperRight.apply(timePercent);
    }

    private double lastTimePercent = 0.0;
}