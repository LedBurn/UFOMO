//public class BabushkaDancingDotAnimations {
//
//    public static class DancingDotSingle extends Animation<BabushkaObject>{
//
//        private int currentElement;
//        private int currentPosition;
//        private boolean reversed;
//
//        public DancingDotSingle(BabushkaObject babushkaObject) {
//            super(babushkaObject);
//        }
//
//        @Override
//        protected void configAnimations() {
//            for (int i = 0; i < ledObject.babushkas.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.babushkas[i],
//                        new ConstColoring(new HSBColor(Math.random(), 1.0, 0.6)),
//                        new Addon[] {new ChangeHueByTimeAddon()},9));
//            }
//            currentElement = (int)Math.floor(Math.random()*ledObject.babushkas.length);
//            if (Math.random() < 0.5) {
//            currentPosition = 0;
//            reversed = false;
//            } else {
//                currentPosition = ledObject.babushkas[currentElement].numOfPixels()-1;
//                reversed = true;
//            }
//        }
//
//        @Override
//        protected void newBeat() {}
//
//
//        @Override
//        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
//            super.apply(timePercent, newBeat, isOn, eq);
//
//            if (reversed) {
//                if (currentPosition >= 0) {
//                    ledObject.babushkas[currentElement].setColor(currentPosition, HSBColor.WHITE);
//                }
//                currentPosition--;
//
//                if (currentPosition + 5 == 0) {
//                    currentElement = (int)Math.floor(Math.random()*ledObject.babushkas.length);
//                    currentPosition = 0;
//                    reversed = false;
//                }
//
//            } else {
//                if (currentPosition < ledObject.babushkas[currentElement].numOfPixels()) {
//                    ledObject.babushkas[currentElement].setColor(currentPosition, HSBColor.WHITE);
//                }
//                currentPosition++;
//
//                if (currentPosition - 5 == ledObject.babushkas[currentElement].numOfPixels()) {
//                    currentElement = (int)Math.floor(Math.random()*ledObject.babushkas.length);
//                    currentPosition = ledObject.babushkas[currentElement].numOfPixels()-1;
//                    reversed = true;
//                }
//            }
//        }
//    }
//
//    public static class DancingDotMultiple extends Animation<BabushkaObject>{
//
//        private int[] currentElement;
//        private int[] currentPosition;
//        private boolean[] reversed;
//
//        public DancingDotMultiple(BabushkaObject babushkaObject) {
//            super(babushkaObject);
//        }
//
//        @Override
//        protected void configAnimations() {
//            for (int i = 0; i < ledObject.babushkas.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.babushkas[i],
//                        new ConstColoring(new HSBColor(Math.random(), 1.0, 0.6)),
//                        new Addon[] {new ChangeHueByTimeAddon()},9));
//            }
//
//            currentElement = new int[5];
//            currentPosition = new int[5];
//            reversed = new boolean[5];
//
//            for (int i = 0; i < 5; i++) {
//                currentElement[i] = (int)Math.floor(Math.random()*ledObject.babushkas.length);
//                if (Math.random() < 0.5) {
//                    currentPosition[i] = 0;
//                    reversed[i] = false;
//                } else {
//                    currentPosition[i] = ledObject.babushkas[currentElement[i]].numOfPixels()-1;
//                    reversed[i] = true;
//                }
//            }
//
//        }
//
//        @Override
//        protected void newBeat() {}
//
//
//        @Override
//        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
//            super.apply(timePercent, newBeat, isOn, eq);
//
//            for (int i = 0; i < 5; i++) {
//                if (reversed[i]) {
//                    if (currentPosition[i] >= 0) {
//                        ledObject.babushkas[currentElement[i]].setColor(currentPosition[i], HSBColor.WHITE);
//                    }
//                    currentPosition[i]--;
//
//                    if (currentPosition[i] + 5 == 0) {
//                        currentElement[i] = (int) Math.floor(Math.random() * ledObject.babushkas.length);
//                        currentPosition[i] = 0;
//                        reversed[i] = false;
//                    }
//
//                } else {
//                    if (currentPosition[i] < ledObject.babushkas[currentElement[i]].numOfPixels()) {
//                        ledObject.babushkas[currentElement[i]].setColor(currentPosition[i], HSBColor.WHITE);
//                    }
//                    currentPosition[i]++;
//
//                    if (currentPosition[i] - 5 == ledObject.babushkas[currentElement[i]].numOfPixels()) {
//                        currentElement[i] = (int) Math.floor(Math.random() * ledObject.babushkas.length);
//                        currentPosition[i] = ledObject.babushkas[currentElement[i]].numOfPixels() - 1;
//                        reversed[i] = true;
//                    }
//                }
//            }
//        }
//    }
//
//
//    public static class DancingDotCrazy extends Animation<BabushkaObject>{
//
//        private int[] currentElement;
//        private int[] currentPosition;
//        private boolean[] reversed;
//
//        public DancingDotCrazy(BabushkaObject babushkaObject) {
//            super(babushkaObject);
//        }
//
//        @Override
//        protected void configAnimations() {
//            for (int i = 0; i < ledObject.babushkas.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.babushkas[i],
//                        new ConstColoring(new HSBColor(Math.random(), 1.0, 0.6)),
//                        new Addon[] {new ChangeHueByTimeAddon()},9));
//            }
//
//            currentElement = new int[30];
//            currentPosition = new int[30];
//            reversed = new boolean[30];
//
//            for (int i = 0; i < 30; i++) {
//                currentElement[i] = (int)Math.floor(Math.random()*ledObject.babushkas.length);
//                currentPosition[i] = (int)Math.floor(Math.random()*ledObject.babushkas[currentElement[i]].numOfPixels());
//                reversed[i] = Math.random() > 0.5;
//            }
//        }
//
//        @Override
//        protected void newBeat() {}
//
//
//        @Override
//        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
//            super.apply(timePercent, newBeat, isOn, eq);
//
//            for (int i = 0; i < 30; i++) {
//                if (reversed[i]) {
//                    if (currentPosition[i] >= 0) {
//                        ledObject.babushkas[currentElement[i]].setColor(currentPosition[i], HSBColor.WHITE);
//                    }
//                    currentPosition[i]--;
//
//                    if (currentPosition[i] + 5 == 0) {
//                        currentElement[i] = (int) Math.floor(Math.random() * ledObject.babushkas.length);
//                        currentPosition[i] = 0;
//                        reversed[i] = false;
//                    }
//
//                } else {
//                    if (currentPosition[i] < ledObject.babushkas[currentElement[i]].numOfPixels()) {
//                        ledObject.babushkas[currentElement[i]].setColor(currentPosition[i], HSBColor.WHITE);
//                    }
//                    currentPosition[i]++;
//
//                    if (currentPosition[i] - 5 == ledObject.babushkas[currentElement[i]].numOfPixels()) {
//                        currentElement[i] = (int) Math.floor(Math.random() * ledObject.babushkas.length);
//                        currentPosition[i] = ledObject.babushkas[currentElement[i]].numOfPixels() - 1;
//                        reversed[i] = true;
//                    }
//                }
//            }
//        }
//    }
//}
