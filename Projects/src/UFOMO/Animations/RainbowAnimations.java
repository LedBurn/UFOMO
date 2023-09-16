//
//public class RainbowAnimations {
//
//    public static class RainbowAnimation1 extends Animation<UFOMOObject> {
//
//        public RainbowAnimation1(UFOMOObject ufomoObject) {
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
//            Addon[] addons = {new ConstCyclicMoveAddon(false)};
//            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, Coloring.RAINBOW_COLORING, addons));
//            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, Coloring.RAINBOW_COLORING, addons));
//            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, Coloring.RAINBOW_COLORING, addons));
//
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.lines[i], Coloring.RAINBOW_REVERESED_COLORING,
//                        new Addon[] {
//                                new ConstCyclicMoveAddon(true),
//                                new EqAddon(i/2)
//                        }));
//            }
//
//            for (int i = 0; i < ledObject.beam.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.beam[i], Coloring.RAINBOW_REVERESED_COLORING,
//                        new Addon[] {
//                                new ConstCyclicMoveAddon(true),
//                                new EqAddon(i, true)
//                        }));
//            }
//
//            Coloring octagonColoring = new ConstColoring(new HSBColor(0,1.0,1.0));
//            Addon[] octagonAddons = {new ChangeHueByTimeAddon()};
//
//            for (int i = 0; i < ledObject.octagon.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], octagonColoring, octagonAddons));
//            }
//        }
//    };
//
//    public static class RainbowAnimation2 extends Animation<UFOMOObject> {
//
//        public RainbowAnimation2(UFOMOObject ufomoObject) {
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
//            Addon[] addons1 = {new ConstCyclicMoveAddon(false)};
//            Addon[] addons2 = {new ConstCyclicMoveAddon(true)};
//            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, Coloring.RAINBOW_COLORING, addons1));
//            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, Coloring.RAINBOW_COLORING, addons2));
//            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, Coloring.RAINBOW_COLORING, addons1));
//
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                Addon[] addons= {
//                        new ConstCyclicMoveAddon(false),
//                        new EqAddon()
//                };
//                animations.add(new PixelsArrayAnimation(ledObject.lines[i], Coloring.RAINBOW_COLORING, addons));
//            }
//
//            for (int i = 0; i < ledObject.beam.length; i++) {
//                Addon[] addons= {
//                        new ConstCyclicMoveAddon(true),
//                        new EqAddon(true)
//                };
//                animations.add(new PixelsArrayAnimation(ledObject.beam[i], Coloring.RAINBOW_COLORING, addons));
//            }
//
//            Coloring octagonColoring = new ConstColoring(new HSBColor(0,1.0,1.0));
//            Addon[] octagonAddons = {new ChangeHueByTimeAddon()};
//
//            for (int i = 0; i < ledObject.octagon.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], octagonColoring, octagonAddons));
//            }
//        }
//    };
//
//
//    public static class RainbowAnimation3 extends Animation<UFOMOObject> {
//
//        public RainbowAnimation3(UFOMOObject ufomoObject) {
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
//            Addon[] addons1 = {new ConstCyclicMoveAddon(false)};
//            Addon[] addons2 = {
//                    new ConstCyclicMoveAddon(false),
//                    new OneTimeCyclicMoveAddon(0.33)
//            };
//            Addon[] addons3 = {
//                    new ConstCyclicMoveAddon(false),
//                    new OneTimeCyclicMoveAddon(0.66)
//            };
//            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, Coloring.RAINBOW_COLORING, addons1));
//            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, Coloring.RAINBOW_COLORING, addons2));
//            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, Coloring.RAINBOW_COLORING, addons3));
//
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.lines[i], Coloring.RAINBOW_COLORING,
//                        new Addon[]{new ConstCyclicMoveAddon(false)}));
//            }
//
//            for (int i = 0; i < ledObject.beam.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.beam[i], Coloring.RAINBOW_REVERESED_COLORING,
//                        new Addon[]{new ConstCyclicMoveAddon(true)}));
//            }
//
//            Coloring octagonColoring = new ConstColoring(new HSBColor(0,1.0,1.0));
//            Addon[] octagonAddons = {new ChangeHueByTimeAddon()};
//
//            for (int i = 0; i < ledObject.octagon.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], octagonColoring, octagonAddons));
//            }
//        }
//    };
//
//    public static class RainbowAnimation4 extends Animation<UFOMOObject> {
//
//        public RainbowAnimation4(UFOMOObject ufomoObject) {
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
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.lines[i], Coloring.RAINBOW_REVERESED_COLORING,
//                        new Addon[] {new ConstCyclicMoveAddon(true)}));
//            }
//
//            HSBColor color = new HSBColor(Math.random(), 0.0, 0.4);
//            for (int i = 0; i < ledObject.beam.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.beam[i],
//                        new BlurColoring(color, ledObject.beam[i].numOfPixels()),
//                        new Addon[] {new ConstCyclicMoveAddon(false)},3));
//            }
//
//            Coloring constColoring = new ConstColoring(new HSBColor(0,1.0,1.0));
//            Addon[] octagonAddons = {new ChangeHueByTimeAddon()};
//
//            for (int i = 0; i < ledObject.octagon.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.octagon[i], constColoring, octagonAddons));
//            }
//
//            Addon[] circlesAddons = {new ChangeHueByTimeAddon(true)};
//            animations.add(new PixelsArrayAnimation(ledObject.bigCircle, Coloring.RAINBOW_COLORING, circlesAddons));
//            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle, Coloring.RAINBOW_COLORING, circlesAddons));
//            animations.add(new PixelsArrayAnimation(ledObject.smallCircle, Coloring.RAINBOW_COLORING, circlesAddons));
//        }
//    };
//}
