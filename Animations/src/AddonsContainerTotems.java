import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

class Segment {

    public Totem t;
    public int indexes[];
    public HSBColor arrayForEffect[];
    public AddonEffect effects[];

}

public class AddonsContainerTotems extends AddonsContainer {

    protected void addSegment(Totem t, int indexes[], List<Callable<AddonEffect>> effects) {

        Segment s = new Segment();

        s.t = t;
        s.indexes = indexes;
        s.arrayForEffect = new HSBColor[indexes.length];

        s.effects = new AddonEffect[effects.size()];
        int i=0;
        for(Callable<AddonEffect> e: effects) {
            try {
                s.effects[i] = e.call();
            }
            catch (java.lang.Exception exception) {

            }
            i++;
        }

        this.segments.add(s);
    }

    protected void addSegment(Totem t, int indexes[], AddonEffect effects[]) {

        Segment s = new Segment();

        s.t = t;
        s.indexes = indexes;
        s.arrayForEffect = new HSBColor[indexes.length];

        s.effects = new AddonEffect[effects.length];
        for(int i=0; i<effects.length; i++) {
            try {
                s.effects[i] = effects[i];
            }
            catch (java.lang.Exception e) {

            }
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
                e.apply(s.arrayForEffect, timePercent);
            }
        }
    }

    private ArrayList<Segment> segments = new ArrayList<>();
}

