import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    public static final Sound bip1 = new Sound("Sounds/bip.wav");
    public static final Sound bip2 = new Sound("Sounds/bip2.wav");
    public static final Sound bip3 = new Sound("Sounds/bip3.wav");
    public static final Sound bip4 = new Sound("Sounds/bip4.wav");
    public static final Sound[] bips = new Sound[]{bip1, bip2, bip3, bip4};

    public static final Sound success = new Sound("Sounds/success.wav");
    public static final Sound failed = new Sound("Sounds/failed.wav");

    private AudioClip clip;

    private Sound(String name) {
        try {
            clip = Applet.newAudioClip(Sound.class.getResource(name));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void play() {
        try {
            new Thread() {
                public void run() {
                    clip.play();
                }
            }.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}