import java.util.Date;
//import java.awt.*;

public class MakeEverythingOK {

    public static void main(String[] args) {
        Network network = new Network();
        network.configure();

        WavAudioSource audio = new WavAudioSource();
        try {
            audio.PlaySong("/home/amir/Downloads/Muumimusiikkia 20.wav");

            // main loop
            Date lastLoopTime = new Date();
            Totem t = new Totem();

            ContinuousWhiteEffect cwe = new ContinuousWhiteEffect();
            ContinuousRainbowEffect cre = new ContinuousRainbowEffect(cwe);
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
            EffectToObjectMapper mapperLeft = new EffectToObjectMapper(dsoe, t.GetAllPixels(), t.leftIndexes);
            EffectToObjectMapper mapperRight = new EffectToObjectMapper(dsoe, t.GetAllPixels(), t.rightIndexes);
            while(true) {
                Double currentPos = audio.GetPositionSeconds();
                if(currentPos != null) {
                    double timePercent = (currentPos % 3.0) / 3.0;
                    mapperLeft.apply(timePercent);
                    mapperRight.apply(timePercent);
                    network.addSegment("test", t.GetRGBColors(0, 220), 2, 0);
                    network.send();
                    Date now = new Date();
                    long msSinceLastSend = now.getTime() - lastLoopTime.getTime();
                    long waitTime = Math.max(20 - msSinceLastSend, (long)0);
                    Thread.sleep(waitTime);
                    lastLoopTime = new Date();
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
