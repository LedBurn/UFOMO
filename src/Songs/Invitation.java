import java.util.Arrays;
import java.util.concurrent.Callable;

public class Invitation extends Song{

    public Invitation(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer, int callNumber) {
        super(true, network, audio, simulator, soundsPath, keyPedServer);
        this.callNumber = callNumber;
    }

    @Override
    protected void configure() {
        Callable<AddonEffect> warmWhite = () -> new AddonSetConstColor(new HSBColor(0.0, 0.0, 0.3));
        Callable<AddonEffect> fadeIn = () -> new AddonFadeInEffect();
        Callable<AddonEffect> blur = () -> new AddonBlur();

        addTiming(0.0, 100.0, new AddonsContainerAllTotems(totems, Arrays.asList(warmWhite, blur)));
        addTiming(0.0, 2, new AddonsContainerAllTotems(totems, Arrays.asList(fadeIn)));
    }

    @Override
    protected String getAudioFileName() {
        switch (this.callNumber % 3) {
            case 0:
                return "start_1.wav";
            case 1:
                return "start_2.wav";
            default:
                return "start_3.wav";
        }
    }

    private int callNumber;
}

