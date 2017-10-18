public class AddonsContainerAllTotems extends AddonsContainerTotems {

    public AddonsContainerAllTotems(Totem totems[], AddonEffect effects[]) {

        for(Totem t : totems) {
            this.addSegment(t, t.leftIndexes, effects);
            this.addSegment(t, t.rightIndexes, effects);
        }
    }

}
