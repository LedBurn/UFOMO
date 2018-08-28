
public class SpikesAnimations {

    public static class SpikeAnimation1 extends Animation<UFOMOObject> {
        public SpikeAnimation1(UFOMOObject ufomoObject) {
            super(ufomoObject);
        }

        @Override
        protected void newBeat() {
        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(), 1, 0.3);
            ConstColoring coloring = new ConstColoring(color);

            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, coloring,
                    new Addon[]{new SpikeAddon(false, true), new ChangeHueByTimeAddon(true)},5));

            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, coloring,
                    new Addon[]{new SpikeAddon(false, true), new ChangeHueByTimeAddon(true)},5));

            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, coloring,
                    new Addon[]{new SpikeAddon(false, true), new ChangeHueByTimeAddon(true)},5));

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring,
                        new Addon[]{new SpikeAddon(i%2==0, false), new ChangeHueByTimeAddon(true)},5));
            }

            for (int i = 0; i < ledObject.octagon.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], coloring,
                        new Addon[]{new SpikeAddon(), new ChangeHueByTimeAddon(true)},5));
            }

            for (int i = 0; i < ledObject.beam.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.beam[i], coloring,
                        new Addon[]{new SpikeAddon(true, false), new ChangeHueByTimeAddon(true)},5));
            }
        }
    }

    public static class SpikeAnimation2 extends Animation<UFOMOObject> {
        public SpikeAnimation2(UFOMOObject ufomoObject) {
            super(ufomoObject);
        }

        @Override
        protected void newBeat() {
        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(), 1, 0.3);
            ConstColoring coloring = new ConstColoring(color);

            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, coloring,
                    new Addon[]{new SpikeAddon(false, true), new ChangeHueByTimeAddon(true)},5));

            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, coloring,
                    new Addon[]{new SpikeAddon(false, true), new ChangeHueByTimeAddon(true)},5));

            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, coloring,
                    new Addon[]{new SpikeAddon(false, true), new ChangeHueByTimeAddon(true)},5));

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring,
                        new Addon[]{new SpikeAddon(false, false), new ChangeHueByTimeAddon(true)},5));
            }

            for (int i = 0; i < ledObject.octagon.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], coloring,
                        new Addon[]{new SpikeAddon(), new ChangeHueByTimeAddon(true)},5));
            }

            for (int i = 0; i < ledObject.beam.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.beam[i], coloring,
                        new Addon[]{new SpikeAddon(true, false), new ChangeHueByTimeAddon(true)},5));
            }
        }
    }

    public static class SpikeAnimation3 extends Animation<UFOMOObject> {
        public SpikeAnimation3(UFOMOObject ufomoObject) {
            super(ufomoObject);
        }

        @Override
        protected void newBeat() {
        }

        @Override
        protected void configAnimations() {

            double hue1 = Math.random();
            double hue2 = hue1 + 0.4;
            Addon alternateAddon = new AlternateAddon(hue1, hue2, 3);
            HSBColor color = new HSBColor(Math.random(), 1, 0.3);
            HSBColor color2 = new HSBColor(color.hue, 1, 1);
            ConstColoring coloring = new ConstColoring(color);
            ConstColoring coloring2 = new ConstColoring(color2);

            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, coloring2,
                    new Addon[]{new ChangeHueByTimeAddon(true)},5));

            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, coloring2,
                    new Addon[]{ new ChangeHueByTimeAddon(false)},5));

            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, coloring2,
                    new Addon[]{new ChangeHueByTimeAddon(false)},5));

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring,
                        new Addon[]{new SpikeAddon(), new ChangeHueByTimeAddon(true)},5));
            }

            for (int i = 0; i < ledObject.octagon.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], coloring2,
                        new Addon[]{new ChangeHueByTimeAddon(false)},5));
            }

            for (int i = 0; i < ledObject.beam.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.beam[i], coloring,
                        new Addon[]{new SpikeAddon(true, false), new ChangeHueByTimeAddon(true)},5));
            }
        }
    }

    public static class SpikeAnimation4 extends Animation<UFOMOObject> {
        public SpikeAnimation4(UFOMOObject ufomoObject) {
            super(ufomoObject);
        }

        @Override
        protected void newBeat() {
        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(), 1, 0.3);
            ConstColoring coloring = new ConstColoring(color);

            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, coloring,
                    new Addon[]{new SpikeAddon(false, true, false, 7), new ChangeHueByTimeAddon(true)},5));

            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, coloring,
                    new Addon[]{new SpikeAddon(false, true, false, 7), new ChangeHueByTimeAddon(true)},5));

            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, coloring,
                    new Addon[]{new SpikeAddon(false, true, false, 7), new ChangeHueByTimeAddon(true)},5));

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring,
                        new Addon[]{new SpikeAddon(false, false, false, i/2), new ChangeHueByTimeAddon(true)},5));
            }

            for (int i = 0; i < ledObject.octagon.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], coloring,
                        new Addon[]{new SpikeAddon(true, false,false, 7), new ChangeHueByTimeAddon(true)},5));
            }

            for (int i = 0; i < ledObject.beam.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.beam[i], coloring,
                        new Addon[]{new SpikeAddon(true, false, false,8-i-1), new ChangeHueByTimeAddon(true)},5));
            }
        }
    }


    public static class SpikeAnimation5 extends Animation<UFOMOObject> {
        public SpikeAnimation5(UFOMOObject ufomoObject) {
            super(ufomoObject);
        }

        @Override
        protected void newBeat() {
        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(), 1, 0.3);
            ConstColoring coloring = new ConstColoring(color);

            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, coloring,
                    new Addon[]{new SpikeAddon(false, true), new ChangeHueByTimeAddon(true)},5));

            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, coloring,
                    new Addon[]{new SpikeAddon(false, true), new ChangeHueByTimeAddon(true)},5));

            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, coloring,
                    new Addon[]{new SpikeAddon(false, true), new ChangeHueByTimeAddon(true)},5));

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring,
                        new Addon[]{new SpikeAddon(true, false), new ChangeHueByTimeAddon(true)},5));
            }

            for (int i = 0; i < ledObject.octagon.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], coloring,
                        new Addon[]{new SpikeAddon(), new ChangeHueByTimeAddon(true)},5));
            }

            for (int i = 0; i < ledObject.beam.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.beam[i], coloring,
                        new Addon[]{new SpikeAddon(false, false), new ChangeHueByTimeAddon(true)},5));
            }
        }
    }
}
