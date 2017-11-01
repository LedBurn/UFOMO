public class AddonSetConstColor extends AddonEffect {

    public AddonSetConstColor(HSBColor c) {
        this.c = c;
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {
        for(HSBColor currColor: array) {
            currColor.copyFromOther(this.c);
        }
    }

    private HSBColor c;

}
