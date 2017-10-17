import com.sun.xml.internal.bind.v2.runtime.reflect.ListTransducedAccessorImpl;

import java.util.ArrayList;
import java.util.concurrent.Callable;

class Segment {

    public Totem t;
    public int indexes[];
    public HSBColor arrayForEffect[];
    public AddonEffect effects[];

}

public class AddonsContainerAllTotems extends AddonsContainer {

    public AddonsContainerAllTotems(Totem totems[], AddonEffect effects[]) {

        for(Totem t : totems) {
            this.addSegment(t, t.leftIndexes, effects);
            this.addSegment(t, t.rightIndexes, effects);
        }
    }

    private void addSegment(Totem t, int indexes[], AddonEffect effects[]) {

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

    private Totem totems[];
    private ArrayList<Callable<AddonEffect>> effects;
}
