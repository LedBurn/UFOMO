import java.util.ArrayList;

// This is the base class of all the UFOMO animations.
// each subclass should add animations to the animations array list.
public abstract class UFOMOAnimation {

    public UFOMOObject ufomoObject = new UFOMOObject();

    protected ArrayList<Animation> animations = new ArrayList<>();

    public UFOMOAnimation() {
        configAnimations();
        newBeat();
    }

    protected abstract void configAnimations();
    protected abstract void newBeat();

    public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {

        for (Animation animation : animations){
            animation.apply(timePercent,newBeat, isOn, eq);
        }
    }
}
