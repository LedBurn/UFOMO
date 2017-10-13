import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Song {
    protected final Totem[] totems;
    private final Network network;
    private final WavAudioSource audio;
    private final Simulator simulator;
    private final String soundsPath;
    private final ISimulatedLEDObject simTotems;
    private Timer timer;

    public Song(Network network, WavAudioSource audio, Simulator simulator, String soundsPath) {
        this.network = network;
        this.audio = audio;
        this.simulator = simulator;
        this.soundsPath = soundsPath;

        totems = new Totem[1];
        for (int i=0; i<totems.length; i++) {
            totems[i] = new Totem();
        }

        simTotems = new SimulatedTotem(totems[0]);

        configure();
    }

    public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        audio.PlaySong(soundsPath + getAudioFileName());

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Double currentPos = audio.GetPositionSeconds();
                if (currentPos != null) {
                    apply(currentPos);
                    network.addSegment("test", totems[0].GetRGBColors(0, 220), 2, 0);
                    if (simulator != null) {
                        simulator.draw(simTotems);
                    }
                } else {
                    timer.cancel();
                }
            }
        }, 0, 20);
    }

    protected abstract String getAudioFileName();
    protected abstract void configure();
    protected abstract void apply(double currentPos);

}
