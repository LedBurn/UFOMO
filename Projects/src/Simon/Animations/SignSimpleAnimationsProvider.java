public class SignSimpleAnimationsProvider implements ISimpleRunnerAnimationsProvider {
    @Override
    public LedObjectAnimation randomNewAnimation() {
        int num = (int)Math.floor(Math.random() * 11);
//        num = 5;
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
            case 7:
                return new CrazyDotAnimation1(new SignLEDObject());
            case 8:
                return new CrazyDotAnimation2(new SignLEDObject());
            case 9:
                return new TrailAnimation1(new SignLEDObject());
            case 10:
                return new TrailAnimation2(new SignLEDObject());
        }
        return null;
    }

    @Override
    public void handleUserCode(int code) {

    }

    public static class RainbowAnimation1 extends LedObjectAnimation<SignLEDObject> {

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
    }


    public static class ConstAnimation1 extends LedObjectAnimation<SignLEDObject> {

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
    }

    public static class ConfettiAnimation1 extends LedObjectAnimation<SignLEDObject> {
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
    }

    public static class ConfettiAnimation2 extends LedObjectAnimation<SignLEDObject> {
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
    }

    public static class CountingAnimation1 extends LedObjectAnimation<SignLEDObject> {
        Coloring[] colorings;


        public CountingAnimation1(SignLEDObject sign) {
            super(sign);
            colorings = new Coloring[sign.fingers.length];
            for (int i = 0; i < colorings.length; i++) {
                colorings[i] = new ConstColoring();
            }
        }

        @Override
        public void apply(double timePercent) {
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
    }


    public static class AlternateAnimation1 extends LedObjectAnimation<SignLEDObject> {
        public AlternateAnimation1(SignLEDObject sign) {
            super(sign);
        }

        @Override
        protected void configAnimations() {
            double hue1 = Math.random();
            double hue2 = hue1 + 0.2 + Math.random() * 0.6;

            for (int i = 0; i < ledObject.all.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.all[i],
                        new ConstColoring(),
                        new Addon[]{new AlternateSmoothAddon(hue1, hue2, 6), new ConstCyclicMoveAddon(Math.random() > 0.5)}));
            }
        }
    }

    public static class AlternateAnimation2 extends LedObjectAnimation<SignLEDObject> {
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
    }

    public static class CrazyDotAnimation1 extends LedObjectAnimation<SignLEDObject> {
        public CrazyDotAnimation1(SignLEDObject sign) {
            super(sign);
        }

        @Override
        protected void configAnimations() {
            for (int i = 0; i < ledObject.all.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.all[i],
                        new ConstColoring(),
                        new Addon[]{new CrazyDotsAddon()}));
            }
        }
    }

    public static class CrazyDotAnimation2 extends LedObjectAnimation<SignLEDObject> {
        public CrazyDotAnimation2(SignLEDObject sign) {
            super(sign);
        }

        @Override
        protected void configAnimations() {
            for (int i = 0; i < ledObject.all.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.all[i],
                        Coloring.RAINBOW_COLORING,
                        new Addon[]{new CrazyDotsAddon()}));
            }
        }
    }

    public static class TrailAnimation1 extends LedObjectAnimation<SignLEDObject> {
        public TrailAnimation1(SignLEDObject sign) {
            super(sign);
        }

        @Override
        protected void configAnimations() {
            for (int i = 0; i < ledObject.all.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.all[i],
                        Coloring.RAINBOW_COLORING,
                        new Addon[]{new TrailAddon()}));
            }
        }
    }

    public static class TrailAnimation2 extends LedObjectAnimation<SignLEDObject> {
        public TrailAnimation2(SignLEDObject sign) {
            super(sign);
        }

        @Override
        protected void configAnimations() {
            Coloring coloring = new ConstColoring(new HSBColor(Math.random(), 1, 1));
            for (int i = 0; i < ledObject.all.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.all[i],
                        coloring,
                        new Addon[]{new TrailAddon(), new ChangeHueByTimeAddon()}));
            }
        }
    }
}
