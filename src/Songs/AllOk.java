public class AllOk extends Song{

    public AllOk(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        super(false, network, audio, simulator, soundsPath, keyPedServer);
    }

    @Override
    protected void configure() {

    }

    @Override
    protected String getAudioFileName() {
        return "all_ok.wav";
    }

}





