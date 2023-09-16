public class HomeObject implements ILEDObject<HomeObject> {

    // see more info here:
    // https://lucid.app/lucidchart/d6bbc7e2-b672-4d99-9288-c79e4797f52e/edit?viewport_loc=2034%2C-1294%2C2582%2C1588%2C0_0&invitationId=inv_8250d507-511d-4838-8ed7-25823d0610e8

    public static final int[] CEILING_NUM_OF_LEDS = { 144, 144, 144, 144 };
    public static final int[] EXTERNAL_FRONT_NUM_OF_LEDS = { 75, 144, 210, 144, 75 };
    public static final int EXTERNAL_FRONT_MISSING_PIXELS = EXTERNAL_FRONT_NUM_OF_LEDS[2] - EXTERNAL_FRONT_NUM_OF_LEDS[0] - EXTERNAL_FRONT_NUM_OF_LEDS[4];
    public static final int[] INTERNAL_FRONT_NUM_OF_LEDS = { 62, 99, 62, 99 };



    public IPixelsArray[] ceiling = new IPixelsArray[CEILING_NUM_OF_LEDS.length];
    public IPixelsArray[] ceilingReversed = new IPixelsArray[CEILING_NUM_OF_LEDS.length];
    public  IPixelsArray[] frontExternal = new IPixelsArray[EXTERNAL_FRONT_NUM_OF_LEDS.length];
    public  IPixelsArray[] frontExternalReversed = new IPixelsArray[EXTERNAL_FRONT_NUM_OF_LEDS.length];
    public  IPixelsArray[] frontInternal = new IPixelsArray[INTERNAL_FRONT_NUM_OF_LEDS.length];
    public  IPixelsArray[] frontInternalReversed = new IPixelsArray[INTERNAL_FRONT_NUM_OF_LEDS.length];


    public  IPixelsArray[] all;

    public IPixelsArray ceilingUnite;
    public IPixelsArray frontInternalUnite;
    public IPixelsArray frontExternalUnite;


    public HomeObject() {
        this.clear();
    }

    @Override
    public void clear() {
        for (int i = 0; i < ceiling.length; i++) {
            ceiling[i] = new PixelsArrayImp(CEILING_NUM_OF_LEDS[i]);
        }
        for (int i = 0; i < ceiling.length; i++) {
            ceilingReversed[i] = new PixelsArrayReversed(ceiling[i]);
        }

        for (int i = 0; i < frontExternal.length; i++) {
            frontExternal[i] = new PixelsArrayImp(EXTERNAL_FRONT_NUM_OF_LEDS[i]);
        }
        for (int i = 0; i < frontExternal.length; i++) {
            frontExternalReversed[i] = new PixelsArrayReversed(frontExternal[i]);
        }

        for (int i = 0; i < frontInternal.length; i++) {
            frontInternal[i] = new PixelsArrayImp(INTERNAL_FRONT_NUM_OF_LEDS[i]);
        }

        for (int i = 0; i < frontInternal.length; i++) {
            frontInternalReversed[i] = new PixelsArrayReversed(frontInternal[i]);
        }

        IPixelsArray[] frontFixBottom = new IPixelsArray[3];
        frontFixBottom[0] = frontExternal[4];
        frontFixBottom[1] = new PixelsArrayImp(EXTERNAL_FRONT_MISSING_PIXELS);
        frontFixBottom[2] = frontExternal[0];
        IPixelsArray fixedFrontBottom = new PixlesArrayUnionImp(frontFixBottom);

        all = new IPixelsArray[]{ceiling[0], ceiling[1], ceiling[2], ceiling[3],
                frontExternal[1], frontExternal[2], frontExternal[3], fixedFrontBottom,
                frontInternal[0], frontInternal[1], frontInternal[2], frontInternal[3]};



        ceilingUnite = new PixlesArrayUnionImp(new IPixelsArray[]{ceiling[2], ceiling[3], ceiling[0], ceiling[1]});
        frontInternalUnite = new PixlesArrayUnionImp(frontInternal);
        IPixelsArray[] frontExternalFull = new IPixelsArray[frontExternal.length + 1];
        frontExternalFull[0] = frontExternal[1];
        frontExternalFull[1] = frontExternal[2];
        frontExternalFull[2] = frontExternal[3];
        frontExternalFull[3] = frontExternal[4];
        frontExternalFull[4] = new PixelsArrayImp(EXTERNAL_FRONT_MISSING_PIXELS);
        frontExternalFull[5] = frontExternal[0];
        frontExternalUnite = new PixlesArrayUnionImp(frontExternalFull);

    }

    @Override
    public void copy(HomeObject other) {
        for (int i = 0; i < all.length; i++) {
            all[i].copy(other.all[i]);
        }
    }

    @Override
    public void mergeAndCopy(HomeObject other1, HomeObject other2, double percent) {
        for (int i = 0; i < all.length; i++) {
            all[i].mergeAndCopy(other1.all[i], other2.all[i], percent);
        }
    }
}

