//public class CirclesAnimations {
//    private Circle bigCircle;
//    private Circle mediumCircle;
//    private Circle smallCircle;
//
//
//    public AddonsContainer randomAnimation(Circle bigCircle, Circle mediumCircle, Circle smallCircle) {
//        int animationNum = 0;
//        switch (animationNum) {
//            case 0:
//            ContinuousWhiteEffect effect = new ContinuousWhiteEffect();
//            ContinuousRainbowEffect effect1 = new ContinuousRainbowEffect(effect);
//            ContinuousCyclicMoveEffect effect2 = new ContinuousCyclicMoveEffect(effect1);
//            DiscreteEffect effect3 = getDiscreteEffect(this.bigCircle, effect2)
//
//        }
//    }
//
//    private DiscreteEffect getDiscreteEffect(Circle circle, ContinuousEffect effect) {
//        return new ContinuousToDiscrete(circle.GetPixelsNumber(), effect);
//    }
//
//    private void getContainer(DiscreteEffect[] effects) {
//
//    }
//
//    private class CirclesContainer extends AddonsContainer {
//        private DiscreteEffect bigEffect;
//        private DiscreteEffect mediumEffect;
//        private DiscreteEffect smallEffect;
//
//        public CirclesContainer(Circle[] circles, DiscreteEffect[] effects) {
//            this.bigEffect = effects[0];
//            this.mediumEffect = effects[1];
//            this.smallEffect = effects[2];
//        }
//
//
//        @Override
//        public void apply(double timePercent) {
//
//        }
//    }
//}
