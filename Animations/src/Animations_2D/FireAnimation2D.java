//import java.util.concurrent.ThreadLocalRandom;
//
//public class FireAnimation2D extends Animation_2D {
//
//    private int[][] heat;
//
//    public FireAnimation2D(PixelArray_2D ledObject) {
//        super(ledObject);
//        this.heat = new int[(int) Math.floor(ledObject.maxX)][(int) Math.floor(ledObject.maxY)];
//    }
//
//    private int[] colors = {
//            0x000000,
//            0x100000,
//            0x300000,
//            0x600000,
//            0x800000,
//            0xA00000,
//            0xC02000,
//            0xC04000,
//            0xC06000,
//            0xC08000,
//            0x807080
//    };
//    private int numOfColors = colors.length;
//
//    @Override
//    public void animate(long cycleNum, double cycleTimePercent) {
//
//        // First, move all existing heat points up the display and fade
//        for (int i = this.heat.length - 1; i > 0 ; i--) {
//            for (int j = 0; j < this.heat[i].length; j++) {
//                if (heat[i-1][j] > 0) {
//                    heat[i][j] = heat[i-1][j] - 1;
//                } else {
//                    heat[i][j] = 0;
//                }
//            }
//        }
//
//        // Heat the bottom row
//        for (int j = 0; j < heat[0].length; j++) {
//            int i = heat[0][j];
//            if ( i > 0 ) {
//                heat[0][j] = ThreadLocalRandom.current().nextInt(colors.length - 6, colors.length - 2);
//            }
//        }
//
//        // flare
//        for ( i=0; i<nflare; ++i ) {
//            int x = flare[i] & 0xff;
//            int y = (flare[i] >> 8) & 0xff;
//            int z = (flare[i] >> 16) & 0xff;
//            glow( x, y, z );
//            if ( z > 1 ) {
//                flare[i] = (flare[i] & 0xffff) | ((z-1)<<16);
//            } else {
//                // This flare is out
//                for ( int j=i+1; j<nflare; ++j ) {
//                    flare[j-1] = flare[j];
//                }
//                --nflare;
//            }
//        }
//        newflare();
//
//        // Set and draw
//        for ( i=0; i<rows; ++i ) {
//            for ( j=0; j<cols; ++j ) {
//                matrix[pos(j,i)] = colors[pix[i][j]];
//            }
//        }
//        FastLED.show();
//    }
//    }
//}
