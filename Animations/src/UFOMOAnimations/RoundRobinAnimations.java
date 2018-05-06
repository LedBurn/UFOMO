public class RoundRobinAnimations {

    public static final UFOMOAnimation ROUND_ROBIN_ANIMATION1 = new RoundRobinAnimation1();

    public static class RoundRobinAnimation1 extends UFOMOAnimation {

        private ConstColoring[] linesColors;
        private int currentIndex;
        private double startingHue;

        @Override
        protected void newBeat() {
            HSBColor newColor = new HSBColor(startingHue + currentIndex * 0.913, 1, 1);
            linesColors[currentIndex % 16].changeColor(newColor);
            currentIndex++;
        }

        @Override
        protected void configAnimations() {

            linesColors = new ConstColoring[16];
            currentIndex = 0;
            startingHue = Math.random();

//            double hue1 = Math.random();
//            double hue2 = hue1 + 0.4;
//            Addon alternateAddon = new AlternateAddon(hue1, hue2, 3);
//
//            animations.add(new Animation(ufomoObject.bigCircle, null, new Addon[]{alternateAddon}));
//            animations.add(new Animation(ufomoObject.mediumCircle, null, new Addon[]{alternateAddon}));
//            animations.add(new Animation(ufomoObject.smallCircle, null, new Addon[]{alternateAddon}));

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                ConstColoring line = new ConstColoring(HSBColor.GRAY);
                animations.add(new Animation(ufomoObject.lines[i], line ,new Addon[]{}));
                linesColors[i] = line;
            }

//            for (int i = 0; i < ufomoObject.beam.length; i++) {
//                animations.add(new Animation(ufomoObject.beam[i], null, new Addon[]{alternateAddon}));
//            }
        }
    }
}
