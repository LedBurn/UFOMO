
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

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i], Coloring.RAINBOW_COLORING, addons));
            }

            Coloring octagonColoring = new ConstColoring(new HSBColor(0,1.0,1.0));
            Addon[] octagonAddons = {new ChangeHueAddon()};

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i], octagonColoring, octagonAddons));
            }
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

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i], Coloring.RAINBOW_COLORING, addons1));
            }

            Coloring octagonColoring = new ConstColoring(new HSBColor(0,1.0,1.0));
            Addon[] octagonAddons = {new ChangeHueAddon()};

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i], octagonColoring, octagonAddons));
            }
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

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i], Coloring.RAINBOW_COLORING, addons1));
            }

            Coloring octagonColoring = new ConstColoring(new HSBColor(0,1.0,1.0));
            Addon[] octagonAddons = {new ChangeHueAddon()};

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i], octagonColoring, octagonAddons));
            }
        }
    };
}
