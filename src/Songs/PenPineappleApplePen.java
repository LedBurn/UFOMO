public class PenPineappleApplePen extends Song {

    public PenPineappleApplePen(Network network, WavAudioSource audio, Simulator simulator, String soundsPath) {
        super(network, audio, simulator, soundsPath);

    }

    @Override
    protected void configure() {

        AddonSetConstColor pen = new AddonSetConstColor(HSBColor.BLUE);
        AddonSetConstColor apple = new AddonSetConstColor(HSBColor.RED);
        AddonSetConstColor pineapple = new AddonSetConstColor(HSBColor.YELLOW);

        AddonAlternateColorsFromArray alternateBlueRed = new AddonAlternateColorsFromArray(new HSBColor[]{HSBColor.BLUE, HSBColor.RED}, 3, 10);
        AddonAlternateColorsFromArray alternateBlueYellow = new AddonAlternateColorsFromArray(new HSBColor[]{HSBColor.BLUE, HSBColor.YELLOW}, 3, 10);

        AddonSpike spikeUp = new AddonSpike(0.0, 2.0, 1.0);
        AddonSpike spikeDown = new AddonSpike(1.0, -1.0, 1.0);


        // pen apple
        addTiming(0.3, 2.066, new AddonsContainerHalfTotems(totems, new AddonEffect[]{pen, new AddonFadeInEffect()}, true));
        addTiming(2.066, 3.822, new AddonsContainerHalfTotems(totems, new AddonEffect[]{apple, new AddonFadeInEffect()}, false));

        timings.add(new AddonTiming(new AddonsContainerAllTotemOneSide(totems, new AddonEffect[]{pen, spikeDown}, true), 4.675, 5.146));
        timings.add(new AddonTiming(new AddonsContainerAllTotemOneSide(totems, new AddonEffect[]{apple, spikeUp}, false), 4.675, 5.146));

        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueRed}), 5.558, 6.920));

        // pen pineapple
        timings.add(new AddonTiming(new AddonsContainerHalfTotems(totems, new AddonEffect[]{pen, new AddonFadeInEffect()}, true), 7.373, 9.117));
        timings.add(new AddonTiming(new AddonsContainerHalfTotems(totems, new AddonEffect[]{pineapple, new AddonFadeInEffect()}, false), 9.117, 10.886));

        timings.add(new AddonTiming(new AddonsContainerAllTotemOneSide(totems, new AddonEffect[]{pen, spikeDown}, true), 11.769, 12.214));
        timings.add(new AddonTiming(new AddonsContainerAllTotemOneSide(totems, new AddonEffect[]{pineapple, spikeUp}, false), 11.769, 12.214));

        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueYellow}), 12.600, 13.971));

        // apple pen
        double penAnimationEnd = 15.689 + 0.3;
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueRed}), 14.412, penAnimationEnd));
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonClearS2SEffect()}), 15.689, penAnimationEnd));

        // pineapple pen
        double pineappleAnimationEnd = 17.500 + 0.3;
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueYellow}), 16.143, pineappleAnimationEnd));
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonClearS2SEffect()}), 17.500, pineappleAnimationEnd));

        // Ahhhh
        AddonEffect randColor = new AddonRandomColorFromList(new HSBColor[]{HSBColor.RED, HSBColor.BLUE, HSBColor.YELLOW});
        AddonRandomOnPixels randOn = new AddonRandomOnPixels(0.5);
        AddonsContainerAllTotems ahhhEffects = new AddonsContainerAllTotems(totems, new AddonEffect[]{randColor, randOn});
        timings.add(new AddonTiming(ahhhEffects, 18.820, 21.475));

        // pen pineapple apple pen
        AddonCyclicMove move = new AddonCyclicMove();


        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{move}), 19.715, 21.475));
//        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 0.25)}), 19.715, 19.944));
//        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.YELLOW.hue, 0.25)}), 19.944, 20.623));
//        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.RED.hue, 0.25)}), 20.623, 21.089));
//        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 0.25)}), 21.089, 21.475));

        // Beats
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.RED.hue, 1.0)}), 21.455, 21.914));
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.YELLOW.hue, 1.0)}), 22.355, 22.796));
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 1.0)}), 23.237, 23.679));
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.RED.hue, 1.0)}), 24.143, 24.561));
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.YELLOW.hue, 1.0)}), 25.002, 25.443));
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 1.0)}), 25.884, 26.331));

        // pen pineapple apple pen
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{move}), 26.331, 28.550));
//        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 0.25)}), 19.715, 19.944));
//        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.YELLOW.hue, 0.25)}), 19.944, 20.623));
//        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.RED.hue, 0.25)}), 20.623, 21.089));
//        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 0.25)}), 21.089, 21.475));

        // Dance time 28.550 - 29.408   ahh 29.408 - 30.295
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonSetConstColor(HSBColor.GREEN),new AddonConfetti(5.0,0.05)}),28.550,30.295));

        // apple apple 30.295 - 33.824
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueRed,move}),30.295,33.824));

        // pen apple 33.824 - 35.364
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueYellow,move}),33.824,35.364));

        // 35.364 - 40.666
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueRed,move,new AddonClearS2SEffect()}),35.364,40.666));

        // 40.666 - 44.418
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueRed,move,spikeDown}),40.666,44.418));

        // crazy time 44.418 - 47.461 phase 1
        AddonEffect standardConfetti = new AddonConfetti(5.0,0.05);
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonSetConstColor(HSBColor.GREEN),standardConfetti,spikeUp}),44.418,47.461));

        // crazy time 47.461 - 51.000 phase 2
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{pen,standardConfetti,spikeDown}),47.461,51.000));

        // crazy time 51.000 - 54.615 phase 3
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{apple,standardConfetti,spikeUp}),51.000,54.615));

        // crazy time 54.615 - 56.767 phase 4
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{pineapple,standardConfetti,new AddonClearS2SEffect()}),54.615,56.767));

        // Fading bla 56.767 - 57.902
        timings.add(new AddonTiming(new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonFadeOut()}), 56.767, 57.902));
    }

    @Override
    protected String getAudioFileName() {
        return "pen.wav";
    }

}
