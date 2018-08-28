
// A vary simple object that contains an array of colors
// use it to build complex LED Objects
public interface IPixelsArray {

    int numOfPixels();

    // get color of a specific pixel
    HSBColor getColor(int pixelNum);

    // get color of a specific pixel as RGB integer (best for sending on network)
    RGBColor getColorRGB(int pixelNum, double brightnessLevel);

    // get color of a specific pixel as RGB integer (best for simulator)
    int getColorRGBInt(int pixelNum);


    // set color of a specific pixel
    void setColor(int pixelNum, HSBColor color);


    // clears the array - set all to black
    void clear();


    // copy pixels array from another led object
    void copy(IPixelsArray other);

    // merge 2 other pixels array and copy it the this object
    void mergeAndCopy(IPixelsArray other1, IPixelsArray other2, double fadePercent);
}
