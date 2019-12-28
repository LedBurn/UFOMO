import java.util.ArrayList;

public class SimonSequenceAnimation extends SimonAnimation {

    ArrayList<Integer> sequence;

    ConstColoring red = new ConstColoring(HSBColor.RED);
    ConstColoring blue = new ConstColoring(HSBColor.BLUE);
    ConstColoring green = new ConstColoring(HSBColor.GREEN);
    ConstColoring yellow = new ConstColoring(HSBColor.YELLOW);
    ConstColoring[] colorings = new ConstColoring[]{ red, blue, green, yellow };

    ConstColoring black = new ConstColoring(HSBColor.BLACK);

    public SimonSequenceAnimation(ArrayList<Integer> sequence) {
        super(totalTimeForSequenceSize(sequence.size()));
        this.sequence = sequence;
    }

    private static double totalTimeForSequenceSize(int size) {
        if (size < 4) return size * 1000;
        if (size < 7) return size * 800;
        return size * 700;
    }

    @Override
    public void apply(SignLEDObject sign, double timePercent) {
        int index = (int)Math.floor(timePercent * sequence.size());
        int colorId = sequence.get(index);
        Coloring coloring = colorings[colorId];

        double level = timePercent * sequence.size() - index;

        for (int i = 0; i < sign.all.length; i++) {
            if (level > 0.2 && level < 0.9) {
                coloring.color(sign.all[i]);
            } else {
                black.color(sign.all[i]);
            }
        }
    }
}
