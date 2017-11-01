import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.UnaryOperator;

class Segment {

    public Totem t;
    public int indexes[];
    public HSBColor arrayForEffect[];
    public AddonEffect effects[];
    public UnaryOperator<Double> calcTimePercent;

}

public class AddonsContainerTotems extends AddonsContainer {

    protected void addSegment(Totem t, int indexes[], List<Callable<AddonEffect>> effects, UnaryOperator<Double> calcTimePercent) {

        Segment s = new Segment();

        s.t = t;
        s.indexes = indexes;
        s.arrayForEffect = new HSBColor[indexes.length];
        s.calcTimePercent = calcTimePercent;

        s.effects = new AddonEffect[effects.size()];
        int i=0;
        for(Callable<AddonEffect> e: effects) {
            try {
                s.effects[i] = e.call();
                s.effects[i].setNumberOfPixels(indexes.length);
            }
            catch (java.lang.Exception exception) {

            }
            i++;
        }

        this.segments.add(s);
    }

    @Override
    public void apply(double timePercent) {

        for(Segment s: this.segments) {

            HSBColor allPixels[] = s.t.GetAllPixels();
            for(int indexArrI = 0; indexArrI < s.indexes.length; indexArrI++) {
                int currentIndex = s.indexes[indexArrI];
                s.arrayForEffect[indexArrI] = allPixels[currentIndex];
            }

            for(AddonEffect e : s.effects) {
                double timePercentToUse = timePercent;
                if(s.calcTimePercent != null) {
                    try {
                        Double calculatedTimePercent = s.calcTimePercent.apply(timePercent);
                        if(calculatedTimePercent != null) {
                            timePercentToUse = Math.min(Math.max(calculatedTimePercent, 0.0), 1.0);
                        }
                    }
                    catch (java.lang.Exception exception) {

                    }
                }
                e.apply(s.arrayForEffect, timePercentToUse);
            }
        }
    }

    private ArrayList<Segment> segments = new ArrayList<>();
}

