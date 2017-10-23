import java.util.Arrays;
import java.util.concurrent.Callable;

public class Hotline extends Song{

    public Hotline(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        super(true, network, audio, simulator, soundsPath, keyPedServer);
    }

    @Override
    protected void configure() {
        Callable<AddonEffect> spikeUp = () -> new AddonSpike(0.0, 1.3, 0.3, false);
        Callable<AddonEffect> spikeDown = () -> new AddonSpike(1.0, -0.3, 0.3, false);

        addTiming(9.035, 9.575, new AddonsContainerAllTotems(new Totem[]{totems[0]}, Arrays.asList(
                () -> new AddonSetConstColor(new HSBColor(0.0 / 6.0, 1.0, 1.0)),
                spikeUp)));
        addTiming(12.491, 13.013, new AddonsContainerAllTotems(new Totem[]{totems[0], totems[1]}, Arrays.asList(
                () -> new AddonSetConstColor(new HSBColor(1.0/6.0, 1.0, 1.0)),
                spikeUp)));
        addTiming(15.245, 15.802, new AddonsContainerAllTotems(new Totem[]{totems[0], totems[1], totems[2]}, Arrays.asList(
                () -> new AddonSetConstColor(new HSBColor(2.0/6.0, 1.0, 1.0)),
                spikeUp)));
        addTiming(19.024, 19.546, new AddonsContainerAllTotems(new Totem[]{totems[0], totems[1], totems[2], totems[3]}, Arrays.asList(
                () -> new AddonSetConstColor(new HSBColor(3.0/6.0, 1.0, 1.0)),
                spikeUp)));
        addTiming(22.228, 22.732, new AddonsContainerAllTotems(new Totem[]{totems[0], totems[1], totems[2], totems[3], totems[4]}, Arrays.asList(
                () -> new AddonSetConstColor(new HSBColor(4.0/6.0, 1.0, 1.0)),
                spikeUp)));
        addTiming(25.324, 25.845, new AddonsContainerAllTotems(new Totem[]{totems[0], totems[1], totems[2], totems[3], totems[4], totems[5]}, Arrays.asList(
                () -> new AddonSetConstColor(new HSBColor(5.0/6.0, 1.0, 1.0)),
                spikeUp)));
        addTiming(28.761, 29.355, new AddonsContainerAllTotems(totems, Arrays.asList(
                () -> new AddonSetConstColor(new HSBColor(0.0, 0.0, 0.3)),
                spikeDown)));

    }

    @Override
    protected String getAudioFileName() {
        return "hotline_new.wav";
    }

}

