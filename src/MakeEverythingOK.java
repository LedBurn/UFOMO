import java.io.File;
import java.util.Date;
import javax.sound.sampled.*;
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

            while(true) {
                Double currentPos = audio.GetPositionSeconds();
                if(currentPos != null) {
                    // paint all the led objects according to the current position
                    // rand wait is here for simulation until actual code is written
                    int randWait = ThreadLocalRandom.current().nextInt(2, 10 + 1);
                    Thread.sleep(randWait);
                    RGBColor colors[] = new RGBColor[2];
                    colors[0] = new RGBColor();
                    colors[0].r = (byte)255;
                    colors[1] = new RGBColor();
                    colors[1].g = (byte)255;
                    network.addSegment("test", colors, 2, 9);
                    network.addSegment("test", colors, 2, 17);
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

        /*int rgb = Color.HSBtoRGB(0.0f, 1.0f, -0.5f);
        int red = (rgb>>16)&0xFF;
        int green = (rgb>>8)&0xFF;
        int blue = rgb&0xFF;

        System.out.println("");
        System.out.println(red);
        System.out.println(green);
        System.out.println(blue);*/
    }
}
