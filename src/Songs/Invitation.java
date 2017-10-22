import java.util.Arrays;

public class Invitation extends Song{

    public Invitation(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        super(true, network, audio, simulator, soundsPath, keyPedServer);

    }

    @Override
    protected void configure() {

    }

    @Override
    protected String getAudioFileName() {
        return "hakuna.wav";
    }

}

