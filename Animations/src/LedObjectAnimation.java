import java.util.ArrayList;

// This is the base class of all the animations of T (can be any led object)
// each subclass should:
// 1. pass the led object instance in the constructor
// 2. implement configAnimation & newBeat and add pixelsArrayAnimations to the animations array list.
public abstract class LedObjectAnimation<T extends ILEDObject> {

    // holds the led object the animation will run on.
    public T ledObject;

    // in the constructor you must pass the led object
    public LedObjectAnimation(T t) {
        ledObject = t;
        configAnimations();
    }

    protected ArrayList<PixelsArrayAnimation> animations = new ArrayList<>();

    protected abstract void configAnimations();

    public void apply(double timePercent) {
        for (PixelsArrayAnimation animation : animations){
            animation.apply(timePercent);
        }
    }
}
