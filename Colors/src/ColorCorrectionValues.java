import de.ralleytn.simple.json.JSONObject;

import java.util.Map;

public class ColorCorrectionValues {

    public double redMax = 1.0;
    public double greenMax = 1.0;
    public double blueMax = 1.0;

    public void loadCorrection(JSONObject colorCorrection) {
        if (colorCorrection == null) return;

        this.redMax = colorCorrection.getDouble("red");
        this.greenMax = colorCorrection.getDouble("green");
        this.blueMax = colorCorrection.getDouble("blue");
    }
}
