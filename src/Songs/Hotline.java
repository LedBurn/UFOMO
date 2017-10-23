import java.util.Arrays;

public class Hotline extends Song{

    public Hotline(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        super(true, network, audio, simulator, soundsPath, keyPedServer);
    }

    @Override
    protected void configure() {

    }

    @Override
    protected String getAudioFileName() {
        return "hotline_new.wav";
    }

}

