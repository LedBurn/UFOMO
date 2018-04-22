import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Simulator {
    private JFrame frame;
    private Graphics graphics;

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;

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

    public void draw(ISimulatedLEDObject ledObject, int startX, int startY) {
        draw(new ISimulatedLEDObject[]{ledObject}, startX, startY);
    }

    public void draw(ISimulatedLEDObject[] ledObjects, int startX, int startY) {
        BufferedImage bi = new BufferedImage(ledObjects[0].getWidth(), ledObjects[0].getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int i=0; i<ledObjects.length; i++) {
            ledObjects[i].draw(bi);
        }
        graphics.drawImage(bi, startX, startY, ledObjects[0].getWidth(), ledObjects[0].getHeight(), null);
    }
}
