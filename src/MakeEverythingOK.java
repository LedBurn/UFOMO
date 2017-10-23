import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

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

            while(true) {

                Song h = new Kivshi(network, audio, s, soundsPath, keyPedServer);
                h.play();

                Integer userInput = null;

                int invitationCallNumber = 0;
                while (userInput == null) {
                    // invitation text and then a minute of silence
                    Song invitation = new Invitation(network, audio, s, soundsPath, keyPedServer, invitationCallNumber++);
                    userInput = invitation.play();
                    if(userInput == null) {
                        Song silence = new Silence(network, audio, s, soundsPath, keyPedServer);
                        userInput = silence.play();
                    }
                }

                makeDigitSound(network, audio, s, soundsPath, keyPedServer, userInput);
                int numberOfHotlinePlays = 0;
                userInput = null;
                while (userInput == null && numberOfHotlinePlays < 3) {
                    Song hotline = new Hotline(network, audio, s, soundsPath, keyPedServer);
                    userInput = hotline.play();

                    if(userInput == null) {
                        Song silence15sec = new Silence15Sec(network, audio, s, soundsPath, keyPedServer);
                        userInput = silence15sec.play();
                    }
                    numberOfHotlinePlays++;

                    if(userInput != null && userInput == 9) {
                        makeDigitSound(network, audio, s, soundsPath, keyPedServer, userInput);
                        userInput = null;
                        numberOfHotlinePlays = 0;
                    }
                }

                if(userInput == null) {
                    continue;
                }

                // we know that userInput is available
                makeDigitSound(network, audio, s, soundsPath, keyPedServer, userInput);
                Song silence2Sec = new Silence2Sec(network, audio, s, soundsPath, keyPedServer);
                silence2Sec.play();

                Song songToPlay = getSongByDigit(network, audio, s, soundsPath, keyPedServer, userInput);

                if(songToPlay != null) {
                    songToPlay.play();

                    if(userInput >= 1 && userInput <=7) {
                        Song allOk = new AllOk(network, audio, s, soundsPath, keyPedServer);
                        allOk.play();
                    }
                }

                Song silence15sec = new Silence15Sec(network, audio, s, soundsPath, keyPedServer);
                silence15sec.play();
            }


            /*Song pen = new PenPineappleApplePen(network, audio, s, soundsPath, keyPedServer);
            userInput = pen.play();
            System.out.println("song ended. userInput: " + userInput);*/
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

    private static Song getSongByDigit(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer, int digit) {
        switch (digit) {
            case 1:
                return new PenPineappleApplePen(network, audio, simulator, soundsPath, keyPedServer);
            case 2:
                return new Money(network, audio, simulator, soundsPath, keyPedServer);
            case 3:
                return new Rachel(network, audio, simulator, soundsPath, keyPedServer);
            case 4:
                return new Hakuna(network, audio, simulator, soundsPath, keyPedServer);
            case 5:
                return new HereComesTheSun(network, audio, simulator, soundsPath, keyPedServer);
            case 6:
                int randSong = ThreadLocalRandom.current().nextInt(1,6);
                if(randSong == 6) { // just to be safe, not to enter never ending recursive calls
                    randSong = 1;
                }
                return getSongByDigit(network, audio, simulator, soundsPath, keyPedServer, randSong);
            case 7:
                return new Kivshi(network, audio, simulator, soundsPath, keyPedServer);
            case 8:
                return new Illegal(network, audio, simulator, soundsPath, keyPedServer);
            case 9: // it was already handled
        }
        return null;
    }

    private static void makeDigitSound(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer, int digit)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Song digitSong = new Digit(network, audio, simulator, soundsPath, keyPedServer, digit);
        digitSong.play();
    }
}


