import java.util.Arrays;
import java.util.concurrent.Callable;

public class HereComesTheSun extends Song {

    public HereComesTheSun(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        super(false, network, audio, simulator, soundsPath, keyPedServer);

    }

    @Override
    protected void configure() {

        Callable<AddonEffect> sunYellow = () -> new AddonSetConstColor(HSBColor.YELLOW);
        Callable<AddonEffect> sunOrange = () -> new AddonSetConstColor(HSBColor.ORANGE);
        Callable<AddonEffect> sunRed = () -> new AddonSetConstColor(HSBColor.RED);
        Callable<AddonEffect> skyBlue = () -> new AddonSetConstColor(new HSBColor(2.0/3.0, 0.7, 0.2));

        Callable<AddonEffect> fadeIn = () -> new AddonFadeInEffect();
        Callable<AddonEffect> fadeOut = () -> new AddonFadeOut();
        Callable<AddonEffect> spikeUp = () -> new AddonSpike(0.0, 2.0, 1.0);
        Callable<AddonEffect> spikeDown = () -> new AddonSpike(1.0, -1.0, 1.0);
        Callable<AddonEffect> confetti = () -> new AddonConfetti(5.0,0.2, false);
        Callable<AddonEffect> move = () -> new AddonCyclicMove();
        Callable<AddonEffect> brightStain = () -> new AddonBrightnessStain();
        Callable<AddonEffect> sin = () -> new AddonMoveSin();
        Callable<AddonEffect> reverse = () -> new AddonReverese();

        Callable<AddonEffect> alternateRedOrange = () -> new AddonAlternateColorsFromArray(new HSBColor[]{HSBColor.RED, HSBColor.ORANGE}, 5, 0);
        Callable<AddonEffect> alternateRedOrangeSmooth = () -> new AddonAlternateSmooth(HSBColor.RED.hue, HSBColor.ORANGE.hue, 10);
        Callable<AddonEffect> changeRedToOrange = () -> new AddonChangeHue(HSBColor.RED.hue, HSBColor.ORANGE.hue);

        Callable<AddonEffect> satGlitter = () -> new AddonSaturationGlitter(2.0, 0.05);


        // Here comes the sun
        addTiming(0.0, 4.818, new AddonsContainerAllTotems(totems, Arrays.asList(sunRed,fadeIn)));
        // Here comes the sun
        addTiming(4.818, 7.126, new AddonsContainerAllTotems(totems, Arrays.asList(changeRedToOrange)));

        addTiming(8.580, 10.174, new AddonsContainerAllTotems(totems, Arrays.asList(sunOrange, confetti)));
        addTiming(8.580, 10.174, new AddonsContainerAllTotemOneSide(totems, Arrays.asList(spikeUp), true));
        addTiming(8.580, 10.174, new AddonsContainerAllTotemOneSide(totems, Arrays.asList(spikeDown), false));
        addTiming(0.0, 10.174, new AddonsContainerAllTotems(totems, Arrays.asList(brightStain)));

        addTiming(7.126, 8.580, new AddonsContainerTotemsCircle(totems, Arrays.asList(sunOrange, brightStain, sin)));

        addTiming(10.403-0.2, 10.403+0.2, new AddonsContainerRadomSegments(totems, Arrays.asList(() -> new AddonStain(HSBColor.RED.hue, 1.0, 1.0)), 0.3, 16));
        addTiming(11.076-0.2, 11.076+0.2, new AddonsContainerRadomSegments(totems, Arrays.asList(() -> new AddonStain(HSBColor.ORANGE.hue, 1.0, 1.0)), 0.3, 16));
        addTiming(11.773-0.2, 11.773+0.2, new AddonsContainerRadomSegments(totems, Arrays.asList(() -> new AddonStain(HSBColor.YELLOW.hue, 1.0, 1.0)), 0.3, 16));
        addTiming(12.458-0.2, 12.458+0.2, new AddonsContainerRadomSegments(totems, Arrays.asList(() -> new AddonStain(HSBColor.RED.hue, 1.0, 1.0)), 0.3, 16));

        addTiming(13.096, 18.050, new AddonsContainerAllTotems(totems, Arrays.asList(alternateRedOrangeSmooth, brightStain)));
        addTiming(13.096, 13.990, new AddonsContainerAllTotems(totems, Arrays.asList(fadeOut, sin)));
        addTiming(14.454, 17.569, new AddonsContainerAllTotems(totems, Arrays.asList(fadeOut, () -> new AddonConfetti(2.0,0.5, true))));
        addTiming(17.569, 18.050, new AddonsContainerAllTotems(totems, Arrays.asList(fadeOut)));

        addTiming(18.050, 24.947, new AddonsContainerTotemsCircle(totems, Arrays.asList(sunRed, satGlitter)));
        addTiming(24.947, 32.167, new AddonsContainerTotemsCircle(totems, Arrays.asList(changeRedToOrange, satGlitter, reverse)));
        addTiming(30.780, 32.167, new AddonsContainerAllTotems(totems, Arrays.asList(fadeOut)));

        // here comes the sun 1
        addTiming(32.167, 34.456, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonChangeHueOverLocation(HSBColor.BLACK, HSBColor.RED))));
        addTiming(34.456, 36.296, new AddonsContainerHalfTotems(totems, Arrays.asList(sunRed, satGlitter), true));
        addTiming(34.456, 36.296, new AddonsContainerHalfTotems(totems, Arrays.asList(sunRed, satGlitter, reverse), false));

        // here comes the sun 2
        addTiming(36.296, 38.109, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonChangeHueOverLocation(HSBColor.RED, HSBColor.ORANGE))));
        addTiming(32.167, 38.109, new AddonsContainerAllTotems(totems, Arrays.asList(brightStain)));
        // and i say
        addTiming(38.109, 39.480, new AddonsContainerHalfTotems(totems, Arrays.asList(sunOrange, brightStain, sin), true));
        addTiming(38.109, 39.480, new AddonsContainerHalfTotems(totems, Arrays.asList(sunOrange, brightStain, sin, reverse), false));

        // it all right
        addTiming(39.480, 41.042, new AddonsContainerAllTotemOneSide(totems, Arrays.asList(sunOrange, brightStain, spikeUp), true));
        addTiming(39.480, 41.042, new AddonsContainerAllTotemOneSide(totems, Arrays.asList(sunOrange, brightStain, spikeDown), false));

        addTiming(41.047-0.2, 41.047+0.2, new AddonsContainerRadomSegments(totems, Arrays.asList(() -> new AddonStain(HSBColor.RED.hue, 1.0, 1.0)), 0.3, 16));
        addTiming(41.970-0.2, 41.970+0.2, new AddonsContainerRadomSegments(totems, Arrays.asList(() -> new AddonStain(HSBColor.ORANGE.hue, 1.0, 1.0)), 0.3, 16));
        addTiming(42.653-0.2, 42.653+0.2, new AddonsContainerRadomSegments(totems, Arrays.asList(() -> new AddonStain(HSBColor.YELLOW.hue, 1.0, 1.0)), 0.3, 16));
        addTiming(43.325-0.2, 43.325+0.2, new AddonsContainerRadomSegments(totems, Arrays.asList(() -> new AddonStain(HSBColor.RED.hue, 1.0, 0.7)), 0.3, 16));

        addTiming(43.947, 44.876, new AddonsContainerAllTotems(totems, Arrays.asList(alternateRedOrange, brightStain)));
        addTiming(43.947, 44.876, new AddonsContainerAllTotems(totems, Arrays.asList(confetti)));
        addTiming(46.705, 47.186, new AddonsContainerHalfTotems(totems, Arrays.asList(alternateRedOrange, brightStain, spikeUp), true));
        addTiming(47.186, 47.590, new AddonsContainerHalfTotems(totems, Arrays.asList(alternateRedOrange, brightStain, spikeUp), false));

        // song end - sun sun sun here it comes
        addTiming(47.590, 76.123, new AddonsContainerAllTotems(totems, Arrays.asList(skyBlue)));

        this.sunsunsunAnimations(47.590, HSBColor.RED.hue, 1.0, 0.0, 0.5);
        this.sunsunsunAnimations(53.314, HSBColor.ORANGE.hue, 0.9, 0.1, 0.6);
        this.sunsunsunAnimations(59.015, HSBColor.ORANGE.hue, 0.8, 0.3, 0.8);
        this.sunsunsunAnimations(64.717, HSBColor.ORANGE.hue, 0.7, 0.4, 0.9);
        this.sunsunsunAnimations(70.440, HSBColor.ORANGE.hue, 0.5, 0.5, 1.0);
        addTiming(64.717, 76.123, new AddonsContainerAllTotems(totems, Arrays.asList(fadeOut)));

    }

    private void sunsunsunAnimations(double startTime, double hue, double saturation, double locStart, double locEnd) {
        addTiming(startTime, startTime + 0.65, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonFadeInEffect())));
        AddonsContainerPartialTotem sun1 = new AddonsContainerPartialTotem();
        sun1.addTotemSegment(new Totem[]{totems[0], totems[3], totems[6]}, Arrays.asList(() -> new AddonStain(hue, 1.0, saturation)), locStart, locEnd, null);
        addTiming(startTime + 0.65, startTime + 5.724, sun1);
        AddonsContainerPartialTotem sun2 = new AddonsContainerPartialTotem();
        sun2.addTotemSegment(new Totem[]{totems[1], totems[4], totems[7]}, Arrays.asList(() -> new AddonStain(hue, 1.0, saturation)), locStart, locEnd, null);
        addTiming(startTime + 1.354, startTime + 5.724, sun2);
        AddonsContainerPartialTotem sun3 = new AddonsContainerPartialTotem();
        sun3.addTotemSegment(new Totem[]{totems[2], totems[5]}, Arrays.asList(() -> new AddonStain(hue, 1.0, saturation)), locStart, locEnd, null);
        addTiming(startTime + 2.0, startTime + 5.724, sun3);
        addTiming(startTime + 2.665, startTime + 5.724, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonFadeOut())));
    }

    @Override
    protected String getAudioFileName() {
        return "sun.wav";
    }

}
