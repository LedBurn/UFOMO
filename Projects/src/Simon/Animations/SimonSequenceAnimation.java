import java.util.ArrayList;

public class SimonSequenceAnimation extends SimonAnimation {

    ArrayList<Integer> sequence;
    boolean[] sounds;

    HSBColor[] colors = new HSBColor[]{ HSBColor.RED, HSBColor.GREEN, HSBColor.BLUE, HSBColor.YELLOW  };
    ConstColoring coloring = new ConstColoring();

    public SimonSequenceAnimation(ArrayList<Integer> sequence) {
        super(totalTimeForSequenceSize(sequence.size()));
        this.sequence = sequence;
        this.sounds = new boolean[sequence.size()];
    }

    private static double totalTimeForSequenceSize(int size) {
        if (size < 4) return size * 1000;
        if (size < 7) return size * 800;
        return size * 700;
    }

    @Override
    public void apply(SignLEDObject sign, double timePercent) {
        int index = (int) Math.floor(timePercent * sequence.size());
        int colorId = sequence.get(index);
        HSBColor color = colors[colorId];
        coloring.changeColor(color);

        double level = timePercent * sequence.size() - index;

        for (IPixelsArray obj : sign.all) {
            Coloring.RAINBOW_COLORING.color(obj);
        }
        for (int i = 0; i < sign.hand.length; i++) {
            Coloring.GRAY_COLORING.color(sign.all[i]);
        }

        if (level > 0.2 && level < 0.9) {
            if (!sounds[index]) {
                Sound.bips[colorId].play();
                sounds[index] = true;
            }
            for (int i = 0; i < sign.hand.length; i++) {
                coloring.color(sign.hand[i]);
            }
        }
    }
}
