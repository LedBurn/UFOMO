
public class RainbowAnimations {

    public static final UFOMOAnimation RAINBOW_ANIMATION1 = new RainbowAnimation1();
    public static final UFOMOAnimation RAINBOW_ANIMATION2 = new RainbowAnimation2();
    public static final UFOMOAnimation RAINBOW_ANIMATION3 = new RainbowAnimation3();

    public static class RainbowAnimation1 extends UFOMOAnimation {
        @Override
        protected void configAnimations() {
            Addon[] addons = {new ConstCyclicMoveAddon(false)};
            animations.add(new Animation(ufomoObject.bigCircle, Coloring.RAINBOW_COLORING, addons));
            animations.add(new Animation(ufomoObject.mediumCircle, Coloring.RAINBOW_COLORING, addons));
            animations.add(new Animation(ufomoObject.smallCircle, Coloring.RAINBOW_COLORING, addons));
        }
    };

    public static class RainbowAnimation2 extends UFOMOAnimation {
        @Override
        protected void configAnimations() {
            Addon[] addons1 = {new ConstCyclicMoveAddon(false)};
            Addon[] addons2 = {new ConstCyclicMoveAddon(true)};
            animations.add(new Animation(ufomoObject.bigCircle, Coloring.RAINBOW_COLORING, addons1));
            animations.add(new Animation(ufomoObject.mediumCircle, Coloring.RAINBOW_COLORING, addons2));
            animations.add(new Animation(ufomoObject.smallCircle, Coloring.RAINBOW_COLORING, addons1));
        }
    };


    public static class RainbowAnimation3 extends UFOMOAnimation {
        @Override
        protected void configAnimations() {
            Addon[] addons1 = {new ConstCyclicMoveAddon(false)};
            Addon[] addons2 = {
                    new ConstCyclicMoveAddon(false),
                    new OneTimeCyclicMoveAddon(0.33)
            };
            Addon[] addons3 = {
                    new ConstCyclicMoveAddon(false),
                    new OneTimeCyclicMoveAddon(0.66)
            };
            animations.add(new Animation(ufomoObject.bigCircle, Coloring.RAINBOW_COLORING, addons1));
            animations.add(new Animation(ufomoObject.mediumCircle, Coloring.RAINBOW_COLORING, addons2));
            animations.add(new Animation(ufomoObject.smallCircle, Coloring.RAINBOW_COLORING, addons3));
        }
    };
}
