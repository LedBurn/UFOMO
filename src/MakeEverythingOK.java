import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MakeEverythingOK {

    public static void run(String soundsPath, final boolean runSimulator) {
        final Network network = new Network();
        network.configure();
        final Simulator s = runSimulator ? new Simulator() : null;
        final WavAudioSource audio = new WavAudioSource();

        try {
            audio.PlaySong(soundsPath + "everythingIsOKNow.wav");
            System.out.print("song is playing");

            final Totem t = new Totem();

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
            final EffectToObjectMapper mapperLeft = new EffectToObjectMapper(dsoe, t.GetAllPixels(), t.leftIndexes);
            final EffectToObjectMapper mapperRight = new EffectToObjectMapper(dsoe, t.GetAllPixels(), t.rightIndexes);

            System.out.println("start timer");

            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Double currentPos = audio.GetPositionSeconds();
                    if (currentPos != null) {
                        System.out.println("pos = " + currentPos);
                        double timePercent = (currentPos % 3.0) / 3.0;
                        mapperLeft.apply(timePercent);
                        mapperRight.apply(timePercent);
                        network.addSegment("test", t.GetRGBColors(0, 220), 2, 0);
                        if (runSimulator) {
                            s.draw(t);
                        }
                    } else {
                        timer.cancel();
                    }
                }
            }, 0, 20);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
