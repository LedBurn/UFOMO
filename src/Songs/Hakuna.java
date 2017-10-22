import java.util.Arrays;

public class Hakuna extends Song{

    public Hakuna(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        super(false, network, audio, simulator, soundsPath, keyPedServer);

    }

    @Override
    protected void configure() {
        addTiming(0, 100, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonNature(), () -> new AddonBlur())));
    }

    @Override
    protected String getAudioFileName() {
        return "hakuna.wav";
    }

}
