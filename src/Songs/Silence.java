public class Silence extends Song{

    public Silence(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        super(true, network, audio, simulator, soundsPath, keyPedServer);
    }

    @Override
    protected void configure() {

    }

    @Override
    protected String getAudioFileName() {
        return "silence.wav";
    }

}




