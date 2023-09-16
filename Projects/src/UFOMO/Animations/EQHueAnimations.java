//public class EQHueAnimations {
//
//    public static class EqHueAnimation1 extends Animation<UFOMOObject> {
//
//        public EqHueAnimation1(UFOMOObject ufomoObject) {
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
//            HSBColor color = new HSBColor(Math.random(), 1, 1);
//            animations.add(new PixelsArrayAnimation(ledObject.bigCircle,
//                    new AlternateColoring(color, 15),
//                    new Addon[]{
//                            new EqHueAddon(),
//                            new ConstCyclicMoveAddon(true)
//                    }, 12));
//
//            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle,
//                    new AlternateColoring(color, 15),
//                    new Addon[]{
//                            new EqHueAddon(),
//                            new ConstCyclicMoveAddon(true)
//                    }, 12));
//
//            animations.add(new PixelsArrayAnimation(ledObject.smallCircle,
//                    new AlternateColoring(color, 15),
//                    new Addon[]{
//                            new EqHueAddon(),
//                            new ConstCyclicMoveAddon(true)
//                    }, 12));
//
//            for (int i = 0; i < ledObject.octagon.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.octagon[i],
//                        new AlternateColoring(color, 15),
//                        new Addon[]{
//                                new EqHueAddon(),
//                                new ConstCyclicMoveAddon(false)
//                        }, 12));
//            }
//
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.lines[i],
//                        new AlternateColoring(color, 15),
//                        new Addon[]{
//                                new EqHueAddon(),
//                                new ConstCyclicMoveAddon(true)
//                        }, 12));
//            }
//
//            for (int i = 0; i < ledObject.beam.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.beam[i],
//                        new AlternateColoring(color, 15),
//                        new Addon[]{
//                                new EqHueAddon(),
//                                new ConstCyclicMoveAddon(false)
//                        }, 12));
//            }
//        }
//    }
//
//    public static class EqHueAnimation2 extends Animation<UFOMOObject> {
//
//        public EqHueAnimation2(UFOMOObject ufomoObject) {
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
//            HSBColor color = new HSBColor(Math.random(), 1, 1);
//            animations.add(new PixelsArrayAnimation(ledObject.bigCircle,
//                    new ConstColoring(color),
//                    new Addon[]{
//                            new EqHueAddon(),
//                            new ConstCyclicMoveAddon(false)
//                    }, 12));
//
//            animations.add(new PixelsArrayAnimation(ledObject.mediumCircle,
//                    new ConstColoring(color),
//                    new Addon[]{
//                            new EqHueAddon(),
//                            new ConstCyclicMoveAddon(false)
//                    }, 12));
//
//            animations.add(new PixelsArrayAnimation(ledObject.smallCircle,
//                    new ConstColoring(color),
//                    new Addon[]{
//                            new EqHueAddon(),
//                            new ConstCyclicMoveAddon(false)
//                    }, 12));
//
//            for (int i = 0; i < ledObject.octagon.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.octagon[i],
//                        new ConstColoring(color),
//                        new Addon[]{
//                                new EqHueAddon(),
//                                new ConstCyclicMoveAddon(false)
//                        }, 12));
//            }
//
//            for (int i = 0; i < ledObject.lines.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.lines[i],
//                        new ConstColoring(color),
//                        new Addon[]{
//                                new EqHueAddon(),
//                                new ConstCyclicMoveAddon(true)
//                        }, 12));
//            }
//
//            for (int i = 0; i < ledObject.beam.length; i++) {
//                animations.add(new PixelsArrayAnimation(ledObject.beam[i],
//                        new ConstColoring(color),
//                        new Addon[]{
//                                new EqHueAddon(),
//                                new ConstCyclicMoveAddon(false)
//                        }, 12));
//            }
//        }
//    }
//}
