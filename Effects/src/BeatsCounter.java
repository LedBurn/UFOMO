public class BeatsCounter {

    /*
    return true if a new beat has started.
    this class is not amazing:
    - you must remember to call it on every loop
    - if the beat is very short, or the frame rate is very it might "miss" the change, which is not
     */
    public boolean newTimePercent(double timePercent) {
        boolean hasNewBeat = false;
        if(this.prevTimePercent > timePercent) {
            hasNewBeat = true;
            beatsChanges++;
        }
        this.prevTimePercent = timePercent;
        return hasNewBeat;
    }

    public int getBeatsChanges() {
        return this.beatsChanges;
    }

    private int beatsChanges = 0;
    private double prevTimePercent = 0.0;
}
