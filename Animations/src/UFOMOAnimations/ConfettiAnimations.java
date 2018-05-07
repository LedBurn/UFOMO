public class ConfettiAnimations {

    public static final UFOMOAnimation CONFETTI_ANIMATION1 = new ConfettiAnimation1();
    public static final UFOMOAnimation CONFETTI_ANIMATION2 = new ConfettiAnimation2();

    public static class ConfettiAnimation1 extends UFOMOAnimation {
        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(),1.0, 1.0);

            animations.add(new Animation(ufomoObject.bigCircle, new ConstColoring(color),
                    new Addon[] {new ConfettiAddon(ufomoObject.bigCircle.numOfPixels())}));
            animations.add(new Animation(ufomoObject.mediumCircle, new ConstColoring(color),
                    new Addon[] {new ConfettiAddon(ufomoObject.mediumCircle.numOfPixels())}));
            animations.add(new Animation(ufomoObject.smallCircle, new ConstColoring(color),
                    new Addon[] {new ConfettiAddon(ufomoObject.smallCircle.numOfPixels())}));

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i], new ConstColoring(color),
                        new Addon[] {new ConfettiAddon(ufomoObject.lines[i].numOfPixels())}));
            }

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i], new ConstColoring(color),
                        new Addon[] {new ConfettiAddon(ufomoObject.octagon[i].numOfPixels())}));
            }

            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i], new ConstColoring(color),
                        new Addon[] {new ConfettiAddon(ufomoObject.beam[i].numOfPixels())}));
            }
        }
    }

    public static class ConfettiAnimation2 extends UFOMOAnimation {
        ConstColoring constColoring;

        @Override
        protected void newBeat() {
            HSBColor color = new HSBColor(Math.random(),1.0, 1.0);
            constColoring.changeColor(color);
        }

        @Override
        protected void configAnimations() {

            HSBColor color = new HSBColor(Math.random(),1.0, 1.0);
            constColoring = new ConstColoring(color);

            animations.add(new Animation(ufomoObject.bigCircle, constColoring,
                    new Addon[] {new ConfettiAddon(ufomoObject.bigCircle.numOfPixels())}));
            animations.add(new Animation(ufomoObject.mediumCircle, constColoring,
                    new Addon[] {new ConfettiAddon(ufomoObject.mediumCircle.numOfPixels())}));
            animations.add(new Animation(ufomoObject.smallCircle, constColoring,
                    new Addon[] {new ConfettiAddon(ufomoObject.smallCircle.numOfPixels())}));

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i], constColoring,
                        new Addon[] {new ConfettiAddon(ufomoObject.lines[i].numOfPixels())}));
            }

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i], constColoring,
                        new Addon[] {new ConfettiAddon(ufomoObject.octagon[i].numOfPixels())}));
            }

            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i], constColoring,
                        new Addon[] {new ConfettiAddon(ufomoObject.beam[i].numOfPixels())}));
            }
        }
    }
}
