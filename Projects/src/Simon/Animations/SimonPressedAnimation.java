public class SimonPressedAnimation {

    ConstColoring pressed = new ConstColoring(HSBColor.YELLOW);
    ConstColoring black = new ConstColoring(HSBColor.WHITE);

    public void apply(SignLEDObject sign, boolean[] pressStates) {
        for (int i = 0; i < sign.gameFingers.length; i++) {
            if (pressStates[i]) {
                pressed.color(sign.gameFingers[i]);
            } else {
                black.color(sign.gameFingers[i]);
            }
        }
    }
}
