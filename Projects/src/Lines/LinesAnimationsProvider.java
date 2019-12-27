public class LinesAnimationsProvider implements ISimpleRunnerAnimationsProvider {

    boolean fireMode = false;

    @Override
    public Animation randomNewAnimation() {
        if (fireMode) {
            return new LinesAnimations.FireAnimation(new LinesObject());
        }

        int num = (int)Math.floor(Math.random() * 18);
        switch (num) {
            case 0:
                return new LinesAnimations.RainbowAnimation1(new LinesObject());
            case 1:
                return new LinesAnimations.RainbowAnimation2(new LinesObject());
            case 2:
                return new LinesAnimations.AlternateAnimation1(new LinesObject());
            case 3:
                return new LinesAnimations.ConfettiAnimation1(new LinesObject());
            case 4:
                return new LinesAnimations.ConfettiAnimation2(new LinesObject());
            case 5:
                return new LinesAnimations.DandooAnimation1(new LinesObject());
            case 6:
                return new LinesAnimations.DandooAnimation2(new LinesObject());
            case 7:
                return new LinesAnimations.RandomAnimation1(new LinesObject());
            case 8:
                return new LinesAnimations.EQAnimation1(new LinesObject());
            case 9:
                return new LinesAnimations.EQAnimation2(new LinesObject());
            case 10:
                return new LinesAnimations.EQAnimation3(new LinesObject());
            case 11:
                return new LinesAnimations.EQAnimation4(new LinesObject());
            case 12:
                return new LinesAnimations.EQHueAnimation1(new LinesObject());
            case 13:
                return new LinesAnimations.EQHueAnimation2(new LinesObject());
            case 14:
                return new LinesAnimations.SpikesAnimation1(new LinesObject());
            case 15:
                return new LinesAnimations.SpikesAnimation2(new LinesObject());
            case 16:
                return new LinesAnimations.SpikesAnimation3(new LinesObject());
            case 17:
                return new LinesAnimations.FireAnimation(new LinesObject());
        }
        return null;
    }

    @Override
    public void handleUserCode(int code) {
        if (code == 1) {
            fireMode = false;
        }

        if (code == 2) {
            fireMode = true;
        }
    }
}
