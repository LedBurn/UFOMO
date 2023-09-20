public class NetworkUtils {

    // returns rgb colors from the object. toIndex can be smaller than fromIndex so it will be a reversed array.
    public static RGBColor[] getRGB(IPixelsArray object, int fromIndex, int toIndex, double brightnessLevel) {
        if (fromIndex < toIndex) {
            RGBColor[] colors = new RGBColor[toIndex-fromIndex+1];
            for (int i = 0; i < colors.length; i++) {
                colors[i] = object.getColorRGB(fromIndex + i, brightnessLevel);
            }
            return colors;
        } else {
            RGBColor[] colors = new RGBColor[fromIndex-toIndex+1];
            for (int i = 0; i < colors.length; i++) {
                colors[i] = object.getColorRGB(fromIndex - i, brightnessLevel);
            }
            return colors;
        }
    }

    public static RGBColor[] getRGB(IPixelsArray object) {
        return getRGB(object, false);
    }

    public static RGBColor[] getRGB(IPixelsArray object, boolean reversed) {
        if (reversed) {
            return getRGB(object, object.numOfPixels()-1, 0, 1.0);
        } else {
            return getRGB(object, 0, object.numOfPixels()-1, 1.0);
        }
    }

    // returns rgb colors for the object. will start from the fromIndex for the entire object.
    public static RGBColor[] getRGBCyclic(IPixelsArray object, int fromIndex, boolean reversed, double brightnessLevel) {
        RGBColor[] colors = new RGBColor[object.numOfPixels()];

        int next = fromIndex;
        for (int i = 0; i < object.numOfPixels(); i++) {
            colors[i] = object.getColorRGB(next, brightnessLevel);

            next += reversed ? -1 : 1;
            if (next == -1) next = object.numOfPixels() - 1;
            if (next == object.numOfPixels()) next = 0;
        }
        return colors;
    }

    public static RGBColor[] connect(RGBColor[] colors1, RGBColor[] colors2) {
        RGBColor[] colors = new RGBColor[colors1.length + colors2.length];
        for (int i = 0; i < colors.length; i++) {
            if (i<colors1.length) {
                colors[i] = colors1[i];
            } else {
                colors[i] = colors2[i - colors1.length];
            }
        }
        return colors;
    }

    public static RGBColor[] joinArray(RGBColor[]... arrays) {
        int length = 0;
        for (RGBColor[] array : arrays) {
            length += array.length;
        }

        final RGBColor[] result = new RGBColor[length];

        int offset = 0;
        for (RGBColor[] array : arrays) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }

        return result;
    }

}
