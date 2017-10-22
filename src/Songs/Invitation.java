import java.util.Arrays;

public class Invitation extends Song{

    public Invitation(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer, int callNumber) {
        super(true, network, audio, simulator, soundsPath, keyPedServer);
        this.callNumber = callNumber;
    }

    @Override
    protected void configure() {

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

