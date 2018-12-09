public class LinesAnimations {

    public static class RainbowAnimation1 extends Animation<LinesObject> {
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

        @Override
        protected void newBeat() {

        }
    }

    public static class RainbowAnimation2 extends Animation<LinesObject> {
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

        @Override
        protected void newBeat() {

        }
    }

    public static class AlternateAnimation1 extends Animation<LinesObject> {
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

        @Override
        protected void newBeat() {

        }
    }

    public static class ConfettiAnimation1 extends Animation<LinesObject> {
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

        @Override
        protected void newBeat() {

        }
    }

    public static class ConfettiAnimation2 extends Animation<LinesObject> {
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

        @Override
        protected void newBeat() {

        }
    }

    public static class DandooAnimation1 extends Animation<LinesObject> {
        public DandooAnimation1(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new DandooAnimation(ledObject.lines[i],4, false));
            }
        }

        @Override
        protected void newBeat() {

        }
    }

    public static class DandooAnimation2 extends Animation<LinesObject> {
        public DandooAnimation2(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new DandooAnimation(ledObject.lines[i],4, true));
            }
        }

        @Override
        protected void newBeat() {

        }
    }

    public static class RandomAnimation1 extends Animation<LinesObject> {
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

        @Override
        protected void newBeat() {

        }
    }

    public static class EQAnimation1 extends Animation<LinesObject> {
        public EQAnimation1(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            Coloring coloring = new ConstColoring(new HSBColor(Math.random(), 1, 1));
            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring, new Addon[]{
                        new EqConstAddon(), new ChangeHueByTimeAddon()
                }, 10));
            }
        }

        @Override
        protected void newBeat() {

        }
    }

    public static class EQAnimation2 extends Animation<LinesObject> {
        public EQAnimation2(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            Coloring coloring = new ConstColoring(new HSBColor(Math.random(), 1, 1));
            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring, new Addon[]{
                        new EqConstAddon((int)Math.floor(Math.random()*8)), new ChangeHueByTimeAddon()
                }, 10));
            }
        }

        @Override
        protected void newBeat() {

        }
    }

    public static class EQAnimation3 extends Animation<LinesObject> {
        public EQAnimation3(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            Coloring coloring = new ConstColoring(new HSBColor(Math.random(), 1, 1));
            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring, new Addon[]{
                        new EqAddon(), new ChangeHueByTimeAddon()
                }, 10));
            }
        }

        @Override
        protected void newBeat() {

        }
    }

    public static class EQAnimation4 extends Animation<LinesObject> {
        public EQAnimation4(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            Coloring coloring = new ConstColoring(new HSBColor(Math.random(), 1, 1));
            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring, new Addon[]{
                        new EqAddon((int)Math.floor(Math.random()*8)), new ChangeHueByTimeAddon()
                }, 10));
            }
        }

        @Override
        protected void newBeat() {

        }
    }

    public static class EQHueAnimation1 extends Animation<LinesObject> {
        public EQHueAnimation1(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(), 1, 1);
            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i],
                        new AlternateColoring(color, 15),
                        new Addon[]{
                                new EqHueAddon(),
                                new ConstCyclicMoveAddon(true)
                        }, 12));
            }
        }

        @Override
        protected void newBeat() {

        }
    }

    public static class EQHueAnimation2 extends Animation<LinesObject> {
        public EQHueAnimation2(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(), 1, 1);
            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i],
                        new ConstColoring(color),
                        new Addon[]{
                                new EqHueAddon(),
                                new ConstCyclicMoveAddon(true)
                        }, 12));
            }
        }

        @Override
        protected void newBeat() {

        }
    }

    public static class SpikesAnimation1 extends Animation<LinesObject> {
        public SpikesAnimation1(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(), 1, 0.3);
            ConstColoring coloring = new ConstColoring(color);
            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring,
                        new Addon[]{new SpikeAddon(false, false), new ChangeHueByTimeAddon(true)},5));
            }
        }

        @Override
        protected void newBeat() {

        }
    }

    public static class SpikesAnimation2 extends Animation<LinesObject> {
        public SpikesAnimation2(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(), 1, 0.3);
            ConstColoring coloring = new ConstColoring(color);
            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring,
                        new Addon[]{new SpikeAddon(true, false), new ChangeHueByTimeAddon(true)},5));
            }
        }

        @Override
        protected void newBeat() {

        }
    }

    public static class SpikesAnimation3 extends Animation<LinesObject> {
        public SpikesAnimation3(LinesObject linesObject) {
            super(linesObject);
        }

        @Override
        protected void configAnimations() {
            HSBColor color1 = new HSBColor(Math.random(),1, 0.3);
            HSBColor color2 = new HSBColor(color1.hue + 0.3,1, 0.3);
            HSBColor color3 = new HSBColor(color1.hue + 0.6,1, 0.3);
            ConstColoring coloring1 = new ConstColoring(color1);
            ConstColoring coloring2 = new ConstColoring(color2);
            ConstColoring coloring3 = new ConstColoring(color3);
            Coloring[] colorings = {coloring1, coloring2, coloring3};

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], colorings[(int)Math.floor(Math.random()*3)],
                        new Addon[]{new SpikeAddon(Math.random() > 0.5, false), new ChangeHueByTimeAddon(true)},5));
            }
        }

        @Override
        protected void newBeat() {

        }
    }

    public static class FireAnimation extends Animation<LinesObject> {
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

        @Override
        protected void newBeat() {

        }
    }
}
