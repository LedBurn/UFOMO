import java.util.ArrayList;

public class CompoundAnimation extends Animation {

    private ArrayList<Animation> animations = new ArrayList<>();

    public CompoundAnimation() {
        super(null);
    }

    public void addAnimation(Animation animation) {
        this.animations.add(animation);
    }
    @Override
    public void animate(long cycleNum, double cycleTimePercent) {
        for (Animation animation : this.animations) {
            animation.animate(cycleNum, cycleTimePercent);
        }
    }

    public void clear() {
        this.animations = new ArrayList<>();
    }
}
