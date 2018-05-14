public class EQHueAnimations {

    public static class EqHueAnimation1 extends UFOMOAnimation {

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(), 1, 1);
            animations.add(new Animation(ufomoObject.bigCircle,
                    new AlternateColoring(color),
                    new Addon[]{
                            new EqHueAddon(),
                            new ConstCyclicMoveAddon(false)
                    }, 12));

            animations.add(new Animation(ufomoObject.mediumCircle,
                    new AlternateColoring(color),
                    new Addon[]{
                            new EqHueAddon(),
                            new ConstCyclicMoveAddon(false)
                    }, 12));

            animations.add(new Animation(ufomoObject.smallCircle,
                    new AlternateColoring(color),
                    new Addon[]{
                            new EqHueAddon(),
                            new ConstCyclicMoveAddon(false)
                    }, 12));

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i],
                        new AlternateColoring(color),
                        new Addon[]{
                                new EqHueAddon(),
                                new ConstCyclicMoveAddon(false)
                        }, 12));
            }

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i],
                        new AlternateColoring(color),
                        new Addon[]{
                                new EqHueAddon(),
                                new ConstCyclicMoveAddon(false)
                        }, 12));
            }

            HSBColor color2 = new HSBColor(Math.random(), 0.0, 0.4);
            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i],
                        new BlurColoring(color2, ufomoObject.beam[i].numOfPixels()),
                        new Addon[] {new ConstCyclicMoveAddon(false)},3));
            }
        }
    }

    public static class EqHueAnimation2 extends UFOMOAnimation {

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(), 1, 1);
            animations.add(new Animation(ufomoObject.bigCircle,
                    new ConstColoring(color),
                    new Addon[]{
                            new EqHueAddon(),
                            new ConstCyclicMoveAddon(false)
                    }, 12));

            animations.add(new Animation(ufomoObject.mediumCircle,
                    new ConstColoring(color),
                    new Addon[]{
                            new EqHueAddon(),
                            new ConstCyclicMoveAddon(false)
                    }, 12));

            animations.add(new Animation(ufomoObject.smallCircle,
                    new ConstColoring(color),
                    new Addon[]{
                            new EqHueAddon(),
                            new ConstCyclicMoveAddon(false)
                    }, 12));

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i],
                        new AlternateColoring(color),
                        new Addon[]{
                                new EqHueAddon(),
                                new ConstCyclicMoveAddon(false)
                        }, 12));
            }

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i],
                        new AlternateColoring(color),
                        new Addon[]{
                                new EqHueAddon(),
                                new ConstCyclicMoveAddon(false)
                        }, 12));
            }

            HSBColor color2 = new HSBColor(Math.random(), 0.0, 0.4);
            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i],
                        new BlurColoring(color2, ufomoObject.beam[i].numOfPixels()),
                        new Addon[] {new ConstCyclicMoveAddon(false)},3));
            }
        }
    }
}
