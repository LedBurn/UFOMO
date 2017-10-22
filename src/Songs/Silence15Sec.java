public class Silence15Sec extends Song{

    public Silence15Sec(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        super(true, network, audio, simulator, soundsPath, keyPedServer);
    }

    @Override
    protected void configure() {

    }

    @Override
    protected String getAudioFileName() {
        return "silence_15sec.wav";
    }

}





