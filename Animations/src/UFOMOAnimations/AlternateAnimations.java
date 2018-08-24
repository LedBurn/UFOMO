import java.util.ArrayList;

public class AlternateAnimations {

    public static final UFOMOAnimation ALTERNATE_ANIMATION1 = new AlternateAnimation1();
    public static final UFOMOAnimation ALTERNATE_ANIMATION2 = new AlternateAnimation2();

    public static class AlternateAnimation1 extends UFOMOAnimation {
        @Override
        protected void newBeat() {
        }

        @Override
        protected void configAnimations() {

            double hue1 = Math.random();
            double hue2 = hue1 + 0.4;
            Addon alternateAddon = new AlternateAddon(hue1, hue2, 10);

            animations.add(new Animation(ufomoObject.bigCircle, null, new Addon[]{alternateAddon}));
            animations.add(new Animation(ufomoObject.mediumCircle, null, new Addon[]{alternateAddon}));
            animations.add(new Animation(ufomoObject.smallCircle, null, new Addon[]{alternateAddon}));

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i], null, new Addon[]{alternateAddon}));
            }

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i], null, new Addon[]{alternateAddon}));
            }

            HSBColor color = new HSBColor(Math.random(), 0.0, 0.4);
            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i],
                        new BlurColoring(color, ufomoObject.beam[i].numOfPixels()),
                        new Addon[] {new ConstCyclicMoveAddon(false)},
                        3));
            }
        }
    }

    public static class AlternateAnimation2 extends UFOMOAnimation {

        @Override
        protected void newBeat() {
        }

        @Override
        protected void configAnimations() {

            double hue1 = Math.random();
            double hue2 = hue1 + 0.4;
            Addon alternateAddon = new AlternateAddon(hue1, hue2, 10);

            animations.add(new Animation(ufomoObject.bigCircle, null, new Addon[]{alternateAddon}));
            animations.add(new Animation(ufomoObject.mediumCircle, null, new Addon[]{alternateAddon}));
            animations.add(new Animation(ufomoObject.smallCircle, null, new Addon[]{alternateAddon}));

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i], null, new Addon[]{alternateAddon}));
            }

            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i], null, new Addon[]{alternateAddon}));
            }

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i],null, new Addon[]{alternateAddon}));
            }
        }
    }
}
