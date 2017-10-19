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

        addTiming(4.675, 5.146, new AddonsContainerAllTotemOneSide(totems, new AddonEffect[]{pen, spikeDown}, true));
        addTiming(4.675, 5.146, new AddonsContainerAllTotemOneSide(totems, new AddonEffect[]{apple, spikeUp}, false));

        addTiming(5.558, 6.920, new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueRed}));

        // pen pineapple
        addTiming(7.373, 9.117, new AddonsContainerHalfTotems(totems, new AddonEffect[]{pen, new AddonFadeInEffect()}, true));
        addTiming(9.117, 10.886, new AddonsContainerHalfTotems(totems, new AddonEffect[]{pineapple, new AddonFadeInEffect()}, false));

        addTiming(11.769, 12.214, new AddonsContainerAllTotemOneSide(totems, new AddonEffect[]{pen, spikeDown}, true));
        addTiming(11.769, 12.214, new AddonsContainerAllTotemOneSide(totems, new AddonEffect[]{pineapple, spikeUp}, false));

        addTiming(12.600, 13.971, new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueYellow}));

        //addBeatAnimations(0.3, 14.417, 16, 0.0, 0.5, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonSaturationGlitter(0.5, 0.5)}));

        // apple pen
        double penAnimationEnd = 15.689 + 0.3;
        addTiming(14.412, penAnimationEnd, new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueRed}));
        addTiming(15.689, penAnimationEnd, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonClearS2SEffect()}));

        // pineapple pen
        double pineappleAnimationEnd = 17.500 + 0.3;
        addTiming(16.143, pineappleAnimationEnd, new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueYellow}));
        addTiming(17.500, pineappleAnimationEnd, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonClearS2SEffect()}));

        // Ahhhh
        AddonEffect randColor = new AddonRandomColorFromList(new HSBColor[]{HSBColor.RED, HSBColor.BLUE, HSBColor.YELLOW});
        AddonRandomOnPixels randOn = new AddonRandomOnPixels(0.5);
        AddonsContainerAllTotems ahhhEffects = new AddonsContainerAllTotems(totems, new AddonEffect[]{randColor, randOn});
        addTiming(18.820, 21.475, ahhhEffects);

        // pen pineapple apple pen
        AddonCyclicMove move = new AddonCyclicMove();


        addTiming(19.715, 21.475, new AddonsContainerAllTotems(totems, new AddonEffect[]{move}));
//        addTiming(19.715, 19.944, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 0.25)}));
//        addTiming(19.944, 20.623, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.YELLOW.hue, 0.25)}));
//        addTiming(20.623, 21.089, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.RED.hue, 0.25)}));
//        addTiming(21.089, 21.475, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 0.25)}));

        // Beats
        addTiming(21.455, 21.914, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.RED.hue, 1.0)}));
        addTiming(22.355, 22.796, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.YELLOW.hue, 1.0)}));
        addTiming(23.237, 23.679, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 1.0)}));
        addTiming(24.143, 24.561, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.RED.hue, 1.0)}));
        addTiming(25.002, 25.443, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.YELLOW.hue, 1.0)}));
        addTiming(25.884, 26.331, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 1.0)}));

        // pen pineapple apple pen
        AddonsContainerPartialTotem partialPen = new AddonsContainerPartialTotem();
        partialPen.addTotemSegment(new Totem[]{totems[0], totems[3], totems[6]}, new AddonEffect[]{new AddonStain(HSBColor.BLUE.hue, 1.0)}, 0.1, 0.3, null);
        partialPen.addTotemSegment(new Totem[]{totems[1], totems[4], totems[7]}, new AddonEffect[]{new AddonStain(HSBColor.BLUE.hue, 1.0)}, 0.6, 0.9, null);
        addTiming(26.331, 28.550, partialPen);

        AddonsContainerPartialTotem partialPineapple = new AddonsContainerPartialTotem();
        partialPineapple.addTotemSegment(new Totem[]{totems[0], totems[3], totems[6]}, new AddonEffect[]{new AddonStain(HSBColor.YELLOW.hue, 1.0)}, 0.5, 0.6, null);
        partialPineapple.addTotemSegment(new Totem[]{totems[1], totems[4], totems[7]}, new AddonEffect[]{new AddonStain(HSBColor.YELLOW.hue, 1.0)}, 0.0, 0.4, null);
        addTiming(26.965, 28.550, partialPineapple);

        AddonsContainerPartialTotem partialApple = new AddonsContainerPartialTotem();
        partialApple.addTotemSegment(new Totem[]{totems[0], totems[3], totems[6]}, new AddonEffect[]{new AddonStain(HSBColor.RED.hue, 1.0)}, 0.2, 0.4, null);
        partialApple.addTotemSegment(new Totem[]{totems[1], totems[4], totems[7]}, new AddonEffect[]{new AddonStain(HSBColor.RED.hue, 1.0)}, 0.7, 0.8, null);
        addTiming(27.683, 28.550, partialApple);

        AddonsContainerPartialTotem partialGreen = new AddonsContainerPartialTotem();
        partialGreen.addTotemSegment(new Totem[]{totems[2], totems[5]}, new AddonEffect[]{new AddonStain(HSBColor.GREEN.hue, 1.0)}, 0.6, 0.9, null);
        partialGreen.addTotemSegment(new Totem[]{totems[1], totems[4]}, new AddonEffect[]{new AddonStain(HSBColor.GREEN.hue, 1.0)}, 0.0, 0.2, null);
        addTiming(28.147, 28.550, partialGreen);

//        addTiming(25.884, 26.331, new AddonsContainerAllTotems(totems, new AddonEffect[]{move}));
//        addTiming(19.715, 19.944, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 0.25)}));
//        addTiming(19.944, 20.623, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.YELLOW.hue, 0.25)}));
//        addTiming(20.623, 21.089, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.RED.hue, 0.25)}));
//        addTiming(21.089, 21.475, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonGlowStain(HSBColor.BLUE.hue, 0.25)}));

        // Dance time 28.550 - 29.408   ahh 29.408 - 30.295
        addTiming(28.550,30.295, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonSetConstColor(HSBColor.GREEN),new AddonConfetti(5.0,0.05)}));

        addMultiSectionsTiming(30.295, 44.418, new AddonsContainer[]{
                new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueRed,move}),
                new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueYellow,move}),
                new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueRed,move,new AddonClearS2SEffect()}),
                new AddonsContainerAllTotems(totems, new AddonEffect[]{alternateBlueRed,move,spikeDown})
        });

        AddonEffect standardConfetti = new AddonConfetti(5.0,0.05);
        addMultiSectionsTiming(44.418, 58.572, new AddonsContainer[]{
                new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonSetConstColor(HSBColor.GREEN),standardConfetti,new AddonClearS2SEffect()}),
                new AddonsContainerAllTotems(totems, new AddonEffect[]{pen,standardConfetti,spikeDown}),
                new AddonsContainerAllTotems(totems, new AddonEffect[]{apple,standardConfetti,spikeUp}),
                new AddonsContainerAllTotems(totems, new AddonEffect[]{pineapple,standardConfetti})
        });

        // Fading bla 56.767 - 57.902
        addTiming(56.767, 57.902, new AddonsContainerAllTotems(totems, new AddonEffect[]{new AddonFadeOut()}));
    }

    @Override
    protected String getAudioFileName() {
        return "pen.wav";
    }

}
