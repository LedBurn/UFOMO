import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Simulator {
    private JFrame frame;
    private Graphics graphics;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    public Simulator() {
        System.out.println("starting screen "+ Thread.currentThread().getName());
        frame = new JFrame("LED");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        graphics = frame.getGraphics();
        System.out.println("screen started");
    }

    public void draw(ISimulatedLEDObject simulatedLEDObject, int startX, int startY) {
        BufferedImage bi = new BufferedImage(simulatedLEDObject.getWidth(), simulatedLEDObject.getHeight(), BufferedImage.TYPE_INT_RGB);
        simulatedLEDObject.draw(bi);
        graphics.drawImage(bi, startX, startY, simulatedLEDObject.getWidth(), simulatedLEDObject.getHeight(), null);
    }
}
