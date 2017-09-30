import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class WavAudioSource extends AudioSource {

    public WavAudioSource() {

    }

    public void PlaySong(String filePath) throws
            UnsupportedAudioFileException, LineUnavailableException,
            IOException {

        if(this.clip != null) {
            this.clip.close();
            this.clip = null;
        }

        this.ais = AudioSystem.getAudioInputStream(new File(filePath));
        this.clip = AudioSystem.getClip();
        this.clip.open(this.ais);
        this.clip.start();
        while (!this.clip.isRunning()) {
            try {
                Thread.sleep(10);
            }
            catch (Exception ex) {
                // TODO: why should it happen? how should we react?
            }
        }
    }

    public Double GetPositionSeconds() {
        if(ais == null) {
            return null;
        }
        if(!this.clip.isRunning()) {
            return null;
        }
        return this.clip.getMicrosecondPosition() / 1000000.0;
    }

    AudioInputStream ais = null;
    Clip clip = null;
}
