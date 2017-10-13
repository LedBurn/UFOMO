import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteOrder;

public class WavAudioSource extends AudioSource {

    AudioInputStream ais = null;
    Clip clip = null;

    public WavAudioSource() {

    }

    public void PlaySong(String filePath) throws
            UnsupportedAudioFileException, LineUnavailableException,
            IOException {

        printInfo();
        if(this.clip != null) {
            this.clip.close();
            this.clip = null;
        }

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath)); // Obtains an audio input stream of the song
        AudioFormat baseFormat = audioStream.getFormat();    //Obtains the audio format of the song in the audio input stream.
        AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, //The type of encoding for the audio stream
                48000, //Sample rate of the audio stream
                16, //Number of bits in each sample of a sound that has this format.
                baseFormat.getChannels(),   //Number of audio channels in this audio stream
                4,   //Number of bytes in each frame of the audiostream
                48000, //Number of frames played or recorded per second, for the sound in the audio stream
                true); //Data stored in little-endian order

        System.out.println("original = "+baseFormat);
        System.out.println("decoded = "+decodedFormat);

        this.ais = AudioSystem.getAudioInputStream(decodedFormat, audioStream); //Obtains an audio input stream of the indicated encoding by converting the provided audio input stream.
        this.clip = AudioSystem.getClip();
        this.clip.addLineListener(new LineListener() {
                                      @Override
                                      public void update(LineEvent event) {
                                          if (LineEvent.Type.STOP.equals(event.getType())) {
                                              clip.close();
                                          }
                                      }
                                  });
        this.clip.open(this.ais);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        System.out.println("Max vol:"+gainControl.getMaximum());
        System.out.println("Min vol:"+gainControl.getMinimum());
        System.out.println("Curr vol:"+gainControl.getValue());

        this.clip.start();
        while (!this.clip.isRunning()) {
            try {
                System.out.println("waiting for song");
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


    private void printInfo() throws LineUnavailableException {
        Mixer.Info[] infos = AudioSystem.getMixerInfo();
        System.out.println("mixers count - " + infos.length);
        for (Mixer.Info mixer_info : infos) {
            System.out.println("INFO: " + mixer_info.toString());
            Mixer mixer = AudioSystem.getMixer(mixer_info);
            mixer.open();
            for (Line.Info info : mixer.getSourceLineInfo()) {
                if (SourceDataLine.class.isAssignableFrom(info.getLineClass())) {
                    SourceDataLine.Info info2 = (SourceDataLine.Info) info;
                    AudioFormat[] formats = info2.getFormats();
                    System.out.println("  Supported Audio formats: ");
                    for (AudioFormat format : formats) {
                        System.out.println("    " + format);
                    }
                    System.out.println();
                } else {
                    System.out.println(info.toString());
                }
                System.out.println();
            }
            mixer.close();
        }
    }
}