public class RandomAnimations {

    public static class RandomAnimation1 extends Animation<UFOMOObject> {
        public RandomAnimation1(UFOMOObject ufomoObject) {
            super(ufomoObject);
        }

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {

            animations.add(new PixelsArrayAnimation(ledObject.bigCircle,
                    new RandomColoring(ledObject.bigCircle.numOfPixels()),
                    new Addon[] {new ConstCyclicMoveAddon(false)}, 3));

            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle,
                    new RandomColoring(ledObject.bigCircle.numOfPixels()),
                    new Addon[] {new ConstCyclicMoveAddon(false)}, 3));

            animations.add(new PixelsArrayAnimation(ledObject.smallCircle,
                    new RandomColoring(ledObject.bigCircle.numOfPixels()),
                    new Addon[] {new ConstCyclicMoveAddon(false)}, 3));

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i],
                        new RandomColoring(ledObject.lines[i].numOfPixels()),
                        new Addon[] {new ConstCyclicMoveAddon(false)}, 1));
            }

            for (int i = 0; i < ledObject.beam.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.beam[i],
                        new RandomColoring(ledObject.beam[i].numOfPixels()),
                        new Addon[] {new ConstCyclicMoveAddon(false)}, 1));
            }
        }
    }
}
