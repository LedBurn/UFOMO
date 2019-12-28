public class SignSimpleAnimationsProvider implements ISimpleRunnerAnimationsProvider {
    @Override
    public Animation randomNewAnimation() {
        int num = (int)Math.floor(Math.random() * 7);
//        num = 6;
        switch (num) {
            case 0:
                return new ConstAnimation1(new SignLEDObject());
            case 1:
                return new RainbowAnimation1(new SignLEDObject());
            case 2:
                return new ConfettiAnimation1(new SignLEDObject());
            case 3:
                return new ConfettiAnimation2(new SignLEDObject());
            case 4:
                return new CountingAnimation1(new SignLEDObject());
            case 5:
                return new AlternateAnimation1(new SignLEDObject());
            case 6:
                return new AlternateAnimation2(new SignLEDObject());
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
                animations.add(
                        new PixelsArrayAnimation(ledObject.all[i],
                        Coloring.RAINBOW_REVERESED_COLORING,
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
                animations.add(
                        new PixelsArrayAnimation(ledObject.all[i],
                                new ConstColoring(),
                        new Addon[]{new ChangeHueByTimeDiscrete(Math.random(), 3, Math.random() > 0.5)}));
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

    public static class CountingAnimation1 extends Animation<SignLEDObject> {
        Coloring[] colorings;


        public CountingAnimation1(SignLEDObject sign) {
            super(sign);
            colorings = new Coloring[sign.fingers.length];
            for (int i = 0; i < colorings.length; i++) {
                colorings[i] = new ConstColoring();
            }
        }

        @Override
        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
            for (IPixelsArray obj : ledObject.all) {
                Coloring.GRAY_COLORING.color(obj);
            }

            for (IPixelsArray obj : ledObject.palm) {
                Coloring.GRAY_COLORING.color(obj);
            }

            int index = (int)Math.floor(timePercent * ledObject.fingers.length * 2 + 1);

            int startIndex = 0;
            int endIndex = ledObject.fingers.length;

            if (index < ledObject.fingers.length) {
                endIndex = index;
            } else {
                endIndex = ledObject.fingers.length * 2 - index;
            }

            for (int i = startIndex; i < endIndex; i++) {
                colorings[i].color(ledObject.fingers[i]);
            }
        }

        @Override
        protected void configAnimations() {}

        @Override
        protected void newBeat() {}
    }


    public static class AlternateAnimation1 extends Animation<SignLEDObject> {
        public AlternateAnimation1(SignLEDObject sign) {
            super(sign);
        }

        @Override
        protected void configAnimations() {
            double hue1 = Math.random();
            double hue2 = Math.random();

            for (int i = 0; i < ledObject.all.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.all[i],
                        new ConstColoring(),
                        new Addon[]{new AlternateSmoothAddon(hue1, hue2, 6), new ConstCyclicMoveAddon(Math.random() > 0.5)}));
            }
        }

        @Override
        protected void newBeat() {

        }
    }

    public static class AlternateAnimation2 extends Animation<SignLEDObject> {
        public AlternateAnimation2(SignLEDObject sign) {
            super(sign);
        }

        @Override
        protected void configAnimations() {
            double hue1 = Math.random();
            double hue2 = hue1 + 0.5;

            for (int i = 0; i < ledObject.all.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.all[i],
                        new ConstColoring(),
                        new Addon[]{new AlternateSmoothAddon(hue1, hue2, 3), new ConstCyclicMoveAddon(Math.random() > 0.5)}));
            }
        }

        @Override
        protected void newBeat() {

        }
    }
}
