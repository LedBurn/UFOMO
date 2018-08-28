public class AlternateAnimations {

    public static class AlternateAnimation1 extends Animation<UFOMOObject> {

        public AlternateAnimation1(UFOMOObject ufomoObject) {
            super(ufomoObject);
        }

        @Override
        protected void newBeat() {
        }

        @Override
        protected void configAnimations() {

            double hue1 = Math.random();
            double hue2 = hue1 + 0.4;
            Addon alternateAddon = new AlternateAddon(hue1, hue2, 10);

            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, null, new Addon[]{alternateAddon}));
            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, null, new Addon[]{alternateAddon}));
            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, null, new Addon[]{alternateAddon}));

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], null, new Addon[]{alternateAddon}));
            }

            for (int i = 0; i < ledObject.octagon.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], null, new Addon[]{alternateAddon}));
            }

            HSBColor color = new HSBColor(Math.random(), 0.0, 0.4);
            for (int i = 0; i < ledObject.beam.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.beam[i],
                        new BlurColoring(color, ledObject.beam[i].numOfPixels()),
                        new Addon[] {new ConstCyclicMoveAddon(false)},
                        3));
            }
        }
    }

    public static class AlternateAnimation2 extends Animation<UFOMOObject> {

        public AlternateAnimation2(UFOMOObject ufomoObject) {
            super(ufomoObject);
        }

        @Override
        protected void newBeat() {
        }

        @Override
        protected void configAnimations() {

            double hue1 = Math.random();
            double hue2 = hue1 + 0.4;
            Addon alternateAddon = new AlternateAddon(hue1, hue2, 10);

            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, null, new Addon[]{alternateAddon}));
            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, null, new Addon[]{alternateAddon}));
            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, null, new Addon[]{alternateAddon}));

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], null, new Addon[]{alternateAddon}));
            }

            for (int i = 0; i < ledObject.beam.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.beam[i], null, new Addon[]{alternateAddon}));
            }

            for (int i = 0; i < ledObject.octagon.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.octagon[i],null, new Addon[]{alternateAddon}));
            }
        }
    }
}
