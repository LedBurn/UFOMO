public class SignSimpleAnimationsProvider implements ISimpleRunnerAnimationsProvider {
    @Override
    public Animation randomNewAnimation() {
        return new RainbowAnimation1(new SignLEDObject());
    }

    @Override
    public void handleUserCode(int code) {

    }


    public static class RainbowAnimation1 extends Animation<SignLEDObject> {
        public RainbowAnimation1(SignLEDObject sign) {
            super(sign);
        }

        @Override
        protected void configAnimations() {
            for (int i = 0; i < ledObject.gameFingers.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.gameFingers[i], Coloring.RAINBOW_REVERESED_COLORING,
                        new Addon[] {new ConstCyclicMoveAddon(true)}));
            }
        }

        @Override
        protected void newBeat() {

        }
    }
}
