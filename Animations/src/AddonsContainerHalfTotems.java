public class AddonsContainerHalfTotems extends AddonsContainerTotems {

    public AddonsContainerHalfTotems(Totem totems[], AddonEffect effects[], boolean isEven) {

        int i=0;
        if(!isEven) {
            i=1;
        }
        for(; i<totems.length; i+=2) {
            Totem t = totems[i];
            this.addSegment(t, t.leftIndexes, effects);
            this.addSegment(t, t.rightIndexes, effects);
        }
    }

}
