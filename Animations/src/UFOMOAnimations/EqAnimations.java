
public class EqAnimations {

    public static class EqAnimation1 extends UFOMOAnimation {

        @Override
        protected void newBeat() {

        }

        @Override
        protected void configAnimations() {
//            animations.add(new DandooAnimation(ufomoObject.bigCircle, 32, true));
//            animations.add(new DandooAnimation(ufomoObject.mediumCircle, 32, true));
//            animations.add(new DandooAnimation(ufomoObject.smallCircle, 32, true));
            Addon[] addons = {new EqAddon()};
            for (int i = 0; i < ufomoObject.lines.length; i++) {
                animations.add(new Animation(ufomoObject.lines[i], Coloring.RAINBOW_COLORING, addons));
            }

//            for (int i = 0; i < ufomoObject.beam.length; i++) {
//                animations.add(new DandooAnimation(ufomoObject.beam[i],4, true));
//            }
//
//            for (int i = 0; i < ufomoObject.octagon.length; i++) {
//                animations.add(new DandooAnimation(ufomoObject.octagon[i],2, true));
//            }
        }
    }
}
