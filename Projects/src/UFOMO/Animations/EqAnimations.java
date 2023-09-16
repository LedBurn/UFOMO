//
//public class EqAnimations {
//
//    public static class EqAnimation1 extends Animation<UFOMOObject> {
//
//        private ConstColoring coloring;
//
//        public EqAnimation1(UFOMOObject ufomoObject) {
//            super(ufomoObject);
//        }
//
//        @Override
//        protected void newBeat() {
//
//        }
//
//        @Override
//        protected void configAnimations() {
//            coloring = new ConstColoring(new HSBColor(Math.random(), 1, 1));
//
//            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, coloring, new Addon[]{
//                    new EqConstAddon(), new ChangeHueByTimeAddon()
//            }, 10));
//            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, coloring, new Addon[]{
//                    new EqConstAddon(), new ChangeHueByTimeAddon()
//            }, 10));
//            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, coloring, new Addon[]{
//                    new EqConstAddon(), new ChangeHueByTimeAddon()
//            }, 10));
//
//            for (int i = 0; i < ledObject.octagon.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], coloring, new Addon[]{
//                        new EqConstAddon(), new ChangeHueByTimeAddon()
//                }, 10));
//            }
//
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring, new Addon[]{
//                        new EqConstAddon(i/2), new ChangeHueByTimeAddon()
//                }, 10));
//            }
//
//            HSBColor color = new HSBColor(Math.random(), 0.0, 0.4);
//            for (int i = 0; i < ledObject.beam.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.beam[i],
//                        new BlurColoring(color, ledObject.beam[i].numOfPixels()),
//                        new Addon[] {new ConstCyclicMoveAddon(false)},3));
//            }
//        }
//    }
//
//    public static class EqAnimation2 extends Animation<UFOMOObject> {
//
//        private ConstColoring coloring;
//
//        public EqAnimation2(UFOMOObject ufomoObject) {
//            super(ufomoObject);
//        }
//
//        @Override
//        protected void newBeat() {
//
//        }
//
//        @Override
//        protected void configAnimations() {
//            coloring = new ConstColoring(new HSBColor(Math.random(), 1, 1));
//
//            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, coloring, new Addon[]{
//                    new EqConstAddon(), new ChangeHueByTimeAddon()
//            }, 10));
//            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, coloring, new Addon[]{
//                    new EqConstAddon(), new ChangeHueByTimeAddon()
//            }, 10));
//            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, coloring, new Addon[]{
//                    new EqConstAddon(), new ChangeHueByTimeAddon()
//            }, 10));
//
//            for (int i = 0; i < ledObject.octagon.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], coloring, new Addon[]{
//                        new EqConstAddon(), new ChangeHueByTimeAddon()
//                }, 10));
//            }
//
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring, new Addon[]{
//                        new EqConstAddon(), new ChangeHueByTimeAddon()
//                }, 10));
//            }
//
//            HSBColor color = new HSBColor(Math.random(), 0.0, 0.4);
//            for (int i = 0; i < ledObject.beam.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.beam[i],
//                        new BlurColoring(color, ledObject.beam[i].numOfPixels()),
//                        new Addon[] {new ConstCyclicMoveAddon(false)},3));
//            }
//        }
//    }
//
//    public static class EqAnimation3 extends Animation<UFOMOObject> {
//
//        private ConstColoring coloring;
//
//        public EqAnimation3(UFOMOObject ufomoObject) {
//            super(ufomoObject);
//        }
//
//        @Override
//        protected void newBeat() {
//
//        }
//
//        @Override
//        protected void configAnimations() {
//            coloring = new ConstColoring(new HSBColor(Math.random(), 1, 1));
//
//            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, coloring, new Addon[]{
//                    new EqConstAddon(), new ChangeHueByTimeAddon()
//            }, 10));
//            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, coloring, new Addon[]{
//                    new EqConstAddon(), new ChangeHueByTimeAddon()
//            }, 10));
//            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, coloring, new Addon[]{
//                    new EqConstAddon(), new ChangeHueByTimeAddon()
//            }, 10));
//
//            for (int i = 0; i < ledObject.octagon.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], coloring, new Addon[]{
//                        new EqConstAddon(), new ChangeHueByTimeAddon()
//                }, 10));
//            }
//
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring, new Addon[]{
//                        new EqConstAddon(i/2), new ChangeHueByTimeAddon()
//                }, 10));
//            }
//
//
//            for (int i = 0; i < ledObject.beam.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.beam[i], coloring, new Addon[]{
//                        new EqConstAddon(), new ChangeHueByTimeAddon()
//                }, 10));
//            }
//        }
//    }
//
//    public static class EqAnimation4 extends Animation<UFOMOObject> {
//
//        private ConstColoring coloring;
//
//        public EqAnimation4(UFOMOObject ufomoObject) {
//            super(ufomoObject);
//        }
//
//        @Override
//        protected void newBeat() {
//
//        }
//
//        @Override
//        protected void configAnimations() {
//            coloring = new ConstColoring(new HSBColor(Math.random(), 1, 1));
//
//            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, coloring, new Addon[]{
//                    new EqConstAddon(), new ChangeHueByTimeAddon()
//            }, 10));
//            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, coloring, new Addon[]{
//                    new EqConstAddon(), new ChangeHueByTimeAddon()
//            }, 10));
//            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, coloring, new Addon[]{
//                    new EqConstAddon(), new ChangeHueByTimeAddon()
//            }, 10));
//
//            for (int i = 0; i < ledObject.octagon.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], coloring, new Addon[]{
//                        new EqConstAddon(), new ChangeHueByTimeAddon()
//                }, 10));
//            }
//
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring, new Addon[]{
//                        new EqConstAddon(i/2), new ChangeHueByTimeAddon()
//                }, 10));
//            }
//
//
//            for (int i = 0; i < ledObject.beam.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.beam[i], coloring, new Addon[]{
//                        new EqConstAddon(ledObject.beam.length - i - 1), new ChangeHueByTimeAddon()
//                }, 10));
//            }
//        }
//    }
//
//    public static class EqAnimation5 extends Animation<UFOMOObject> {
//
//        private ConstColoring coloring;
//
//        public EqAnimation5(UFOMOObject ufomoObject) {
//            super(ufomoObject);
//        }
//
//        @Override
//        protected void newBeat() {
//
//        }
//
//        @Override
//        protected void configAnimations() {
//            coloring = new ConstColoring(new HSBColor(Math.random(), 1, 1));
//
//            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, coloring, new Addon[]{
//                   new ChangeHueByTimeAddon()
//            }, 10));
//            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, coloring, new Addon[]{
//                    new ChangeHueByTimeAddon()
//            }, 10));
//            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, coloring, new Addon[]{
//                    new ChangeHueByTimeAddon()
//            }, 10));
//
//            for (int i = 0; i < ledObject.octagon.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], coloring, new Addon[]{
//                        new EqAddon(), new ChangeHueByTimeAddon()
//                }, 10));
//            }
//
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.lines[i], coloring, new Addon[]{
//                        new EqAddon(i/2), new ChangeHueByTimeAddon()
//                }, 10));
//            }
//
//            HSBColor color = new HSBColor(Math.random(), 0.0, 0.4);
//            for (int i = 0; i < ledObject.beam.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.beam[i], coloring, new Addon[]{
//                        new EqAddon(true), new ChangeHueByTimeAddon()
//                }, 10));
//            }
//        }
//    }
//}
