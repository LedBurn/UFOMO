public class AddonsContainerAllTotemOneSide extends AddonsContainerTotems {

    public AddonsContainerAllTotemOneSide(Totem totems[], AddonEffect effects[], boolean isLeft) {

        for(Totem t : totems) {
            if(isLeft) {
                this.addSegment(t, t.leftIndexes, effects);
            }
            else {
                this.addSegment(t, t.rightIndexes, effects);
            }
        }
    }

}

