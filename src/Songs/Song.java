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
    private final KeyPedServer keyPedServer;
    private final boolean allowInterupt;

    private Timer timer;

    public Song(boolean allowInterupt, Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        this.network = network;
        this.audio = audio;
        this.simulator = simulator;
        this.soundsPath = soundsPath;
        this.keyPedServer = keyPedServer;
        this.allowInterupt = allowInterupt;

        totems = new Totem[8];
        for (int i=0; i<totems.length; i++) {
            totems[i] = new Totem();
        }

        simTotems = new SimulatedTotem(totems[0]);

        configure();
    }

    public Integer play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        audio.PlaySong(soundsPath + getAudioFileName());

        if(this.allowInterupt) {
            this.keyPedServer.clearCurrentInupt();
        }

        timer = new Timer();

        while(true) {
            if(allowInterupt) {
                Integer userChoice = keyPedServer.checkForInput();
                if(userChoice != null) {
                    System.out.println("stop playing current song due to user interrupt. userChoice: " + userChoice);
                    audio.StopSong();
                    timer.cancel();
                    return userChoice;
                }
            }

            Double currentPos = audio.GetPositionSeconds();
            if (currentPos != null) {
                apply(currentPos);
                network.addSegment("test", totems[0].GetRGBColors(0, 220), 0, 0);
                network.addSegment("test", totems[1].GetRGBColors(0, 220), 1, 0);
                network.addSegment("test", totems[2].GetRGBColors(0, 220), 2, 0);
                network.addSegment("test", totems[3].GetRGBColors(0, 220), 3, 0);
                network.addSegment("test", totems[4].GetRGBColors(0, 220), 4, 0);
                network.addSegment("test", totems[5].GetRGBColors(0, 220), 5, 0);
                network.addSegment("test", totems[6].GetRGBColors(0, 220), 6, 0);
                network.addSegment("test", totems[7].GetRGBColors(0, 220), 7, 0);
                network.addSegment("test", totems[0].GetRGBColors(0, 220), 8, 0);
                network.addSegment("test", totems[1].GetRGBColors(0, 220), 9, 0);
                network.addSegment("test", totems[2].GetRGBColors(0, 220), 10, 0);
                network.addSegment("test", totems[3].GetRGBColors(0, 220), 11, 0);
                network.addSegment("test", totems[4].GetRGBColors(0, 220), 12, 0);
                network.addSegment("test", totems[5].GetRGBColors(0, 220), 13, 0);
                network.addSegment("test", totems[6].GetRGBColors(0, 220), 14, 0);
                network.addSegment("test", totems[7].GetRGBColors(0, 220), 15, 0);
                network.addSegment("test", totems[0].GetRGBColors(0, 220), 16, 0);
                network.addSegment("test", totems[1].GetRGBColors(0, 220), 17, 0);
                network.addSegment("test", totems[2].GetRGBColors(0, 220), 18, 0);
                network.addSegment("test", totems[3].GetRGBColors(0, 220), 19, 0);
                network.addSegment("test", totems[4].GetRGBColors(0, 220), 20, 0);
                network.addSegment("test", totems[5].GetRGBColors(0, 220), 21, 0);
                network.addSegment("test", totems[6].GetRGBColors(0, 220), 22, 0);
                network.addSegment("test", totems[7].GetRGBColors(0, 220), 23, 0);
                if (simulator != null) {
                    simulator.draw(simTotems);
                }
                network.send();
            } else {
                return null;
            }

            try {
                Thread.sleep(30);
            }
            catch (java.lang.InterruptedException interrupt) {
                return null;
            }

        }
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
