public class SimonPressedAnimation {

    ConstColoring red = new ConstColoring(HSBColor.RED);
    ConstColoring blue = new ConstColoring(HSBColor.BLUE);
    ConstColoring green = new ConstColoring(HSBColor.GREEN);
    ConstColoring yellow = new ConstColoring(HSBColor.YELLOW);
    ConstColoring black = new ConstColoring(HSBColor.BLACK);
    ConstColoring[] colorings = new ConstColoring[]{ red, blue, green, yellow };

    public void apply(SignLEDObject sign, boolean[] pressStates) {
        int colorId = -1;
        for (int i = 0; i < pressStates.length; i++) {
            if (pressStates[i]) {
                colorId = i;
            }
        }

        Coloring coloring = black;
        if (colorId != -1 && colorId < colorings.length) {
            coloring = colorings[colorId];
        }
        for (IPixelsArray obj : sign.all) {
            coloring.color(obj);
        }
    }
}
