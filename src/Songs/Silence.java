import java.util.Arrays;
import java.util.concurrent.Callable;

public class Silence extends Song{

    public Silence(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        super(true, network, audio, simulator, soundsPath, keyPedServer);
    }

    @Override
    protected void configure() {
        Callable<AddonEffect> warmToBlue = () -> new AddonChangeHueOverLocation(new HSBColor(HSBColor.BLUE.hue, 0.5, 0.3), HSBColor.BLUE);
        Callable<AddonEffect> blueToWarm = () -> new AddonChangeHueOverLocation(HSBColor.BLUE, new HSBColor(HSBColor.BLUE.hue, 0.5, 0.3));

        addBeatAnimations(0.0, 60.0, 10, 0.0, 0.5, new AddonsContainerAllTotems(totems, Arrays.asList(warmToBlue)));
        addBeatAnimations(0.0, 60.0, 10, 0.5, 1.0, new AddonsContainerAllTotems(totems, Arrays.asList(blueToWarm)));

        addTiming(58.0, 60.0, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonFadeOut(0.0))));
    }

    @Override
    protected String getAudioFileName() {
        return "silence.wav";
    }

}




