import java.util.ArrayList;

public class AlternateAnimations {

    public static final UFOMOAnimation ALTERNATE_ANIMATION1 = new AlternateAnimation1();
    public static final UFOMOAnimation ALTERNATE_ANIMATION2 = new AlternateAnimation2();

    public static class AlternateAnimation1 extends UFOMOAnimation {

        private double hue1;
        private double hue2;

        private AlternateSmoothAddon smooth50;
        private AlternateSmoothAddon smooth15;
        private ConstColoring octagonColoring;

        @Override
        protected void newBeat() {
            hue1 += 0.1;
            hue2 += 0.1;

            if (smooth50 != null) smooth50.changeHue(hue1, hue2);
            if (smooth15 != null) smooth15.changeHue(hue1, hue2);
            if (octagonColoring != null) octagonColoring.changeColor(new HSBColor(hue1, 1, 1));
        }

        @Override
        protected void configAnimations() {

            hue1 = Math.random();
            hue2 = hue1 + 0.4;

            smooth50 = new AlternateSmoothAddon(hue1, hue2, 50);
            smooth15 = new AlternateSmoothAddon(hue1, hue2, 50);
            octagonColoring = new ConstColoring(new HSBColor(hue1, 1, 1));

            animations.add(new Animation(ufomoObject.bigCircle, null, new Addon[]{
                    smooth50,
                    new ConstCyclicMoveAddon(true)}));

            animations.add(new Animation(ufomoObject.mediumCircle, null, new Addon[]{
                    smooth50,
                    new ConstCyclicMoveAddon(false)}));

            animations.add(new Animation(ufomoObject.smallCircle, null, new Addon[]{
                    smooth50,
                    new ConstCyclicMoveAddon(true)}));

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i], null, new Addon[]{
                        smooth15,
                        new ConstCyclicMoveAddon(true)}));
            }

            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new Animation(ufomoObject.beam[i], null, new Addon[]{
                        smooth15,
                        new ConstCyclicMoveAddon(false)}));
            }

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new Animation(ufomoObject.octagon[i],
                        octagonColoring,
                        new Addon[]{}));
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
            Addon alternateAddon = new AlternateAddon(hue1, hue2, 3);

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
