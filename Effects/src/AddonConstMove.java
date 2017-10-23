public class AddonConstMove extends AddonEffect {

    public AddonConstMove(double amount) {
        this.amount = amount;
    }

    @Override
    public void apply(HSBColor[] array, double timePercent) {
        int amountInPixels = (int)(array.length * this.amount);

        if(amountInPixels >= 0) {
            for(int i=array.length - 1; i >= amountInPixels; i--) {
                array[i].copyFromOther(array[i - amountInPixels]);
            }
            for(int i=amountInPixels - 1; i>=0 ; i--) {
                array[i].copyFromOther(HSBColor.BLACK);
            }
        }
        else {
            for(int i=0; i < (array.length - amountInPixels); i++) {
                array[i].copyFromOther(array[i + amountInPixels]);
            }
            for(int i = (array.length - amountInPixels); i<array.length ; i++) {
                array[i].copyFromOther(HSBColor.BLACK);
            }
        }
    }

    private double amount;

}
