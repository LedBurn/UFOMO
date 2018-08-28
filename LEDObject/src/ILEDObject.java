
public interface ILEDObject<T> {

    // clears all the pixels - set all to black
    void clear();

    // copy all the pixels form another object
    void copy(T other);

    // merge 2 other objects (pixel by pixel merge) and copy it the this object
    void mergeAndCopy(T other1, T other2, double percent);
}
