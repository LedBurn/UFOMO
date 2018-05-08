
public class DandooAnimations {

    public static final UFOMOAnimation DANDOO_ANIMATION1 = new DandooUFOMOAnimation1();
    public static final UFOMOAnimation DANDOO_ANIMATION2 = new DandooUFOMOAnimation2();
    public static final UFOMOAnimation DANDOO_ANIMATION3 = new DandooUFOMOAnimation3();

    public static class DandooUFOMOAnimation1 extends UFOMOAnimation {

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {
            animations.add(new DandooAnimation(ufomoObject.bigCircle, 32, false));
            animations.add(new DandooAnimation(ufomoObject.mediumCircle, 32, false));
            animations.add(new DandooAnimation(ufomoObject.smallCircle, 32, false));

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new DandooAnimation(ufomoObject.lines[i],4, false));
            }

            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new DandooAnimation(ufomoObject.beam[i],4, false));
            }

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new DandooAnimation(ufomoObject.octagon[i],1, false));
            }
        }
    }

    public static class DandooUFOMOAnimation2 extends UFOMOAnimation {

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {
            animations.add(new DandooAnimation(ufomoObject.bigCircle, 32, true));
            animations.add(new DandooAnimation(ufomoObject.mediumCircle, 32, true));
            animations.add(new DandooAnimation(ufomoObject.smallCircle, 32, true));

            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new DandooAnimation(ufomoObject.lines[i],4, true));
            }

            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new DandooAnimation(ufomoObject.beam[i],4, true));
            }

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new DandooAnimation(ufomoObject.octagon[i],2, true));
            }
        }
    }

    public static class DandooUFOMOAnimation3 extends UFOMOAnimation {

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {

            Addon[] line1 = {new ConstChangeHueAddon(0.33)};
            Addon[] line2 = {new ConstChangeHueAddon(0.66)};

            animations.add(new DandooAnimation(ufomoObject.bigCircle, 32, false, line1));
            animations.add(new DandooAnimation(ufomoObject.mediumCircle, 32, false, line2));
            animations.add(new DandooAnimation(ufomoObject.smallCircle, 32, false, line1));


            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new DandooAnimation(ufomoObject.lines[i],4, false, (i / 2) % 2 == 0 ? line1 : line2));
            }

            for (int i = 0; i < ufomoObject.beam.length; i++) {
                animations.add(new DandooAnimation(ufomoObject.beam[i],4, false));
            }

            for (int i = 0; i < ufomoObject.octagon.length; i++) {
                animations.add(new DandooAnimation(ufomoObject.octagon[i],2, false));
            }
        }
    }
}
