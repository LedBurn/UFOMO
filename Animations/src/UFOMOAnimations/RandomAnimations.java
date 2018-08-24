public class RandomAnimations {

    public static class RandomAnimation1 extends UFOMOAnimation {
        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {

            animations.add(new Animation(ufomoObject.bigCircle,
                    new RandomColoring(ufomoObject.bigCircle.numOfPixels()),
                    new Addon[] {new ConstCyclicMoveAddon(false)}, 3));

            animations.add(new Animation(ufomoObject.mediumCircle,
                    new RandomColoring(ufomoObject.bigCircle.numOfPixels()),
                    new Addon[] {new ConstCyclicMoveAddon(false)}, 3));

            animations.add(new Animation(ufomoObject.smallCircle,
                    new RandomColoring(ufomoObject.bigCircle.numOfPixels()),
                    new Addon[] {new ConstCyclicMoveAddon(false)}, 3));

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i],
                        new RandomColoring(ufomoObject.lines[i].numOfPixels()),
                        new Addon[] {new ConstCyclicMoveAddon(false)}, 1));
            }

            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i],
                        new RandomColoring(ufomoObject.beam[i].numOfPixels()),
                        new Addon[] {new ConstCyclicMoveAddon(false)}, 1));
            }
        }
    }
}
