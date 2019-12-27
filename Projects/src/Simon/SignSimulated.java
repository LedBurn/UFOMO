import java.awt.image.BufferedImage;

public class SignSimulated implements ISimulatedLEDObject<SignLEDObject> {

    @Override
    public int getHeight() {
        return 400;
    }

    @Override
    public int getWidth() {
        return 400;
    }


    @Override
    public void draw(SignLEDObject signLEDObject, BufferedImage bi) {
        for (int i = 0; i < signLEDObject.finger1.numOfPixels(); i++) {
            bi.setRGB(20 + i, 20, signLEDObject.finger1.getColorRGBInt(i));
            bi.setRGB(21 + i, 20, signLEDObject.finger1.getColorRGBInt(i));
            bi.setRGB(20 + i, 21, signLEDObject.finger1.getColorRGBInt(i));
            bi.setRGB(21 + i, 21, signLEDObject.finger1.getColorRGBInt(i));
        }
        for (int i = 0; i < signLEDObject.finger2.numOfPixels(); i++) {
            bi.setRGB(120 + i, 20, signLEDObject.finger2.getColorRGBInt(i));
            bi.setRGB(121 + i, 20, signLEDObject.finger2.getColorRGBInt(i));
            bi.setRGB(120 + i, 21, signLEDObject.finger2.getColorRGBInt(i));
            bi.setRGB(121 + i, 21, signLEDObject.finger2.getColorRGBInt(i));
        }
        for (int i = 0; i < signLEDObject.finger3.numOfPixels(); i++) {
            bi.setRGB(220 + i, 50, signLEDObject.finger3.getColorRGBInt(i));
            bi.setRGB(221 + i, 50, signLEDObject.finger3.getColorRGBInt(i));
            bi.setRGB(220 + i, 51, signLEDObject.finger3.getColorRGBInt(i));
            bi.setRGB(221 + i, 51, signLEDObject.finger3.getColorRGBInt(i));
        }
        for (int i = 0; i < signLEDObject.finger4.numOfPixels(); i++) {
            bi.setRGB(320 + i, 50, signLEDObject.finger4.getColorRGBInt(i));
            bi.setRGB(321 + i, 50, signLEDObject.finger4.getColorRGBInt(i));
            bi.setRGB(320 + i, 51, signLEDObject.finger4.getColorRGBInt(i));
            bi.setRGB(321 + i, 51, signLEDObject.finger4.getColorRGBInt(i));
        }
    }
}
