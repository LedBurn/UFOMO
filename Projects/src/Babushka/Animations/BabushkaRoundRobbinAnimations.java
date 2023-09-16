//public class BabushkaRoundRobbinAnimations {
//
//    public static class BabushkaRoundRobbinAnimation extends Animation<BabushkaObject>{
//
//        private int cycleNum;
//        double currHue;
//        boolean reversed;
//        boolean reversedHue;
//
//        public BabushkaRoundRobbinAnimation(BabushkaObject babushkaObject) {
//            super(babushkaObject);
//        }
//
//        @Override
//        protected void configAnimations() {
//            currHue = Math.random();
//            reversed = Math.random() < 0.5;
//            reversedHue = Math.random() < 0.5;
//        }
//
//        @Override
//        protected void newBeat() {
//
//        }
//
//        @Override
//        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
//            super.apply(timePercent, newBeat, isOn, eq);
//
//            if (newBeat) {
//                if (reversedHue) {
//                    currHue += 0.3;
//                    if (currHue > 1) currHue -= 1;
//                } else {
//                    currHue -= 0.3;
//                    if (currHue < 0) currHue += 1;
//                }
//
//                int index = reversed ? cycleNum % ledObject.babushkas.length : ledObject.babushkas.length - 1 - cycleNum % ledObject.babushkas.length;
//
//                IPixelsArray babushka = ledObject.babushkas[index];
//                Coloring coloring = new ConstColoring(new HSBColor(currHue, 1.0, 1.0));
//                coloring.color(babushka);
//
//                cycleNum++;
//            }
//        }
//    }
//}
