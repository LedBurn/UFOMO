

public class FastFadeInOutAddon extends Addon {

    private double currentLevel = 0.2;
    private boolean isIn = true;

    @Override
    public void change(IPixelsArray ledObject, double level) {
        if (isIn){
            currentLevel = Math.min(currentLevel + 0.02, 1);
        } else {
            currentLevel = Math.max(currentLevel - 0.01, 0.2);
        }
        if (currentLevel == 1){
            isIn = false;
        }


        for (int i = 0; i < ledObject.numOfPixels(); i++) {
            ledObject.getColor(i).brightness = currentLevel;
        }
    }
}
