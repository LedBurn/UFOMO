import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class TestEffect {

    private static final int NUM_OF_PIXELS = 100;
    private static final double CYCLE_TIME = 2000; //millisec
    private static double currentTime = 0; //millisec

    private static DiscreteEffect getEffect() {
        // ----------------------------------------- //
        // Change this section to test other effects //
        // ----------------------------------------- //
        ContinuousWhiteEffect effect = new ContinuousWhiteEffect();
        ContinuousRainbowEffect effect1 = new ContinuousRainbowEffect(effect);
        ContinuousCyclicMoveEffect effect2 = new ContinuousCyclicMoveEffect(effect1);
        ContinuousToDiscrete discrete = new ContinuousToDiscrete(NUM_OF_PIXELS, effect2);
        return discrete;
        // ----------------------------------------- //
    }

    public static void main(String[] args) {

        final JFrame frame = new JFrame("Test Effects");
        frame.setSize(600, 100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final BufferedImage bi = new BufferedImage(600, 100, BufferedImage.TYPE_INT_RGB);
        frame.setVisible(true);

        // timer
        final java.util.Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    int color = getEffect().getColor(currentTime/CYCLE_TIME, i).toRGBInt();
                    int[] xs = range(50 + i*5, 50 + i*5 + 5);
                    int[] ys = range(55, 60);
                    for (int x : xs) {
                        for (int y : ys) {
                            bi.setRGB(x, y, color);
                        }
                    }
                }

                frame.getGraphics().drawImage(bi, 0, 0, 600, 100, null);
                currentTime += 20;
                if (currentTime >= CYCLE_TIME) {
                    currentTime -= CYCLE_TIME;
                }
            }
        }, 0, 20);
    }


    private static int[] range(int firstIndex, int lastIndex) {
        int allIndexes[] = new int[lastIndex - firstIndex + 1];
        for(int i=firstIndex; i <= lastIndex; i++) {
            allIndexes[i-firstIndex] = i;
        }
        return allIndexes;
    }
}
