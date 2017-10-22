import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class PenPineappleApplePen extends Song {

    public PenPineappleApplePen(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
        super(false, network, audio, simulator, soundsPath, keyPedServer);

    }

    @Override
    protected void configure() {

        Callable<AddonEffect> pen = () -> new AddonSetConstColor(HSBColor.BLUE);
        Callable<AddonEffect> apple = () -> new AddonSetConstColor(HSBColor.RED);
        Callable<AddonEffect> pineapple = () -> new AddonSetConstColor(HSBColor.YELLOW);

        Callable<AddonEffect> alternateBlueRed = () -> new AddonAlternateColorsFromArray(new HSBColor[]{HSBColor.BLUE, HSBColor.RED}, 3, 10);
        Callable<AddonEffect> alternateBlueYellow = () -> new AddonAlternateColorsFromArray(new HSBColor[]{HSBColor.BLUE, HSBColor.YELLOW}, 3, 10);

        Callable<AddonEffect> spikeUp = () -> new AddonSpike(0.0, 2.0, 1.0);
        Callable<AddonEffect> spikeDown = () -> new AddonSpike(1.0, -1.0, 1.0);

        Callable<AddonEffect> fadeIn = () -> new AddonFadeInEffect();
        Callable<AddonEffect> clearS2S = () -> new AddonClearS2SEffect();
        Callable<AddonEffect> move = () -> new AddonCyclicMove();
        Callable<AddonEffect> standardConfetti = () -> new AddonConfetti(5.0,0.05, false);

        // new AddonEffect[]{pen, new AddonFadeInEffect()}
        // pen apple
        addTiming(0.3, 2.066, new AddonsContainerHalfTotems(totems, Arrays.asList(pen, fadeIn), true));
        addTiming(2.066, 3.822, new AddonsContainerHalfTotems(totems, Arrays.asList(apple, fadeIn), false));

        addTiming(4.675, 5.146, new AddonsContainerAllTotemOneSide(totems, Arrays.asList(pen, spikeDown), true));
        addTiming(4.675, 5.146, new AddonsContainerAllTotemOneSide(totems, Arrays.asList(apple, spikeUp), false));

        addTiming(5.558, 6.920, new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueRed)));

        // pen pineapple
        addTiming(7.373, 9.117, new AddonsContainerHalfTotems(totems, Arrays.asList(pen, fadeIn), true));
        addTiming(9.117, 10.886, new AddonsContainerHalfTotems(totems, Arrays.asList(pineapple, fadeIn), false));

        addTiming(11.769, 12.214, new AddonsContainerAllTotemOneSide(totems, Arrays.asList(pen, spikeDown), true));
        addTiming(11.769, 12.214, new AddonsContainerAllTotemOneSide(totems, Arrays.asList(pineapple, spikeUp), false));

        addTiming(12.600, 13.971, new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueYellow)));

        //addBeatAnimations(0.3, 14.417, 16, 0.0, 0.5, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonSaturationGlitter(0.5, 0.5)}));

        // apple pen
        double penAnimationEnd = 15.689 + 0.3;
        addTiming(14.412, penAnimationEnd, new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueRed)));
        addTiming(15.689, penAnimationEnd, new AddonsContainerAllTotems(totems, Arrays.asList(clearS2S)));

        // pineapple pen
        double pineappleAnimationEnd = 17.500 + 0.3;
        addTiming(16.143, pineappleAnimationEnd, new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueYellow)));
        addTiming(17.500, pineappleAnimationEnd, new AddonsContainerAllTotems(totems, Arrays.asList(clearS2S)));

        // Ahhhh
        Callable<AddonEffect> alternate3Segments = () -> new AddonAlternateColorsFromArray(new HSBColor[]{HSBColor.RED, HSBColor.BLUE, HSBColor.YELLOW}, 10, 0);
        Callable<AddonEffect> randOn = () -> new AddonRandomOnPixels(0.5);
        AddonsContainerAllTotems ahhhEffects = new AddonsContainerAllTotems(totems, Arrays.asList(alternate3Segments, randOn));
        addTiming(18.820, 21.455, ahhhEffects);

        // pen pineapple apple pen
        addTiming(19.715, 21.455, new AddonsContainerAllTotems(totems, Arrays.asList(move)));


/*        addTiming(19.715, 19.944, new AddonsContainerAllTotems(totems, Arrays.asList(pen, spikeUp)));
        addTiming(19.944, 20.623, new AddonsContainerAllTotems(totems, Arrays.asList(pineapple, spikeUp)));
        addTiming(20.623, 21.089, new AddonsContainerAllTotems(totems, Arrays.asList(apple, spikeUp)));
        addTiming(21.089, 21.475, new AddonsContainerAllTotems(totems, Arrays.asList(pen, spikeUp)));
*/
        // Beats
        addTiming(21.455, 21.914, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonGlowStain(HSBColor.RED.hue, 1.0))));
        addTiming(22.355, 22.796, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonGlowStain(HSBColor.YELLOW.hue, 1.0))));
        addTiming(23.237, 23.679, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonGlowStain(HSBColor.BLUE.hue, 1.0))));
        addTiming(24.143, 24.561, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonGlowStain(HSBColor.RED.hue, 1.0))));
        addTiming(25.002, 25.443, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonGlowStain(HSBColor.YELLOW.hue, 1.0))));
        addTiming(25.884, 26.331, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonGlowStain(HSBColor.BLUE.hue, 1.0))));

        // pen pineapple apple pen
        addTiming(26.331, 28.550, new AddonsContainerRadomSegments(totems, Arrays.asList(() -> new AddonStain(HSBColor.BLUE.hue, 1.0, 1.0)), 0.2, 16));
        addTiming(26.965, 28.550, new AddonsContainerRadomSegments(totems, Arrays.asList(() -> new AddonStain(HSBColor.YELLOW.hue, 1.0, 1.0)), 0.2, 16));
        addTiming(27.683, 28.550, new AddonsContainerRadomSegments(totems, Arrays.asList(() -> new AddonStain(HSBColor.RED.hue, 1.0, 1.0)), 0.2, 16));
        addTiming(28.147, 28.550, new AddonsContainerRadomSegments(totems, Arrays.asList(() -> new AddonStain(HSBColor.BLUE.hue, 1.0, 1.0)), 0.2, 16));

//        addTiming(25.884, 26.331, new AddonsContainerAllTotems(totems, new AddonEffect[]{move}));
//        addTiming(19.715, 19.944, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 0.25)}));
//        addTiming(19.944, 20.623, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.YELLOW.hue, 0.25)}));
//        addTiming(20.623, 21.089, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.RED.hue, 0.25)}));
//        addTiming(21.089, 21.475, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 0.25)}));

        // Dance time 28.550 - 29.408   ahh 29.408 - 30.295
        addTiming(28.550,30.295, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonRainbow(), standardConfetti)));
        addTiming(28.550,29.408, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonChangeBrightness(0.5))));

        addMultiSectionsTiming(30.295, 44.418, new AddonsContainer[]{
                new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueRed, move)),
                new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueYellow, move)),
                new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueRed, move, clearS2S)),
                new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueRed, move, spikeDown))
        });

        addMultiSectionsTiming(44.418, 58.572, new AddonsContainer[]{
                new AddonsContainerAllTotems(totems, Arrays.asList(pen, standardConfetti, clearS2S)),
                new AddonsContainerAllTotems(totems, Arrays.asList(apple, standardConfetti, spikeDown)),
                new AddonsContainerAllTotems(totems, Arrays.asList(pineapple, standardConfetti, spikeUp)),
                new AddonsContainerAllTotems(totems, Arrays.asList(pen, standardConfetti))
        });

        // Fading bla 56.767 - 57.902
        addTiming(56.767, 57.902, new AddonsContainerAllTotems(totems, Arrays.asList(() -> new AddonFadeOut())));
    }

    @Override
    protected String getAudioFileName() {
        return "pen.wav";
    }

}
