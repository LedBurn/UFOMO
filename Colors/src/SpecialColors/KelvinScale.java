import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class KelvinScale {

    static ArrayList<GradientPoint> gradientPoints = new ArrayList<>();

    public KelvinScale() {
        if (gradientPoints.isEmpty()) {
            this.readGradientFile();
        }
    }

    private void readGradientFile() {
        try (Stream<String> stream = Files.lines(Paths.get("Colors/src/SpecialColors/KelvinScaleGradient.txt"))) {
            stream.forEach(line -> {
                String[] split = line.split(" ");
                String offsetStr = split[1].substring(8, split[1].length()-1);
                double offset = new Double(offsetStr);
                String colorStr = split[2].substring(18, split[2].length()-3);
                HSBColor color = HSBColor.hsbColorFromHex(colorStr);
                gradientPoints.add(new GradientPoint(offset, color));
            });
        } catch (IOException exception){
            System.out.println(exception.toString());
        }
    }

    public HSBColor getColorForLocation(double location) {
        // edge cases
        if (gradientPoints.get(0).location == location) {
            return gradientPoints.get(0).color;
        }
        if (gradientPoints.get(gradientPoints.size()-1).location == location) {
            return gradientPoints.get(gradientPoints.size()-1).color;
        }

        // find the closest points to the desired location
        GradientPoint lowPoint = gradientPoints.get(0);
        GradientPoint highPoint = gradientPoints.get(gradientPoints.size()-1);
        for (int i = 1; i < gradientPoints.size(); i++) {
            if (gradientPoints.get(i).location >= location) {
                highPoint = gradientPoints.get(i);
                lowPoint = gradientPoints.get(i-1);
                break;
            }
        }

        // calculate the color based on linear gradient between the two point
        double ratio = (location - lowPoint.location) / (highPoint.location - lowPoint.location);
        return HSBColor.averageColors(lowPoint.color, highPoint.color, ratio);
    }

    private class GradientPoint {
        private final double location;
        private final HSBColor color;

        public GradientPoint(double location, HSBColor color) {
            this.location = location;
            this.color = color;
        }
    }
}
