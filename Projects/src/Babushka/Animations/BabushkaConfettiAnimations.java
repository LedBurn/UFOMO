public class BabushkaConfettiAnimations {

    public static class ConfettiAnimation1 extends Animation<BabushkaObject> {
        public ConfettiAnimation1(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            HSBColor color = new HSBColor(Math.random(),1.0, 1.0);

            for (int i = 0; i < ledObject.babushkas.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.babushkas[i],
                        new ConstColoring(color),
                        new Addon[] {new ConfettiAddon(ledObject.babushkas[i].numOfPixels())}));
            }
        }

        @Override
        protected void newBeat() {

        }
    }

    public static class ConfettiAnimation2 extends Animation<BabushkaObject> {
        public ConfettiAnimation2(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            double hue = Math.random();

            for (int i = 0; i < ledObject.babushkas.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.babushkas[i],
                        new ConstColoring(new HSBColor(hue + 0.2*i, 1.0 , 1.0)),
                        new Addon[] {new ConfettiAddon(ledObject.babushkas[i].numOfPixels())}));
            }
        }

        @Override
        protected void newBeat() {

        }
    }
}
