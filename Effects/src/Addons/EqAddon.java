//// show only the first leds according to the eq
//public class EqAddon extends Addon {
//
//    private int keepMax = 0;
//    private int eqIndex = 7;
//    private boolean reversed = false;
//
//    public EqAddon() {
//    }
//
//    public EqAddon(int eqIndex) {
//        this.eqIndex = eqIndex;
//    }
//
//    public EqAddon(boolean reversed) {
//        this.reversed = reversed;
//    }
//
//    public EqAddon(int eqIndex, boolean reversed) {
//        this.eqIndex = eqIndex;
//        this.reversed = reversed;
//    }
//
//    @Override
//    public void change(IPixelsArray ledObject, double level, boolean newBeat, boolean isOn, int[] eq) {
//        int index = (int)Math.floor(ledObject.numOfPixels() * eq[eqIndex] / 128.0);
//        if (index >= keepMax) {
//            keepMax = index;
//        } else {
//            keepMax -= 1;
//        }
//        index = Math.max(index, keepMax);
//        for (int i = 0; i < ledObject.numOfPixels(); i++) {
//            if ((reversed && ledObject.numOfPixels()-i >= index) ||
//                    !reversed && i>=index) {
//                HSBColor color = ledObject.getColor(i);
//                color.brightness *= 0.15;
//            }
//        }
//    }
//}
