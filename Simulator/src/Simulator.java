import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Simulator {
    private JFrame frame;
    private Graphics graphics;

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    public Simulator() {
        System.out.println("starting screen "+ Thread.currentThread().getName());
        frame = new JFrame("LED");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        graphics = frame.getGraphics();
        System.out.print("screen started");
    }

    public void draw(ISimulatedLEDObject ledObject) {
        BufferedImage bi = new BufferedImage(ledObject.getWidth(),
                ledObject.getHeight(), BufferedImage.TYPE_INT_RGB);

        ledObject.draw(bi);
        graphics.drawImage(bi, 0, 0, ledObject.getWidth(), ledObject.getHeight(), null);
    }
}
