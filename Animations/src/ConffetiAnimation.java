//import java.util.concurrent.ThreadLocalRandom;
//
//public class ConffetiAnimation extends Animation {
//
//    private long[] shineTimes;
//
//    public ConffetiAnimation(LEDObject ledObject) {
//        this(ledObject, new Addon[]{});
//    }
//    public ConffetiAnimation(LEDObject ledObject, Addon[] addons) {
//        super(ledObject, null, addons);
//
//        shineTimes = new long[ledObject.numOfPixels()];
//    }
//
//
//    @Override
//    public void apply(double level, boolean newBeat) {
//        boolean isNewShine = false;
//        int newShinesNum = ledObject.numOfPixels() / ;
//        for (int i = 0; i < newShinesNum; i++) {
//            int shineIndex = (int) Math.floor(Math.random() * ledObject.numOfPixels());
//
//            shineTimes[shineIndex] = System.currentTimeMillis();
//        }
//
//        long currentTime = System.currentTimeMillis();
//        for (int i = 0; i < ledObject.numOfPixels(); i++) {
//            long shineTime = currentTime - shineTimes[i];
//            double brigthness = 0;
//            if (shineTime < 1000) {
//                brigthness = Math.exp((1000.0 - shineTime) / 1000.0);
//            }
//
//            ledObject.setColor(i, new HSBColor(0, 0, brigthness));
//        }
//    }
//}
//
