import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
//import java.awt.*;

public class MakeEverythingOK {

    public static void main(String[] args) {
        Network network = new Network();
        network.configure();

        WavAudioSource audio = new WavAudioSource();
        try {
            audio.PlaySong("/home/amir/Downloads/Muumimusiikkia 20.wav");

            // main loop
            Date lastLoopTime = new Date();
            Totem t = new Totem();

            while(true) {
                Double currentPos = audio.GetPositionSeconds();
                if(currentPos != null) {
                    // paint all the led objects according to the current position
                    // rand wait is here for simulation until actual code is written
                    int randWait = ThreadLocalRandom.current().nextInt(2, 10 + 1);
                    Thread.sleep(randWait);
                    network.addSegment("test", t.GetRGBColors(0, 220), 2, 0);
                    network.send();
                    Date now = new Date();
                    long msSinceLastSend = now.getTime() - lastLoopTime.getTime();
                    long waitTime = Math.max(20 - msSinceLastSend, (long)0);
                    Thread.sleep(waitTime);
                    lastLoopTime = new Date();
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
