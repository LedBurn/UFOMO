import java.awt.image.BufferedImage;

public interface ISimulatedLEDObject<T extends ILEDObject> {

    int getWidth();
    int getHeight();

    /* Draw the current state inside the buffered image */
    void draw(T t, BufferedImage bi);
}