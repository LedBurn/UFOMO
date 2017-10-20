import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MakeEverythingOK {

    public static void run(String soundsPath, final boolean runSimulator) {

        final Network network = new Network();
        network.configure();
        final Simulator s = runSimulator ? new Simulator() : null;
        final WavAudioSource audio = new WavAudioSource();

        try {
            Song sun = new HereComesTheSun(network, audio, s, soundsPath);
            sun.play();
            //Song pen = new PenPineappleApplePen(network, audio, s, soundsPath);
            //pen.play();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }
}
