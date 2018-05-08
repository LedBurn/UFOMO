
public class EqAnimations {

    public static final UFOMOAnimation EQ_ANIMATION1 = new EqAnimation1();

    public static class EqAnimation1 extends UFOMOAnimation {

        private ConstColoring coloring;

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {
            coloring = new ConstColoring(new HSBColor(Math.random(), 1, 1));

            animations.add(new Animation(ufomoObject.bigCircle, coloring, new Addon[]{
                    new EqConstAddon(), new ChangeHueByTimeAddon()
            }, 10));
            animations.add(new Animation(ufomoObject.mediumCircle, coloring, new Addon[]{
                    new EqConstAddon(), new ChangeHueByTimeAddon()
            }, 10));
            animations.add(new Animation(ufomoObject.smallCircle, coloring, new Addon[]{
                    new EqConstAddon(), new ChangeHueByTimeAddon()
            }, 10));

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i], coloring, new Addon[]{
                        new EqConstAddon(), new ChangeHueByTimeAddon()
                }, 10));
            }

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i], coloring, new Addon[]{
                        new EqConstAddon(i/2), new ChangeHueByTimeAddon()
                }, 10));
            }

            HSBColor color = new HSBColor(Math.random(), 0.0, 0.4);
            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i],
                        new BlurColoring(color, ufomoObject.beam[i].numOfPixels()),
                        new Addon[] {new ConstCyclicMoveAddon(false)}));
            }
        }
    }

    public static class EqAnimation2 extends UFOMOAnimation {

        private ConstColoring coloring;

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {
            coloring = new ConstColoring(new HSBColor(Math.random(), 1, 1));

            animations.add(new Animation(ufomoObject.bigCircle, coloring, new Addon[]{
                    new EqConstAddon(), new ChangeHueByTimeAddon()
            }, 10));
            animations.add(new Animation(ufomoObject.mediumCircle, coloring, new Addon[]{
                    new EqConstAddon(), new ChangeHueByTimeAddon()
            }, 10));
            animations.add(new Animation(ufomoObject.smallCircle, coloring, new Addon[]{
                    new EqConstAddon(), new ChangeHueByTimeAddon()
            }, 10));

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i], coloring, new Addon[]{
                        new EqConstAddon(), new ChangeHueByTimeAddon()
                }, 10));
            }

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i], coloring, new Addon[]{
                        new EqConstAddon(), new ChangeHueByTimeAddon()
                }, 10));
            }

            HSBColor color = new HSBColor(Math.random(), 0.0, 0.4);
            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i],
                        new BlurColoring(color, ufomoObject.beam[i].numOfPixels()),
                        new Addon[] {new ConstCyclicMoveAddon(false)}));
            }
        }
    }

    public static class EqAnimation3 extends UFOMOAnimation {

        private ConstColoring coloring;

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {
            coloring = new ConstColoring(new HSBColor(Math.random(), 1, 1));

            animations.add(new Animation(ufomoObject.bigCircle, coloring, new Addon[]{
                    new EqConstAddon(), new ChangeHueByTimeAddon()
            }, 10));
            animations.add(new Animation(ufomoObject.mediumCircle, coloring, new Addon[]{
                    new EqConstAddon(), new ChangeHueByTimeAddon()
            }, 10));
            animations.add(new Animation(ufomoObject.smallCircle, coloring, new Addon[]{
                    new EqConstAddon(), new ChangeHueByTimeAddon()
            }, 10));

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i], coloring, new Addon[]{
                        new EqConstAddon(), new ChangeHueByTimeAddon()
                }, 10));
            }

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i], coloring, new Addon[]{
                        new EqConstAddon(i/2), new ChangeHueByTimeAddon()
                }, 10));
            }


            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i], coloring, new Addon[]{
                        new EqConstAddon(), new ChangeHueByTimeAddon()
                }, 10));
            }
        }
    }

    public static class EqAnimation4 extends UFOMOAnimation {

        private ConstColoring coloring;

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {
            coloring = new ConstColoring(new HSBColor(Math.random(), 1, 1));

            animations.add(new Animation(ufomoObject.bigCircle, coloring, new Addon[]{
                    new EqConstAddon(), new ChangeHueByTimeAddon()
            }, 10));
            animations.add(new Animation(ufomoObject.mediumCircle, coloring, new Addon[]{
                    new EqConstAddon(), new ChangeHueByTimeAddon()
            }, 10));
            animations.add(new Animation(ufomoObject.smallCircle, coloring, new Addon[]{
                    new EqConstAddon(), new ChangeHueByTimeAddon()
            }, 10));

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i], coloring, new Addon[]{
                        new EqConstAddon(), new ChangeHueByTimeAddon()
                }, 10));
            }

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i], coloring, new Addon[]{
                        new EqConstAddon(i/2), new ChangeHueByTimeAddon()
                }, 10));
            }


            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i], coloring, new Addon[]{
                        new EqConstAddon(ufomoObject.beam.length - i - 1), new ChangeHueByTimeAddon()
                }, 10));
            }
        }
    }
}
