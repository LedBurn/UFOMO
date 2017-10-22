import java.util.Arrays;

public class Digit extends Song{

    public Digit(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer, int digit) {
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

    private int digit;
}
