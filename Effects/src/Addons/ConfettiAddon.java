public class ConfettiAddon extends Addon {

    private long[] shineTimes;

    public ConfettiAddon(int numOfPixels) {
        shineTimes = new long[numOfPixels];
    }

    @Override
    public void change(IPixelsArray ledObject, double level, boolean newBeat, boolean isOn, int[] eq) {
        double chance = ledObject.numOfPixels() / 30.0;
        while (chance >= 1) {
            int shineIndex = (int) Math.floor(Math.random() * ledObject.numOfPixels());
            shineTimes[shineIndex] = System.currentTimeMillis();
            chance -= 1;
        }
        if (Math.random() < chance) {
            int shineIndex = (int) Math.floor(Math.random() * ledObject.numOfPixels());
            shineTimes[shineIndex] = System.currentTimeMillis();
        }

        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            long shineTime = currentTime - shineTimes[i];
            double brightness = 0.1;
            if (shineTime < 500) {
                brightness = shineTime / 500.0;
            }
            ledObject.getColor(i).brightness =  brightness;
            ledObject.getColor(i).hue +=  1 - brightness / 3;
        }
    }
}

