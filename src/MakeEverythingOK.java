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
        final KeyPedServer keyPedServer = new KeyPedServer();
        Thread keyPadServertThread = new Thread(keyPedServer, "key pad server thread");
        keyPadServertThread.start();
        final Simulator s = runSimulator ? new Simulator() : null;
        final WavAudioSource audio = new WavAudioSource();

        try {
            //Song sun = new HereComesTheSun(network, audio, s, soundsPath, keyPedServer);
            //sun.play();
            Song pen = new PenPineappleApplePen(network, audio, s, soundsPath, keyPedServer);
            pen.play();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
