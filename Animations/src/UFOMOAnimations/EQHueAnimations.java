public class EQHueAnimations {

    public static class EqHueAnimation1 extends UFOMOAnimation {

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(), 1, 1);
            animations.add(new Animation(ufomoObject.bigCircle,
                    new AlternateColoring(color, 15),
                    new Addon[]{
                            new EqHueAddon(),
                            new ConstCyclicMoveAddon(true)
                    }, 12));

            animations.add(new Animation(ufomoObject.mediumCircle,
                    new AlternateColoring(color, 15),
                    new Addon[]{
                            new EqHueAddon(),
                            new ConstCyclicMoveAddon(true)
                    }, 12));

            animations.add(new Animation(ufomoObject.smallCircle,
                    new AlternateColoring(color, 15),
                    new Addon[]{
                            new EqHueAddon(),
                            new ConstCyclicMoveAddon(true)
                    }, 12));

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i],
                        new AlternateColoring(color, 15),
                        new Addon[]{
                                new EqHueAddon(),
                                new ConstCyclicMoveAddon(false)
                        }, 12));
            }

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i],
                        new AlternateColoring(color, 15),
                        new Addon[]{
                                new EqHueAddon(),
                                new ConstCyclicMoveAddon(true)
                        }, 12));
            }

            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i],
                        new AlternateColoring(color, 15),
                        new Addon[]{
                                new EqHueAddon(),
                                new ConstCyclicMoveAddon(false)
                        }, 12));
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
                        new ConstColoring(color),
                        new Addon[]{
                                new EqHueAddon(),
                                new ConstCyclicMoveAddon(false)
                        }, 12));
            }

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i],
                        new ConstColoring(color),
                        new Addon[]{
                                new EqHueAddon(),
                                new ConstCyclicMoveAddon(true)
                        }, 12));
            }

            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i],
                        new ConstColoring(color),
                        new Addon[]{
                                new EqHueAddon(),
                                new ConstCyclicMoveAddon(false)
                        }, 12));
            }
        }
    }
}
