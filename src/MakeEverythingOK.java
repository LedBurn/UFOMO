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

            Integer userInput = null;
            /*while(userInput == null) {
                // invitation text and then a minute of silence
                Song invitation = new Invitation(network, audio, s, soundsPath, keyPedServer);
                userInput = invitation.play();
            }*/

            //this.makeDigitSound(userInput);


            /*Song pen = new PenPineappleApplePen(network, audio, s, soundsPath, keyPedServer);
            userInput = pen.play();
            System.out.println("song ended. userInput: " + userInput);*/
            Song hakuna = new Hakuna(network, audio, s, soundsPath, keyPedServer);
            userInput = hakuna.play();
            System.out.println("song ended. userInput: " + userInput);
            /*Song sun = new HereComesTheSun(network, audio, s, soundsPath, keyPedServer);
            userInput = sun.play();
            System.out.println("song ended. userInput: " + userInput);*/
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void makeDigitSound(int digit) {
        /*Song invitation = new Invitation(network, audio, s, soundsPath, keyPedServer);
        userInput = invitation.play();*/
    }
}


