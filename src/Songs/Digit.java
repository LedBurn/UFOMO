import java.lang.reflect.Array;
import java.util.Arrays;

public class Digit extends Song{

    public Digit(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer, int digit) {
        super(false, network, audio, simulator, soundsPath, keyPedServer);
        this.digit = digit;
    }

    @Override
    protected void configure() {
        addTiming(0, 100, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonSetConstColor(HSBColor.GRAY))));
    }

    @Override
    protected String getAudioFileName() {
        return Integer.toString(this.digit) + ".wav";
    }

    private int digit;
}
