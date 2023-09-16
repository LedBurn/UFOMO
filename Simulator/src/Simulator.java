import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Simulator {
    private JFrame frame;
    private Graphics graphics;

    public static final int STATUS_BAR_HEIGHT = 16;

    public Simulator(ISimulatedLEDObject simulatedLEDObject) {
        System.out.println("starting screen "+ Thread.currentThread().getName());
        frame = new JFrame("LED");
        frame.setSize(simulatedLEDObject.getWidth(), simulatedLEDObject.getHeight() + STATUS_BAR_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        pane.setPreferredSize(new Dimension(simulatedLEDObject.getWidth(), simulatedLEDObject.getHeight()));
//        frame.setContentPane(pane);

        graphics = frame.getGraphics();
        System.out.println("screen started");
    }

    public void draw(ILEDObject ledObject, ISimulatedLEDObject simulatedLEDObject, int startX, int startY) {
        BufferedImage bi = new BufferedImage(simulatedLEDObject.getWidth(), simulatedLEDObject.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics biGraphics =  bi.createGraphics();
        biGraphics.setColor(new Color(36, 36, 36));
        biGraphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        biGraphics.dispose();
        simulatedLEDObject.draw(ledObject, bi);
        graphics.drawImage(bi, startX, STATUS_BAR_HEIGHT, simulatedLEDObject.getWidth(), simulatedLEDObject.getHeight(), null);
    }
}
