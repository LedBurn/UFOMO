public class LinesAnimations {

    public static class RainbowAnimation1 extends LedObjectAnimation<LinesObject> {
        public RainbowAnimation1(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], Coloring.RAINBOW_REVERESED_COLORING,
                        new Addon[] {new ConstCyclicMoveAddon(true)}));
            }
        }
    }

    public static class RainbowAnimation2 extends LedObjectAnimation<LinesObject> {
        public RainbowAnimation2(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], Coloring.RAINBOW_REVERESED_COLORING,
                        new Addon[] {new ConstCyclicMoveAddon(Math.random() > 0.5)}));
            }
        }
    }

    public static class AlternateAnimation1 extends LedObjectAnimation<LinesObject> {
        public AlternateAnimation1(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            double hue1 = Math.random();
            double hue2 = hue1 + 0.4;

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], null, new Addon[]{
                        new AlternateAddon(hue1, hue2, 8)}));
            }
        }
    }

    public static class ConfettiAnimation1 extends LedObjectAnimation<LinesObject> {
        public ConfettiAnimation1(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(),1.0, 1.0);

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i],
                        new ConstColoring(color),
                        new Addon[] {new ConfettiAddon(ledObject.lines[i].numOfPixels())}));
            }
        }
    }

    public static class ConfettiAnimation2 extends LedObjectAnimation<LinesObject> {
        public ConfettiAnimation2(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            HSBColor color1 = new HSBColor(Math.random(),1.0, 1.0);
            HSBColor color2 = new HSBColor(color1.hue + 0.1,1.0, 1.0);
            HSBColor color3 = new HSBColor(color1.hue + 0.2,1.0, 1.0);
            HSBColor[] colors = {color1, color2, color3};

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i],
                        new ConstColoring(colors[(int)Math.floor(Math.random()*3)]),
                        new Addon[] {new ConfettiAddon(ledObject.lines[i].numOfPixels())}));
            }
        }
    }

    public static class DandooAnimation1 extends LedObjectAnimation<LinesObject> {
        public DandooAnimation1(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new DandooAnimation(ledObject.lines[i], 4, false));
//            }
        }
    }

    public static class DandooAnimation2 extends LedObjectAnimation<LinesObject> {
        public DandooAnimation2(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new DandooAnimation(ledObject.lines[i],4, true));
//            }
        }
    }

    public static class RandomAnimation1 extends LedObjectAnimation<LinesObject> {
        public RandomAnimation1(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i],
                        new RandomColoring(ledObject.lines[i].numOfPixels()),
                        new Addon[] {new ConstCyclicMoveAddon(false)}, 1));
            }
        }
    }


    public static class FireAnimation extends LedObjectAnimation<LinesObject> {
        public FireAnimation(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], null,
                        new Addon[]{new FireAddon()}, 4));
            }
        }
    }
}
