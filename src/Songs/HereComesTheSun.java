import java.util.Arrays;
import java.util.concurrent.Callable;

public class HereComesTheSun extends Song {

    public HereComesTheSun(Network network, WavAudioSource audio, Simulator simulator, String soundsPath) {
        super(network, audio, simulator, soundsPath);

    }

    @Override
    protected void configure() {

        Callable<AddonEffect> blue = () -> new AddonSetConstColor(HSBColor.BLUE);
        Callable<AddonEffect> apple = () -> new AddonSetConstColor(HSBColor.RED);
        Callable<AddonEffect> sun = () -> new AddonGlowStain(HSBColor.YELLOW.hue,1.0);

        Callable<AddonEffect> alternateBlueRed = () -> new AddonAlternateColorsFromArray(new HSBColor[]{HSBColor.BLUE, HSBColor.RED}, 3, 10);
        Callable<AddonEffect> alternateBlueYellow = () -> new AddonAlternateColorsFromArray(new HSBColor[]{HSBColor.BLUE, HSBColor.YELLOW}, 3, 10);

        Callable<AddonEffect> spikeUp = () -> new AddonSpike(0.0, 2.0, 1.0);
        Callable<AddonEffect> spikeDown = () -> new AddonSpike(1.0, -1.0, 1.0);

        Callable<AddonEffect> fadeIn = () -> new AddonFadeInEffect();
        Callable<AddonEffect> clearS2S = () -> new AddonClearS2SEffect();
        Callable<AddonEffect> move = () -> new AddonCyclicMove();
        Callable<AddonEffect> standardConfetti = () -> new AddonConfetti(5.0,0.05);

        // Here comes the sun
        addTiming(0.0, 4.818, new AddonsContainerAllTotems(totems, Arrays.asList(sun,fadeIn)));
        // Here comes the sun
        addTiming(4.818, 7.126, new AddonsContainerAllTotems(totems, Arrays.asList(sun)));
        // and i say, its all right
        AddonsContainerPartialTotem singleTot = new AddonsContainerPartialTotem();
        singleTot.addTotemSegment(new Totem[]{totems[0]}, Arrays.asList(blue,standardConfetti), 0.0, 1.0, null);
        addTiming(7.126, 10.304, singleTot);
        // to lu le lu lu le
        addTiming(10.304, 14.000, new AddonsContainerAllTotems(totems, Arrays.asList(blue,standardConfetti)));

        //addTiming(5.558, 6.920, new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueRed)));

        //addTiming(12.600, 13.971, new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueYellow)));

        //addBeatAnimations(0.3, 14.417, 16, 0.0, 0.5, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonSaturationGlitter(0.5, 0.5)}));

        // apple pen
//        double penAnimationEnd = 15.689 + 0.3;
//        addTiming(14.412, penAnimationEnd, new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueRed)));
//        addTiming(15.689, penAnimationEnd, new AddonsContainerAllTotems(totems, Arrays.asList(clearS2S)));

        // pineapple pen
//        double pineappleAnimationEnd = 17.500 + 0.3;
//        addTiming(16.143, pineappleAnimationEnd, new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueYellow)));
//        addTiming(17.500, pineappleAnimationEnd, new AddonsContainerAllTotems(totems, Arrays.asList(clearS2S)));

        // Ahhhh
//        AddonEffect randColor = new AddonRandomColorFromList(new HSBColor[]{HSBColor.RED, HSBColor.BLUE, HSBColor.YELLOW});
//        AddonRandomOnPixels randOn = new AddonRandomOnPixels(0.5);
//        AddonsContainerAllTotems ahhhEffects = new AddonsContainerAllTotems(totems, Arrays.asList(() -> randColor, () -> randOn));
//        addTiming(18.820, 21.475, ahhhEffects);

        // Beats
//        addTiming(21.455, 21.914, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonGlowStain(HSBColor.RED.hue, 1.0))));
//        addTiming(22.355, 22.796, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonGlowStain(HSBColor.YELLOW.hue, 1.0))));
//        addTiming(23.237, 23.679, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonGlowStain(HSBColor.BLUE.hue, 1.0))));
//        addTiming(24.143, 24.561, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonGlowStain(HSBColor.RED.hue, 1.0))));
//        addTiming(25.002, 25.443, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonGlowStain(HSBColor.YELLOW.hue, 1.0))));
//        addTiming(25.884, 26.331, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonGlowStain(HSBColor.BLUE.hue, 1.0))));

        // pen pineapple apple pen
//        AddonsContainerPartialTotem partialPen = new AddonsContainerPartialTotem();
//        partialPen.addTotemSegment(new Totem[]{totems[0], totems[3], totems[6]}, Arrays.asList(() -> new AddonStain(HSBColor.BLUE.hue, 1.0)), 0.1, 0.3, null);
//        partialPen.addTotemSegment(new Totem[]{totems[1], totems[4], totems[7]}, Arrays.asList(() -> new AddonStain(HSBColor.BLUE.hue, 1.0)), 0.6, 0.9, null);
//        addTiming(26.331, 28.550, partialPen);

//        AddonsContainerPartialTotem partialPineapple = new AddonsContainerPartialTotem();
//        partialPineapple.addTotemSegment(new Totem[]{totems[0], totems[3], totems[6]}, Arrays.asList(() -> new AddonStain(HSBColor.YELLOW.hue, 1.0)), 0.5, 0.6, null);
//        partialPineapple.addTotemSegment(new Totem[]{totems[1], totems[4], totems[7]}, Arrays.asList(() -> new AddonStain(HSBColor.YELLOW.hue, 1.0)), 0.0, 0.4, null);
//        addTiming(26.965, 28.550, partialPineapple);

//        AddonsContainerPartialTotem partialApple = new AddonsContainerPartialTotem();
//        partialApple.addTotemSegment(new Totem[]{totems[0], totems[3], totems[6]}, Arrays.asList(() -> new AddonStain(HSBColor.RED.hue, 1.0)), 0.2, 0.4, null);
//        partialApple.addTotemSegment(new Totem[]{totems[1], totems[4], totems[7]}, Arrays.asList(() -> new AddonStain(HSBColor.RED.hue, 1.0)), 0.7, 0.8, null);
//        addTiming(27.683, 28.550, partialApple);

//        AddonsContainerPartialTotem partialGreen = new AddonsContainerPartialTotem();
//        partialGreen.addTotemSegment(new Totem[]{totems[2], totems[5]}, Arrays.asList(() -> new AddonStain(HSBColor.GREEN.hue, 1.0)), 0.6, 0.9, null);
//        partialGreen.addTotemSegment(new Totem[]{totems[1], totems[4]}, Arrays.asList(() -> new AddonStain(HSBColor.GREEN.hue, 1.0)), 0.0, 0.2, null);
//        addTiming(28.147, 28.550, partialGreen);

//        addTiming(25.884, 26.331, new AddonsContainerAllTotems(totems, new AddonEffect[]{move}));
//        addTiming(19.715, 19.944, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 0.25)}));
//        addTiming(19.944, 20.623, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.YELLOW.hue, 0.25)}));
//        addTiming(20.623, 21.089, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.RED.hue, 0.25)}));
//        addTiming(21.089, 21.475, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 0.25)}));

        // Dance time 28.550 - 29.408   ahh 29.408 - 30.295
//        addTiming(28.550,30.295, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonSetConstColor(HSBColor.GREEN),() -> new AddonConfetti(5.0,0.05))));

//        addMultiSectionsTiming(30.295, 44.418, new AddonsContainer[]{
//                new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueRed, move)),
//                new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueYellow, move)),
//                new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueRed, move, clearS2S)),
//                new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueRed, move, spikeDown))
//        });

//        Callable<AddonEffect> standardConfetti = () -> new AddonConfetti(5.0,0.05);
        // Fading bla 56.767 - 57.902
//        addTiming(56.767, 57.902, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonFadeOut())));
    }

    @Override
    protected String getAudioFileName() {
        return "sun.wav";
    }

}
