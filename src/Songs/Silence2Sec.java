public class Silence2Sec extends Song{

    public Silence2Sec(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        super(false, network, audio, simulator, soundsPath, keyPedServer);
    }

    @Override
    protected void configure() {

    }

    @Override
    protected String getAudioFileName() {
        return "silence_15sec.wav";
    }

}





