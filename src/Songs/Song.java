import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Song {
    protected final Totem[] totems;
    protected final ArrayList<AddonTiming> timings = new ArrayList<>();

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

        totems = new Totem[8];
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
                    network.addSegment("test", totems[0].GetRGBColors(0, 220), 0, 0);
                    network.addSegment("test", totems[1].GetRGBColors(0, 220), 4, 0);
                    network.addSegment("test", totems[2].GetRGBColors(0, 220), 2, 0);
                    if (simulator != null) {
                        simulator.draw(simTotems);
                    }
                    network.send();
                } else {
                    timer.cancel();
                }
            }
        }, 0, 20);
    }

    private void apply(double currentPos) {
        for(Totem t: this.totems) {
            t.clear();
        }
        for(AddonTiming timing : timings) {
            timing.apply(currentPos);
        }
    }

    protected void addBeatAnimations(double from, double to, int numberOfBeats, double inBeatFrom, double inBeatTo, AddonsContainer container) {
        double eachBeatTime = (to-from) /  numberOfBeats;
        for(int i=0; i<numberOfBeats; i++) {
            double currFromTime = from + i*eachBeatTime + inBeatFrom * eachBeatTime;
            double currToTime = from + i*eachBeatTime + inBeatTo * eachBeatTime;
            this.timings.add(new AddonTiming(container, currFromTime, currToTime));
        }
    }

    protected void addMultiSectionsTiming(double from, double to, AddonsContainer containers[]) {
        double eachBeatTime = (to-from) /  containers.length;
        for(int i=0; i < containers.length; i++) {
            AddonsContainer currContainer = containers[i];
            double currFromTime = from + i * eachBeatTime;
            double currToTime = currFromTime + eachBeatTime;
            this.timings.add(new AddonTiming(containers[i], currFromTime, currToTime));
        }
    }

    protected void addTiming(double from, double to, AddonsContainer container) {
        this.timings.add(new AddonTiming(container, from, to));
    }

    protected abstract String getAudioFileName();
    protected abstract void configure();


}
