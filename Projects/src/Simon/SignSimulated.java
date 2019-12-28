import java.awt.image.BufferedImage;

public class SignSimulated implements ISimulatedLEDObject<SignLEDObject> {

    @Override
    public int getHeight() {
        return 500;
    }

    @Override
    public int getWidth() {
        return 500;
    }


    @Override
    public void draw(SignLEDObject signLEDObject, BufferedImage bi) {
        for (int i = 0; i < signLEDObject.finger1.numOfPixels(); i++) {
            bi.setRGB(20 + i, 50, signLEDObject.finger1.getColorRGBInt(i));
            bi.setRGB(21 + i, 50, signLEDObject.finger1.getColorRGBInt(i));
            bi.setRGB(20 + i, 51, signLEDObject.finger1.getColorRGBInt(i));
            bi.setRGB(21 + i, 51, signLEDObject.finger1.getColorRGBInt(i));
        }
        for (int i = 0; i < signLEDObject.finger2.numOfPixels(); i++) {
            bi.setRGB(120 + i, 50, signLEDObject.finger2.getColorRGBInt(i));
            bi.setRGB(121 + i, 50, signLEDObject.finger2.getColorRGBInt(i));
            bi.setRGB(120 + i, 51, signLEDObject.finger2.getColorRGBInt(i));
            bi.setRGB(121 + i, 51, signLEDObject.finger2.getColorRGBInt(i));
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
        for (int i = 0; i < signLEDObject.finger5.numOfPixels(); i++) {
            bi.setRGB(420 + i, 50, signLEDObject.finger5.getColorRGBInt(i));
            bi.setRGB(421 + i, 50, signLEDObject.finger5.getColorRGBInt(i));
            bi.setRGB(420 + i, 51, signLEDObject.finger5.getColorRGBInt(i));
            bi.setRGB(421 + i, 51, signLEDObject.finger5.getColorRGBInt(i));
        }


        int x = 30;
        int y = 100;
        for (int i = 0; i < signLEDObject.palm.length; i++) {
            for (int j = 0; j < signLEDObject.palm[i].numOfPixels(); j++) {
                bi.setRGB(x + j, y, signLEDObject.palm[i].getColorRGBInt(j));
                bi.setRGB(x + j + 1, y, signLEDObject.palm[i].getColorRGBInt(j));
                bi.setRGB(x + j, y + 1, signLEDObject.palm[i].getColorRGBInt(j));
                bi.setRGB(x + i + 1, y + 1, signLEDObject.palm[i].getColorRGBInt(j));
            }
            x += 70;
        }


        x = 100;
        y = 250;
        for (int i = 0; i < signLEDObject.camp.length; i++) {
            for (int j = 0; j < signLEDObject.camp[i].numOfPixels(); j++) {
                bi.setRGB(x + j, y, signLEDObject.camp[i].getColorRGBInt(j));
                bi.setRGB(x + j + 1, y, signLEDObject.camp[i].getColorRGBInt(j));
                bi.setRGB(x + j, y + 1, signLEDObject.camp[i].getColorRGBInt(j));
                bi.setRGB(x + i + 1, y + 1, signLEDObject.camp[i].getColorRGBInt(j));
            }
            x += 100;
        }

        x = 20;
        y = 350;
        for (int i = 0; i < signLEDObject.ledburn.length; i++) {
            for (int j = 0; j < signLEDObject.ledburn[i].numOfPixels(); j++) {
                bi.setRGB(x + j, y, signLEDObject.ledburn[i].getColorRGBInt(j));
                bi.setRGB(x + j + 1, y, signLEDObject.ledburn[i].getColorRGBInt(j));
                bi.setRGB(x + j, y + 1, signLEDObject.ledburn[i].getColorRGBInt(j));
                bi.setRGB(x + i + 1, y + 1, signLEDObject.ledburn[i].getColorRGBInt(j));
            }
            x += 70;
        }
    }
}
