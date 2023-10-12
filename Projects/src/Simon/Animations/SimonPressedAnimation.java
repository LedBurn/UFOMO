public class SimonPressedAnimation {

    ConstColoring red = new ConstColoring(LEDColor.RED);
    ConstColoring blue = new ConstColoring(LEDColor.BLUE);
    ConstColoring green = new ConstColoring(LEDColor.GREEN);
    ConstColoring yellow = new ConstColoring(LEDColor.YELLOW);
    ConstColoring[] colorings = new ConstColoring[]{ red, green, blue, yellow };

    public void apply(SignLEDObject sign, boolean[] pressStates) {
        int colorId = -1;
        for (int i = 0; i < pressStates.length; i++) {
            if (pressStates[i]) {
                colorId = i;
            }
        }

        for (IPixelsArray obj : sign.all) {
            Coloring.RAINBOW_COLORING.color(obj);
        }

        for (IPixelsArray obj : sign.hand) {
            Coloring.GRAY_COLORING.color(obj);
        }
        if (colorId != -1 && colorId < colorings.length) {
            for (int i = 0; i < sign.hand.length; i++) {
                colorings[colorId].color(sign.hand[i]);
            }
        }
    }
}
