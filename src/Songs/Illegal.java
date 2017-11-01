import java.util.Arrays;
import java.util.concurrent.Callable;

public class Illegal extends Song{

    public Illegal(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        super(false, network, audio, simulator, soundsPath, keyPedServer);
    }

    @Override
    protected void configure() {
        Callable<AddonEffect> badChoiceColor = () -> new AddonSetConstColor(new HSBColor(0.0, 1.0, 0.5));

        addTiming(0.7, 4.8, new AddonsContainerAllTotems(totems, Arrays.asList(badChoiceColor)));
    }

    @Override
    protected String getAudioFileName() {
        return "illegal.wav";
    }

}




