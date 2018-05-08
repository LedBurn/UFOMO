public class RoundRobinAnimations {

    public static final UFOMOAnimation ROUND_ROBIN_ANIMATION1 = new RoundRobinAnimation1();

    public static class RoundRobinAnimation1 extends UFOMOAnimation {

        private ConstColoring[] linesColors;
        private int currentIndex;
        private double startingHue;

        private ConstColoring circleColoring;

        @Override
        protected void newBeat() {
            HSBColor newColor = new HSBColor(startingHue + currentIndex * 0.173, 1, 1);
            linesColors[currentIndex % 16].changeColor(newColor);
            circleColoring.changeColor(newColor);
            currentIndex++;
        }

        @Override
        protected void configAnimations() {

            linesColors = new ConstColoring[16];
            currentIndex = 0;
            startingHue = Math.random();
            circleColoring = new ConstColoring();



//            double hue1 = Math.random();
//            double hue2 = hue1 + 0.4;
//            Addon alternateAddon = new AlternateAddon(hue1, hue2, 3);
//
            animations.add(new Animation(ufomoObject.bigCircle, circleColoring, new Addon[]{new FastFadeInOutAddon()}));
            animations.add(new Animation(ufomoObject.mediumCircle, circleColoring, new Addon[]{new FastFadeInOutAddon()}));
            animations.add(new Animation(ufomoObject.smallCircle, circleColoring, new Addon[]{new FastFadeInOutAddon()}));

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                ConstColoring line = new ConstColoring(HSBColor.GRAY);
                animations.add(new Animation(ufomoObject.lines[i], line ,new Addon[]{}));
                linesColors[i] = line;
            }

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i], circleColoring, new Addon[]{}));
            }

//            for (int i = 0; i < ufomoObject.beam.length; i++) {
//                animations.add(new Animation(ufomoObject.beam[i], null, new Addon[]{alternateAddon}));
//            }
        }
    }
}
