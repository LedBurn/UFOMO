//public class BabushkaAlternateAnimations {
//
//    public static class AlternateAnimationHalf extends Animation<BabushkaObject>{
//
//        public AlternateAnimationHalf(BabushkaObject babushkaObject) {
//            super(babushkaObject);
//        }
//
//        HSBColor color1;
//        HSBColor color2;
//        boolean first;
//
//        @Override
//        protected void configAnimations() {
//            double hue1 = Math.random();
//            double hue2 = hue1 + 0.25 + Math.random() * 0.5;
//            color1 = new HSBColor(hue1, 1.0, 1.0);
//            color2 = new HSBColor(hue2, 1.0, 1.0);
//            first = false;
//        }
//
//        @Override
//        protected void newBeat() {}
//
//        @Override
//        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
//            super.apply(timePercent, newBeat, isOn, eq);
//
//            if (newBeat) {
//                first = !first;
//            }
//
//            for (int i = 0; i < ledObject.babushkas.length; i++) {
//                boolean normalBabushka = (first && i%2==0) || (!first && i%2==1);
//                for (int j = 0; j < ledObject.babushkas[i].numOfPixels(); j++) {
//                     boolean normalColor = (normalBabushka && j<ledObject.babushkas[i].numOfPixels()/2.0) ||
//                             (!normalBabushka && j>=ledObject.babushkas[i].numOfPixels()/2.0);
//                    ledObject.babushkas[i].setColor(j, normalColor ? color1 : color2);
//                }
//            }
//        }
//    }
//
//    public static class AlternateAnimationFull extends Animation<BabushkaObject>{
//
//        public AlternateAnimationFull(BabushkaObject babushkaObject) {
//            super(babushkaObject);
//        }
//
//        HSBColor color1;
//        HSBColor color2;
//        boolean first;
//
//        @Override
//        protected void configAnimations() {
//            double hue1 = Math.random();
//            double hue2 = hue1 + 0.25 + Math.random() * 0.5;
//            color1 = new HSBColor(hue1, 1.0, 1.0);
//            color2 = new HSBColor(hue2, 1.0, 1.0);
//            first = false;
//        }
//
//        @Override
//        protected void newBeat() {}
//
//        @Override
//        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
//            super.apply(timePercent, newBeat, isOn, eq);
//
//            if (newBeat) {
//                first = !first;
//            }
//
//            for (int i = 0; i < ledObject.babushkas.length; i++) {
//                boolean normalBabushka = (first && i%2==0) || (!first && i%2==1);
//                for (int j = 0; j < ledObject.babushkas[i].numOfPixels(); j++) {
//                    ledObject.babushkas[i].setColor(j, normalBabushka ? color1 : color2);
//                }
//            }
//        }
//    }
//
//    public static class AlternateAnimationHalfMove extends Animation<BabushkaObject>{
//
//        public AlternateAnimationHalfMove(BabushkaObject babushkaObject) {
//            super(babushkaObject);
//        }
//
//        HSBColor color1;
//        HSBColor color2;
//        ConstCyclicMoveAddon addon;
//        boolean first;
//
//        @Override
//        protected void configAnimations() {
//            double hue1 = Math.random();
//            double hue2 = hue1 + 0.25 + Math.random() * 0.5;
//            color1 = new HSBColor(hue1, 1.0, 1.0);
//            color2 = new HSBColor(hue2, 1.0, 1.0);
//            first = false;
//            addon = new ConstCyclicMoveAddon(Math.random()<0.5);
//        }
//
//        @Override
//        protected void newBeat() {}
//
//        @Override
//        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
//            super.apply(timePercent, newBeat, isOn, eq);
//
//            for (int i = 0; i < ledObject.babushkas.length; i++) {
//                boolean normalBabushka = i%2==0;
//                for (int j = 0; j < ledObject.babushkas[i].numOfPixels(); j++) {
//                    boolean normalColor = (normalBabushka && j<ledObject.babushkas[i].numOfPixels()/2.0) ||
//                            (!normalBabushka && j>=ledObject.babushkas[i].numOfPixels()/2.0);
//                    ledObject.babushkas[i].setColor(j, normalColor ? color1 : color2);
//                }
//
//                addon.change(ledObject.babushkas[i], timePercent, false, false, null);
//            }
//
//
//        }
//    }
//
//
//    public static class AlternateAnimationHalfJump extends Animation<BabushkaObject>{
//
//        public AlternateAnimationHalfJump(BabushkaObject babushkaObject) {
//            super(babushkaObject);
//        }
//
//        HSBColor color1;
//        HSBColor color2;
//        ConstCyclicMoveAddon addon;
//
//        @Override
//        protected void configAnimations() {
//            double hue1 = Math.random();
//            double hue2 = hue1 + 0.25 + Math.random() * 0.5;
//            color1 = new HSBColor(hue1, 1.0, 1.0);
//            color2 = new HSBColor(hue2, 1.0, 1.0);
//            addon = new ConstCyclicMoveAddon(Math.random()<0.5);
//        }
//
//        @Override
//        protected void newBeat() {}
//
//        @Override
//        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
//            super.apply(timePercent, newBeat, isOn, eq);
//
//            for (int i = 0; i < ledObject.babushkas.length; i++) {
//                for (int j = 0; j < ledObject.babushkas[i].numOfPixels(); j++) {
//                    ledObject.babushkas[i].setColor(j, j>=ledObject.babushkas[i].numOfPixels()/2.0 ? color1 : color2);
//                }
//
//                double level = timePercent < 0.5 ? timePercent : 1.0 - timePercent;
//                addon.change(ledObject.babushkas[i], level, false, false, null);
//            }
//        }
//    }
//
//}
