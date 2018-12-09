public class BabushkaRainbowAnimations {

    public static class RainbowAnimationAll extends Animation<BabushkaObject>{

        public RainbowAnimationAll(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            boolean oppositeDirection = Math.random() > 0.5;
            double positionDiff = Math.random() > 0.5 ? 0.05 : 0.95;
            for (int i = 0; i < ledObject.babushkas.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.babushkas[i], Coloring.RAINBOW_COLORING,
                        new Addon[] {new ConstCyclicMoveAddon(oppositeDirection)}));
            }
        }

        @Override
        protected void newBeat() {}
    }

    public static class RainbowAnimationEqualDiff extends Animation<BabushkaObject> {

        public RainbowAnimationEqualDiff(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            boolean oppositeDirection = Math.random() > 0.5;
            for (int i = 0; i < ledObject.babushkas.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.babushkas[i], Coloring.RAINBOW_COLORING,
                        new Addon[] {new ConstCyclicMoveAddon(oppositeDirection, i / (double)BabushkaObject.SIZES.length)}));
            }
        }

        @Override
        protected void newBeat() {}
    }

    public static class RainbowAnimationSmallDiff extends Animation<BabushkaObject> {

        public RainbowAnimationSmallDiff(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            boolean oppositeDirection = Math.random() > 0.5;
            double positionDiff = Math.random() > 0.5 ? 0.05 : 0.95;
            for (int i = 0; i < ledObject.babushkas.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.babushkas[i], Coloring.RAINBOW_COLORING,
                        new Addon[] {new ConstCyclicMoveAddon(oppositeDirection, i * positionDiff)}));
            }
        }

        @Override
        protected void newBeat() {}
    }
}
