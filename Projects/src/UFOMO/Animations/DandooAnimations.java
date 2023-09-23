//
//public class DandooAnimations {
//
//    public static class DandooUFOMOAnimation1 extends LedObjectAnimation<UFOMOObject> {
//
//        public DandooUFOMOAnimation1(UFOMOObject ufomoObject) {
//            super(ufomoObject);
//        }
//
//        @Override
//        protected void configAnimations() {
//            animations.add(new DandooAnimation(ledObject.bigCircle, 32, false));
//            animations.add(new DandooAnimation(ledObject.mediumCircle, 32, false));
//            animations.add(new DandooAnimation(ledObject.smallCircle, 32, false));
//
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new DandooAnimation(ledObject.lines[i],4, false));
//            }
//
//            for (int i = 0; i < ledObject.beam.length; i++) {
//                animations.add(new DandooAnimation(ledObject.beam[i],4, false));
//            }
//
//            for (int i = 0; i < ledObject.octagon.length; i++) {
//                animations.add(new DandooAnimation(ledObject.octagon[i],1, false));
//            }
//        }
//    }
//
//    public static class DandooUFOMOAnimation2 extends LedObjectAnimation<UFOMOObject> {
//
//        public DandooUFOMOAnimation2(UFOMOObject ufomoObject) {
//            super(ufomoObject);
//        }
//
//        @Override
//        protected void configAnimations() {
//            animations.add(new DandooAnimation(ledObject.bigCircle, 32, true));
//            animations.add(new DandooAnimation(ledObject.mediumCircle, 32, true));
//            animations.add(new DandooAnimation(ledObject.smallCircle, 32, true));
//
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new DandooAnimation(ledObject.lines[i],4, true));
//            }
//
//            for (int i = 0; i < ledObject.beam.length; i++) {
//                animations.add(new DandooAnimation(ledObject.beam[i],4, true));
//            }
//
//            for (int i = 0; i < ledObject.octagon.length; i++) {
//                animations.add(new DandooAnimation(ledObject.octagon[i],2, true));
//            }
//        }
//    }
//
//    public static class DandooUFOMOAnimation3 extends LedObjectAnimation<UFOMOObject> {
//
//        public DandooUFOMOAnimation3(UFOMOObject ufomoObject) {
//            super(ufomoObject);
//        }
//
//        @Override
//        protected void configAnimations() {
//
//            Addon[] line1 = {new ConstChangeHueAddon(0.33)};
//            Addon[] line2 = {new ConstChangeHueAddon(0.66)};
//
//            animations.add(new DandooAnimation(ledObject.bigCircle, 32, false, line1));
//            animations.add(new DandooAnimation(ledObject.mediumCircle, 32, false, line2));
//            animations.add(new DandooAnimation(ledObject.smallCircle, 32, false, line1));
//
//
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new DandooAnimation(ledObject.lines[i],4, false, (i / 2) % 2 == 0 ? line1 : line2));
//            }
//
//            for (int i = 0; i < ledObject.beam.length; i++) {
//                animations.add(new DandooAnimation(ledObject.beam[i],4, false));
//            }
//
//            for (int i = 0; i < ledObject.octagon.length; i++) {
//                animations.add(new DandooAnimation(ledObject.octagon[i],2, false));
//            }
//        }
//    }
//}
