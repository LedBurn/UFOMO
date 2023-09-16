//
//// Changes all the brightness
//public class EqConstAddon extends Addon {
//
//    private double keepMax = 1.0;
//    private int eqIndex = 7;
//
//    public EqConstAddon() {
//    }
//
//    public EqConstAddon(int eqIndex) {
//        this.eqIndex = eqIndex;
//    }
//
//    @Override
//    public void change(IPixelsArray ledObject, double level, boolean newBeat, boolean isOn, int[] eq) {
//        double brightness =  eq[eqIndex] / 128.0;
//        if (brightness >= keepMax) {
//            keepMax = brightness;
//        } else {
//            keepMax = Math.max(0.1, keepMax - 0.02);
//        }
//        brightness = Math.max(brightness, keepMax);
//        for (int i = 0; i < ledObject.numOfPixels(); i++) {
//            HSBColor color = ledObject.getColor(i);
//            color.brightness = brightness;
//        }
//    }
//}
//
//
