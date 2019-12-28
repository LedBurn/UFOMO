public class SignLEDObject implements ILEDObject<SignLEDObject> {

    public static int SIZE_FINGER1 = 26;
    public static int SIZE_FINGER2 = 28;
    public static int SIZE_FINGER3 = 31;
    public static int SIZE_FINGER4 = 29;
    public static int SIZE_FINGER5 = 18;
    public static int SIZE_PALM6 = 28;
    public static int SIZE_PALM7 = 23;
    public static int SIZE_PALM8 = 32;
    public static int SIZE_PALM9 = 39;
    public static int SIZE_PALM10 = 29;
    public static int SIZE_PALM11 = 13;
    public static int SIZE_C = 28;
    public static int SIZE_A = 28;
    public static int SIZE_M = 40;
    public static int SIZE_P = 28;
    public static int SIZE_L = 26;
    public static int SIZE_E = 38;
    public static int SIZE_D = 27;
    public static int SIZE_B = 29;
    public static int SIZE_U = 40;
    public static int SIZE_R = 31;
    public static int SIZE_N = 37;


    public IPixelsArray finger1;
    public IPixelsArray finger2;
    public IPixelsArray finger3;
    public IPixelsArray finger4;
    public IPixelsArray finger5;

    public IPixelsArray palm6;
    public IPixelsArray palm7;
    public IPixelsArray palm8;
    public IPixelsArray palm9;
    public IPixelsArray palm10;
    public IPixelsArray palm11;

    public IPixelsArray camp_c;
    public IPixelsArray camp_a;
    public IPixelsArray camp_m;
    public IPixelsArray camp_p;

    public IPixelsArray led_l;
    public IPixelsArray led_e;
    public IPixelsArray led_d;

    public IPixelsArray burn_b;
    public IPixelsArray burn_u;
    public IPixelsArray burn_r;
    public IPixelsArray burn_n;


    public IPixelsArray[] gameFingers;
    public IPixelsArray[] fingers;
    public IPixelsArray[] palm;
    public IPixelsArray[] hand;
    public IPixelsArray[] camp;
    public IPixelsArray[] led;
    public IPixelsArray[] burn;
    public IPixelsArray[] ledburn;
    public IPixelsArray[] campledburn;
    public IPixelsArray[] all;

    public SignLEDObject() {
        clear();
    }

    @Override
    public void clear() {
        finger1 = new PixelsArrayImp(SIZE_FINGER1);
        finger2 = new PixelsArrayImp(SIZE_FINGER2);
        finger3 = new PixelsArrayImp(SIZE_FINGER3);
        finger4 = new PixelsArrayImp(SIZE_FINGER4);
        finger5 = new PixelsArrayImp(SIZE_FINGER5);

        palm6 = new PixelsArrayImp(SIZE_PALM6);
        palm7 = new PixelsArrayImp(SIZE_PALM7);
        palm8 = new PixelsArrayImp(SIZE_PALM8);
        palm9 = new PixelsArrayImp(SIZE_PALM9);
        palm10 = new PixelsArrayImp(SIZE_PALM10);
        palm11 = new PixelsArrayImp(SIZE_PALM11);

        camp_c = new PixelsArrayImp(SIZE_C);
        camp_a = new PixelsArrayImp(SIZE_A);
        camp_m = new PixelsArrayImp(SIZE_M);
        camp_p = new PixelsArrayImp(SIZE_P);

        led_l = new PixelsArrayImp(SIZE_L);
        led_e = new PixelsArrayImp(SIZE_E);
        led_d = new PixelsArrayImp(SIZE_D);

        burn_b = new PixelsArrayImp(SIZE_B);
        burn_u = new PixelsArrayImp(SIZE_U);
        burn_r = new PixelsArrayImp(SIZE_R);
        burn_n = new PixelsArrayImp(SIZE_N);

        gameFingers = new IPixelsArray[]{ finger1, finger2, finger3, finger4 };
        fingers = new IPixelsArray[]{ finger5, finger4, finger3, finger2, finger1 };
        palm = new IPixelsArray[]{ palm6, palm7, palm8, palm9, palm10, palm11 };
        hand = new IPixelsArray[]{ finger1, finger2, finger3, finger4, finger5, palm6, palm7, palm8, palm9, palm10, palm11 };
        camp = new IPixelsArray[]{ camp_c, camp_a, camp_m, camp_p };
        led = new IPixelsArray[]{ led_l, led_e, led_d };
        burn = new IPixelsArray[]{ burn_b, burn_u, burn_r, burn_n };
        ledburn = new IPixelsArray[]{ led_l, led_e, led_d, burn_b, burn_u, burn_r, burn_n };
        campledburn = new IPixelsArray[]{ camp_c, camp_a, camp_m, camp_p, led_l, led_e, led_d, burn_b, burn_u, burn_r, burn_n };
        all = new IPixelsArray[]{ finger1, finger2, finger3, finger4, finger5, palm6, palm7, palm8, palm9, palm10, palm11, camp_c, camp_a, camp_m, camp_p, led_l, led_e, led_d, burn_b, burn_u, burn_r, burn_n };
    }

    @Override
    public void copy(SignLEDObject other) {
        for (int i = 0; i < all.length; i++) {
            all[i].copy(other.all[i]);
        }
    }

    @Override
    public void mergeAndCopy(SignLEDObject other1, SignLEDObject other2, double percent) {
        for (int i = 0; i < all.length; i++) {
            all[i].mergeAndCopy(other1.all[i], other2.all[i], percent);
        }
    }

}
