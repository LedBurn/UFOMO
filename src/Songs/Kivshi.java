import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Kivshi extends Song{

    public Kivshi(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        super(false, network, audio, simulator, soundsPath, keyPedServer);
    }

    @Override
    protected void configure() {
        Callable<AddonEffect> green = () -> new AddonSetConstColor(HSBColor.GREEN);
        Callable<AddonEffect> fadeOut = () -> new AddonFadeOut(0.0);

        addTiming(0.695, 3.491, new EntireSheep(sheep, Arrays.asList(green)));

        addTiming(4.564, 5.290, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(1.0)), fadeOut)));
        addTiming(5.381, 6.378, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(1.0)), fadeOut)));
        addTiming(6.015, 6.771, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(1.0)), fadeOut)));
        addTiming(7.935, 8.721, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(1.0)), fadeOut)));
        addTiming(9.673, 10.640, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(1.0)), fadeOut)));
        addTiming(10.791, 11.592, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(1.0)), fadeOut)));
        addTiming(11.592, 12.499, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(1.0)), fadeOut)));
        addTiming(13.028, 14.298, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(1.0)), fadeOut)));
        addTiming(13.678, 14.147, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(1.0)), fadeOut)));
        addTiming(13.935, 14.887, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(1.0)), fadeOut)));
        addTiming(15.567, 16.036, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(0.25)), fadeOut)));
        addTiming(16.716, 17.714, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(1.0)), fadeOut)));
        addTiming(18.469, 19.558, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(1.0)), fadeOut)));
        addTiming(20.525, 21.734, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(0.5)), fadeOut)));
        addTiming(22.444, 23.155, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(0.5)), fadeOut)));
        addTiming(24.651, 25.316, new EntireSheep(sheep, Arrays.asList(() -> new AddonSetConstColor(getRandomColor(0.5)), fadeOut)));
    }

    private HSBColor getRandomColor(double brightness) {
        return new HSBColor(ThreadLocalRandom.current().nextDouble(), 1.0, brightness);
    }

    @Override
    protected String getAudioFileName() {
        return "kivshi.wav";
    }

}



