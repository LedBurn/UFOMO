public class BeamAnimations {

    public static final UFOMOAnimation BEAM_ANIMATION1 = new BeamAnimation1();
//    public static final UFOMOAnimation CONFETTI_ANIMATION2 = new ConfettiAnimation2();

    public static class BeamAnimation1 extends UFOMOAnimation {

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(), 0.0, 0.4);

//            animations.add(new Animation(ufomoObject.bigCircle, new ConstColoring(color),
//                    new Addon[] {new ConfettiAddon(ufomoObject.bigCircle.numOfPixels())}));
//            animations.add(new Animation(ufomoObject.mediumCircle, new ConstColoring(color),
//                    new Addon[] {new ConfettiAddon(ufomoObject.mediumCircle.numOfPixels())}));
//            animations.add(new Animation(ufomoObject.smallCircle, new ConstColoring(color),
//                    new Addon[] {new ConfettiAddon(ufomoObject.smallCircle.numOfPixels())}));
//
//            for (int i = 0; i < ufomoObject.lines.length; i++) {
//                animations.add(new Animation(ufomoObject.lines[i], new ConstColoring(color),
//                        new Addon[] {new ConfettiAddon(ufomoObject.lines[i].numOfPixels())}));
//            }
//
//            for (int i = 0; i < ufomoObject.octagon.length; i++) {
//                animations.add(new Animation(ufomoObject.octagon[i],
//                        new BlurColoring(color, ufomoObject.octagon[i].numOfPixels()),
//                        new Addon[] {new ConstCyclicMoveAddon(false)}));
//            }
//
//            for (int i = 0; i < ufomoObject.lines.length; i++) {
//                animations.add(new Animation(ufomoObject.lines[i],
//                        new BlurColoring(color, ufomoObject.lines[i].numOfPixels()),
//                        new Addon[] {new ConstCyclicMoveAddon(false)}));
//            }

            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i],
                        new BlurColoring(color, ufomoObject.beam[i].numOfPixels()),
                        new Addon[] {new ConstCyclicMoveAddon(false)}));
            }
        }
    }
}
