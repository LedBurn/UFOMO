
// This abstract class is the base of all the addons. it takes an array of pixels and change it.
public abstract class Addon {

    // changes the ledObject pixels array.
    // the level is 0-1 range. 0 = no change and 1 = full change.
    // all changes must be cyclic, which means level0=level1.
    public abstract void change(IPixelsArray ledObject, double level, boolean newBeat, boolean isOn, int[] eq);

    public void change(IPixelsArray ledobject, double level) {
        change(ledobject, level, false, false, new int[]{});
    }
}

