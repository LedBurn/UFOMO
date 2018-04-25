//import java.util.Arrays;
//import java.util.concurrent.Callable;
//
//public class Hakuna extends Song{
//
//    public Hakuna(Network network, WavAudioSource audio, Simulator simulator, String soundsPath, KeyPedServer keyPedServer) {
//        super(false, network, audio, simulator, soundsPath, keyPedServer);
//
//    }
//
//    @Override
//    protected void configure() {
//        Callable<AddonEffect> yellow = () -> new AddonSetConstColor(HSBColor.YELLOW);
//        Callable<AddonEffect> pink = () -> new AddonSetConstColor(HSBColor.PINK);
//        Callable<AddonEffect> orange = () -> new AddonSetConstColor(HSBColor.ORANGE);
//        Callable<AddonEffect> red = () -> new AddonSetConstColor(HSBColor.RED);
//        Callable<AddonEffect> skyblue = () -> new AddonSetConstColor(new HSBColor(2.0/3.0, 0.7, 0.2));
//        Callable<AddonEffect> rainbow = () -> new AddonRainbow();
//        Callable<AddonEffect> fadeIn = () -> new AddonFadeInEffect();
//        Callable<AddonEffect> fadeOut = () -> new AddonFadeOut(0.0);
//        Callable<AddonEffect> dark = () -> new AddonChangeBrightness(0.24);
//        Callable<AddonEffect> light = () -> new AddonChangeBrightness(1.0);
//        Callable<AddonEffect> inOut = () -> new AddonOutAndIn(0.2);
//        Callable<AddonEffect> spikeUp = () -> new AddonSpike(0.0, 2.0, 1.0, false);
//        Callable<AddonEffect> spikeDown = () -> new AddonSpike(1.0, -1.0, 1.0, false);
//        Callable<AddonEffect> confetti = () -> new AddonConfetti(5.0,0.2, false);
//        Callable<AddonEffect> move = () -> new AddonCyclicMove();
//        Callable<AddonEffect> brightStain = () -> new AddonBrightnessStain();
//        Callable<AddonEffect> sin = () -> new AddonMoveSin();
//        Callable<AddonEffect> reverse = () -> new AddonReverese();
//        Callable<AddonEffect> RedToOrange = () -> new AddonChangeHue(HSBColor.RED.hue, HSBColor.ORANGE.hue);
//        Callable<AddonEffect> OrangeToSky = () -> new AddonChangeHue(HSBColor.ORANGE.hue, HSBColor.SKYBLUE.hue);
//
//        Callable<AddonEffect> alternateRedOrange = () -> new AddonAlternateColorsFromArray(new HSBColor[]{HSBColor.RED, HSBColor.ORANGE}, 5, 0);
//        Callable<AddonEffect> alternateBlueGreen = () -> new AddonAlternateSmooth(HSBColor.BLUE.hue, HSBColor.GREEN.hue, 10);
//        Callable<AddonEffect> alternatePinkYellow = () -> new AddonAlternateSmooth(HSBColor.PINK.hue, HSBColor.YELLOW.hue, 10);
//
//        Callable<AddonEffect> satGlitter = () -> new AddonSaturationGlitter(2.0, 0.05);
//
//        Callable<AddonEffect> nature = () -> new AddonNature();
//        Callable<AddonEffect> blur = () -> new AddonBlur();
//
//        Totem totems03[] = new Totem[] {totems[0], totems[1], totems[2], totems[3]};
//        Totem totems47[] = new Totem[] {totems[4], totems[5], totems[6], totems[7]};
//
//        //hakuna matata
//
//        addTiming(0.500, 3.300, new AddonsContainerAllTotems(totems, Arrays.asList(nature)));
//        addTiming(0.500, 2.071, new AddonsContainerAllTotems(totems, Arrays.asList(blur, fadeIn)));
//        addTiming(1.600, 3.300, new AddonsContainerAllTotems(totems, Arrays.asList(satGlitter)));
//        //what a wonderful phrase
//        addTiming(3.300, 6.341, new AddonsContainerAllTotems(totems, Arrays.asList(pink)));
//        addTiming(3.300, 5.241, new AddonsContainerAllTotems(totems, Arrays.asList(blur, fadeIn)));
//        addTiming(4.5, 6.341, new AddonsContainerAllTotems(totems, Arrays.asList(satGlitter)));
//        //hakuna matata
//        addTiming(6.341, 8.800, new AddonsContainerAllTotems(totems, Arrays.asList(nature)));
//        addTiming(6.341, 7.829, new AddonsContainerAllTotems(totems, Arrays.asList(blur, fadeIn)));
//        addTiming(7.5, 8.800, new AddonsContainerAllTotems(totems, Arrays.asList(confetti)));
//        // aint no passing phrase
//        addTiming(8.800, 12.358, new AddonsContainerAllTotems(totems, Arrays.asList(rainbow)));
//        addTiming(8.800, 10.0, new AddonsContainerAllTotems(totems, Arrays.asList(fadeIn)));
//        addTiming(10.0, 12.358, new AddonsContainerAllTotems(totems, Arrays.asList(confetti)));
//        //it means no worries
//        addTiming(12.358, 14.230, new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueGreen, spikeUp)));
//        //for the rest of your days
//        addTiming(14.231, 18.052, new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueGreen, spikeDown)));
//        //its a problem free
//        addTiming(18.052, 21.288, new AddonsContainerAllTotems(totems, Arrays.asList(alternatePinkYellow, dark)));
//        addTiming(18.052, 21.288, new AddonsContainerAllTotems(totems, Arrays.asList(confetti)));
//        //philosophyyyyyyyyyyyyyy
//        addTiming(21.288, 23.117, new AddonsContainerAllTotems(totems, Arrays.asList(alternatePinkYellow, light)));
//        addTiming(21.288, 23.117, new AddonsContainerAllTotems(totems, Arrays.asList(confetti)));
//        //hakuna matataaaaaa
//        addTiming(23.617, 26.593, new AddonsContainerTotemsCircle(totems, Arrays.asList(rainbow, fadeIn)));
//
//        //simba asks
//        addTiming(26.593, 28.146, new AddonsContainerAllTotems(totems03, Arrays.asList(nature)));
//        addTiming(26.593, 28.146, new AddonsContainerAllTotems(totems03, Arrays.asList(blur, spikeUp)));
//        //pumba answer
//        addTiming(28.146, 29.570, new AddonsContainerAllTotems(totems47, Arrays.asList(nature)));
//        addTiming(28.146, 29.570, new AddonsContainerAllTotems(totems47, Arrays.asList(blur, spikeDown)));
//        //simba asks
//        addTiming(29.570, 30.799, new AddonsContainerAllTotems(totems03, Arrays.asList(alternatePinkYellow)));
//        addTiming(29.570, 30.799, new AddonsContainerAllTotems(totems03, Arrays.asList(blur, spikeUp)));
//
//        //pumba answer
//        addTiming(30.799, 32.158, new AddonsContainerAllTotems(totems47, Arrays.asList(alternatePinkYellow)));
//        addTiming(30.799, 32.158, new AddonsContainerAllTotems(totems47, Arrays.asList(blur, spikeDown)));
//
//        //hafira long
//        addTiming(32.158, 38.219, new AddonsContainerAllTotems(totems03, Arrays.asList(nature)));
//        addTiming(32.158, 38.219, new AddonsContainerAllTotems(totems03, Arrays.asList(blur)));
//        addTiming(32.158, 38.219, new AddonsContainerAllTotems(totems03, Arrays.asList(confetti)));
//        //timon answer
//        addTiming(38.219, 40.440, new AddonsContainerAllTotems(totems47, Arrays.asList(alternatePinkYellow)));
//        addTiming(38.219, 40.440, new AddonsContainerAllTotems(totems47, Arrays.asList(blur)));
//        addTiming(38.219, 40.440, new AddonsContainerAllTotems(totems47, Arrays.asList(confetti)));
//
//        //whyyy
//        addTiming(40.440, 41.411, new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueGreen)));
//        addTiming(40.440, 41.411, new AddonsContainerAllTotems(totems, Arrays.asList(blur, spikeUp)));
//
//        //when he was a...
//        addTiming(41.411, 44.387, new AddonsContainerAllTotems(totems03, Arrays.asList(nature, dark)));
//        addTiming(41.411, 44.387, new AddonsContainerAllTotems(totems03, Arrays.asList(fadeIn)));
//
//        //when he was a... biiiiiggggg
//        addTiming(44.387, 48.528, new AddonsContainerAllTotems(totems47, Arrays.asList(alternatePinkYellow, light)));
//        addTiming(44.387, 48.528, new AddonsContainerAllTotems(totems47, Arrays.asList(fadeIn)));
//        addTiming(46.787, 48.528, new AddonsContainerAllTotems(totems, Arrays.asList(confetti, spikeUp)));
//
//        //timon says
//        addTiming(48.528, 50.275, new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueGreen, inOut)));
//        //pumba says
//        addTiming(51.057, 51.257, new AddonsContainerAllTotems(totems, Arrays.asList(alternatePinkYellow, inOut)));
//        //hakuna matata
//        addTiming(51.957, 54.545, new AddonsContainerAllTotems(totems, Arrays.asList(nature)));
//        addTiming(51.957, 53.545, new AddonsContainerAllTotems(totems, Arrays.asList(blur, fadeIn)));
//        addTiming(52.800, 54.545, new AddonsContainerAllTotems(totems, Arrays.asList(satGlitter)));
//
//        //what a wonderful phrase
//        addTiming(54.545, 57.912, new AddonsContainerAllTotems(totems, Arrays.asList(pink)));
//        addTiming(54.545, 56.912, new AddonsContainerAllTotems(totems, Arrays.asList(blur, fadeIn)));
//        addTiming(55.400, 57.912, new AddonsContainerAllTotems(totems, Arrays.asList(satGlitter)));
//
//        //hakuna matata
//        addTiming(57.912, 59.269, new AddonsContainerAllTotems(totems, Arrays.asList(nature)));
//        addTiming(57.912, 58.569, new AddonsContainerAllTotems(totems, Arrays.asList(blur, fadeIn)));
//        addTiming(58.300, 59.269, new AddonsContainerAllTotems(totems, Arrays.asList(satGlitter)));
//
//        // aint no passing phrase
//        addTiming(59.269, 63.022, new AddonsContainerAllTotems(totems, Arrays.asList(rainbow)));
//        addTiming(59.269, 63.022, new AddonsContainerAllTotems(totems, Arrays.asList(fadeIn)));
//        addTiming(58.300, 59.269, new AddonsContainerAllTotems(totems, Arrays.asList(brightStain)));
//
//        //it means no worries
//        addTiming(63.022, 64.963, new AddonsContainerAllTotems(totems, Arrays.asList(alternatePinkYellow, spikeUp)));
//        //for the rest of your days
//        addTiming(64.963, 68.910, new AddonsContainerAllTotems(totems, Arrays.asList(alternateBlueGreen, spikeDown)));
//        //its a problem free
//        addTiming(68.910, 71.757, new AddonsContainerAllTotems(totems, Arrays.asList(rainbow, dark)));
//        addTiming(68.910, 71.757, new AddonsContainerAllTotems(totems, Arrays.asList(confetti)));
//        //philosophyyyyyyyyyyyyyy
//        addTiming(71.757, 73.800, new AddonsContainerAllTotems(totems, Arrays.asList(rainbow, light)));
//        addTiming(71.757, 73.800, new AddonsContainerAllTotems(totems, Arrays.asList(confetti)));
//        //hakuna matataaaaaa
//        addTiming(74.400, 77.710, new AddonsContainerTotemsCircle(totems, Arrays.asList(rainbow, fadeOut)));
//        //the end peace out anna belkind yo!!! 22/10/17 22:37
//
//
//
//    }
//
//    @Override
//    protected String getAudioFileName() {
//        return "hakuna.wav";
//    }
//
//}
