import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Simulator {
    private JFrame frame;
    private Graphics graphics;

    public Simulator(ISimulatedLEDObject simulatedLEDObject) {
        System.out.println("starting screen "+ Thread.currentThread().getName());
        frame = new JFrame("LED");
        frame.setSize(simulatedLEDObject.getWidth(), simulatedLEDObject.getHeight());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        graphics = frame.getGraphics();
        System.out.println("screen started");
    }

    public void draw(ILEDObject ledObject, ISimulatedLEDObject simulatedLEDObject, int startX, int startY) {
        BufferedImage bi = new BufferedImage(simulatedLEDObject.getWidth(), simulatedLEDObject.getHeight(), BufferedImage.TYPE_INT_RGB);
        simulatedLEDObject.draw(ledObject, bi);
        graphics.drawImage(bi, startX, startY, simulatedLEDObject.getWidth(), simulatedLEDObject.getHeight(), null);
    }
}
