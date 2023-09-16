//import java.util.ArrayList;
//
///**
// *
// */
//public class CompoundPixelColor {
//
//    public enum COLOR_MIXING_TYPE {
//        ADDITIVE,
//        SUBTRACTIVE
//    }
//
//    private final COLOR_MIXING_TYPE colorMixingType;
//
//    ArrayList<Fragment> fragments = new ArrayList<>();
//
//    public CompoundPixelColor(COLOR_MIXING_TYPE type) {
//        this.colorMixingType = type;
//    }
//
//    public void addFragment(HSBColor color, double amount) {
//        this.fragments.add(new Fragment(color, amount));
//    }
//
//    public HSBColor compoundColor() {
//
//    }
//
//    private static class Fragment {
//        private HSBColor color;
//        private double amount;
//
//        public Fragment(HSBColor color, double amount) {
//            this.color = color;
//            this.amount = amount;
//        }
//    }
//}
