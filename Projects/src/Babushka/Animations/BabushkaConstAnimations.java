public class BabushkaConstAnimations {

    public static class ConstAnimation extends LedObjectAnimation<BabushkaObject> {

        public ConstAnimation(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            double hue = Math.random();
            boolean reversed = Math.random() > 0.5;
            for (int i = 0; i < ledObject.babushkas.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.babushkas[i],
                        new ConstColoring(new LEDColor(hue + i * 0.05,1.0, 1.0)),
                        new Addon[] {new ChangeHueByTimeAddon(reversed)}));
            }
        }
    }

    public static class ConstAnimation2 extends LedObjectAnimation<BabushkaObject> {

        public ConstAnimation2(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            double hue = Math.random();
            boolean reversed = Math.random() > 0.5;
            for (int i = 0; i < ledObject.babushkas.length; i++) {
                animations.add(new PixelsArrayAnimation(ledObject.babushkas[i],
                        new ConstColoring(new LEDColor(hue + i * 0.05,1.0, 1.0)),
                        new Addon[] {new ChangeHueByTimeAddon(reversed)}));
            }
        }

    }
}
