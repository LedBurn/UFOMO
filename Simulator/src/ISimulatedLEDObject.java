import java.awt.image.BufferedImage;

public interface ISimulatedLEDObject {

    public int getWidth();
    public int getHeight();

    /* Draw the current state inside the buffered image */
    public void draw(BufferedImage bi);
}