import java.util.ArrayList;

public class SimonSequenceAnimation extends SimonAnimation {

    ArrayList<Integer> sequence;

    ConstColoring red = new ConstColoring(HSBColor.RED);
    ConstColoring black = new ConstColoring(HSBColor.WHITE);

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
        int buttonId = sequence.get(index);

        double level = timePercent * sequence.size() - index;

        for (int i = 0; i < sign.gameFingers.length; i++) {
            if (i == buttonId && level > 0.2 && level < 0.9) {
                red.color(sign.gameFingers[i]);
            } else {
                black.color(sign.gameFingers[i]);
            }
        }
    }
}
