import java.util.Arrays;
import java.util.concurrent.Callable;

public class Rachel extends Song{

    public Rachel(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        super(false, network, audio, simulator, soundsPath, keyPedServer);
    }

    @Override
    protected void configure() {
        Callable<AddonEffect> pink = () -> new AddonSetConstColor(HSBColor.PINK);
        Callable<AddonEffect> yellow = () -> new AddonSetConstColor(HSBColor.YELLOW);
        Callable<AddonEffect> confetti = () -> new AddonConfetti(5.0, 0.2, true);
        Callable<AddonEffect> glitterUp = () -> new AddonSaturationGlitter(2.0, 0.05);


        addTiming(00.823, 11.426, new AddonsContainerAllTotems(totems, Arrays.asList(pink, confetti)));
        addTiming(12.100, 15.213, new AddonsContainerAllTotems(totems, Arrays.asList(yellow, confetti)));
        addBeatAnimations(12.100, 15.213, 7, 00.0, 1.0, new AddonsContainerAllTotems(totems, Arrays.asList(glitterUp)));
    }

    @Override
    protected String getAudioFileName() {
        return "rachel.wav";
    }

}


