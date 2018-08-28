public class ConfettiAnimations {

    public static class ConfettiAnimation1 extends Animation<UFOMOObject> {

        public ConfettiAnimation1(UFOMOObject ufomoObject) {
            super(ufomoObject);
        }

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(),1.0, 1.0);

            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, new ConstColoring(color),
                    new Addon[] {new ConfettiAddon(ledObject.bigCircle.numOfPixels())}));
            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, new ConstColoring(color),
                    new Addon[] {new ConfettiAddon(ledObject.mediumCircle.numOfPixels())}));
            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, new ConstColoring(color),
                    new Addon[] {new ConfettiAddon(ledObject.smallCircle.numOfPixels())}));

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], new ConstColoring(color),
                        new Addon[] {new ConfettiAddon(ledObject.lines[i].numOfPixels())}));
            }

            for (int i = 0; i < ledObject.octagon.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], new ConstColoring(color),
                        new Addon[] {new ConfettiAddon(ledObject.octagon[i].numOfPixels())}));
            }

            for (int i = 0; i < ledObject.beam.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.beam[i], new ConstColoring(color),
                        new Addon[] {new ConfettiAddon(ledObject.beam[i].numOfPixels())}));
            }
        }
    }

    public static class ConfettiAnimation2 extends Animation<UFOMOObject>  {
        ConstColoring constColoring;

        public ConfettiAnimation2(UFOMOObject ufomoObject) {
            super(ufomoObject);
        }

        @Override
        protected void newBeat() {
            HSBColor color = new HSBColor(Math.random(),1.0, 1.0);
            constColoring.changeColor(color);
        }

        @Override
        protected void configAnimations() {

            HSBColor color = new HSBColor(Math.random(),1.0, 1.0);
            constColoring = new ConstColoring(color);

            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, constColoring,
                    new Addon[] {new ConfettiAddon(ledObject.bigCircle.numOfPixels())}));
            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, constColoring,
                    new Addon[] {new ConfettiAddon(ledObject.mediumCircle.numOfPixels())}));
            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, constColoring,
                    new Addon[] {new ConfettiAddon(ledObject.smallCircle.numOfPixels())}));

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], constColoring,
                        new Addon[] {new ConfettiAddon(ledObject.lines[i].numOfPixels())}));
            }

            for (int i = 0; i < ledObject.octagon.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], constColoring,
                        new Addon[] {new ConfettiAddon(ledObject.octagon[i].numOfPixels())}));
            }

            for (int i = 0; i < ledObject.beam.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.beam[i], constColoring,
                        new Addon[] {new ConfettiAddon(ledObject.beam[i].numOfPixels())}));
            }
        }
    }

    public static class ConfettiAnimation3 extends Animation<UFOMOObject>  {
        public ConfettiAnimation3(UFOMOObject ufomoObject) {
            super(ufomoObject);
        }

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {
            HSBColor color1 = new HSBColor(Math.random(),1.0, 1.0);
            HSBColor color2 = new HSBColor(color1.hue + 0.1,1.0, 1.0);
            HSBColor color3 = new HSBColor(color1.hue + 0.2,1.0, 1.0);

            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, new ConstColoring(color1),
                    new Addon[] {new ConfettiAddon(ledObject.bigCircle.numOfPixels())}));
            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, new ConstColoring(color1),
                    new Addon[] {new ConfettiAddon(ledObject.mediumCircle.numOfPixels())}));
            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, new ConstColoring(color1),
                    new Addon[] {new ConfettiAddon(ledObject.smallCircle.numOfPixels())}));

            for (int i = 0; i < ledObject.lines.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.lines[i], new ConstColoring(color2),
                        new Addon[] {new ConfettiAddon(ledObject.lines[i].numOfPixels())}));
            }

            for (int i = 0; i < ledObject.octagon.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], new ConstColoring(color3),
                        new Addon[] {new ConfettiAddon(ledObject.octagon[i].numOfPixels())}));
            }

            for (int i = 0; i < ledObject.beam.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.beam[i], new ConstColoring(color3),
                        new Addon[] {new ConfettiAddon(ledObject.beam[i].numOfPixels())}));
            }
        }
    }
}
