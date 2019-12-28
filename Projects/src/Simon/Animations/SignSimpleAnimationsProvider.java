public class SignSimpleAnimationsProvider implements ISimpleRunnerAnimationsProvider {
    @Override
    public Animation randomNewAnimation() {
        int num = (int)Math.floor(Math.random() * 4);
//        num = 2;
        switch (num) {
            case 0:
                return new ConstAnimation1(new SignLEDObject());
            case 1:
                return new RainbowAnimation1(new SignLEDObject());
            case 2:
                return new ConfettiAnimation1(new SignLEDObject());
            case 3:
                return new ConfettiAnimation2(new SignLEDObject());
        }
        return null;
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
            for (int i = 0; i < ledObject.all.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.all[i], Coloring.RAINBOW_REVERESED_COLORING,
                        new Addon[] {new ConstCyclicMoveAddon(true)}));
            }
        }

        @Override
        protected void newBeat() {

        }
    }


    public static class ConstAnimation1 extends Animation<SignLEDObject> {

        public ConstAnimation1(SignLEDObject sign) {
            super(sign);
        }

        @Override
        protected void configAnimations() {
            for (int i = 0; i < ledObject.all.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.all[i], new ConstColoring(),
                        new Addon[]{}));
            }
        }

        @Override
        protected void newBeat() {
        }
    }

    public static class ConfettiAnimation1 extends Animation<SignLEDObject> {
        public ConfettiAnimation1(SignLEDObject sign) {
            super(sign);
        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(),1.0, 1.0);

            for (int i = 0; i < ledObject.all.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.all[i],
                        new ConstColoring(color),
                        new Addon[] {new ConfettiAddon(ledObject.all[i].numOfPixels())}));
            }
        }

        @Override
        protected void newBeat() {

        }
    }

    public static class ConfettiAnimation2 extends Animation<SignLEDObject> {
        public ConfettiAnimation2(SignLEDObject sign) {
            super(sign);
        }

        @Override
        protected void configAnimations() {
            for (int i = 0; i < ledObject.all.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.all[i],
                        new ConstColoring(),
                        new Addon[] {new ConfettiAddon(ledObject.all[i].numOfPixels())}));
            }
        }

        @Override
        protected void newBeat() {

        }
    }
}
