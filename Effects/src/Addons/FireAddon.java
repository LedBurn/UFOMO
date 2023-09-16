import java.awt.*;

public class FireAddon extends Addon {

    int maxRed = 255;
    int minRed = 120;

    int maxGreen = 70;
    int minGreen = 5;

    int[] redColorTop = new int[] {5, 0, 0};
    int[] redColorBottom = new int[] {200, 0, 0};


    int[] maxColor = null;
    int[] minColor = null;
    int fireLevel = -1;

    @Override
    public void change(IPixelsArray ledObject, double level) {
        if (fireLevel == -1) {
            random(ledObject);
        }

        rerandom(ledObject);
        gradient(ledObject, fireLevel, ledObject.numOfPixels(), minColor, redColorTop);
        gradient(ledObject, 5, fireLevel, maxColor, minColor);
        gradient(ledObject, 0, 5, redColorBottom, maxColor);
    }

    private void random(IPixelsArray ledObject) {
        int maxR = maxRed - (int)(Math.random()*100);
        int minR = minRed + (int)(Math.random()*100);

        int maxG = maxGreen - (int)(Math.random()*50);
        int minG = minGreen + (int)(Math.random()*5);

        if (minG > minR + 20) {
            minR = minG + 20;
        }

        maxColor = new int[] {maxR, maxG, 0};
        minColor = new int[] {minR, minG, 0};

        fireLevel = ledObject.numOfPixels() - (int)(Math.random()*0.8*ledObject.numOfPixels());
        if (fireLevel < 10) {
            fireLevel = 10;
        }
    }

    private void rerandom(IPixelsArray ledObject) {

        int maxR = maxRed - (int)(Math.random()*100);
        int minR = minRed + (int)(Math.random()*100);

        int maxG = maxGreen - (int)(Math.random()*50);
        int minG = minGreen + (int)(Math.random()*5);

        if (minG > minR + 20) {
            minR = minG + 20;
        }

        maxColor = new int[] {maxColor[0] + (int)(Math.round(maxR-maxColor[0]) * 0.3), maxColor[1] + (int)(Math.round(maxG-maxColor[1]) * 0.3), 0};
        minColor = new int[] {minColor[0] + (int)(Math.round(minR-minColor[0]) * 0.3), minColor[1] + (int)(Math.round(minG-minColor[1]) * 0.3), 0};

        int newFireLevel = ledObject.numOfPixels() - (int)(Math.random()*0.8*ledObject.numOfPixels());
        if (newFireLevel < 10) {
            newFireLevel = 10;
        }

        fireLevel = fireLevel + (int)(Math.round(newFireLevel-fireLevel) * 0.3);
    }

    private void gradient(IPixelsArray ledObject, int fromIndex, int toIndex, int[] bottomColor, int[] topColor) {
        int size = toIndex-fromIndex;
        for (int i=fromIndex; i<toIndex; i++) {
            int phase = i-fromIndex;

            int r = bottomColor[0] + (int)Math.floor(((topColor[0]-bottomColor[0])*phase/(double)size));
            int g = bottomColor[1] + (int)Math.floor(((topColor[1]-bottomColor[1])*phase/(double)size));
            int b = bottomColor[2] + (int)Math.floor(((topColor[2]-bottomColor[2])*phase/(double)size));

            float[] hsbvals = new float[3];
            Color.RGBtoHSB(r,g,b,hsbvals);

            ledObject.setColor(i, new HSBColor(hsbvals[0], hsbvals[1], hsbvals[2]));
        }
    }
//
//    def apply(self, time_percent, parent_array):
//
//            if (time_percent < self.previous_time):
//    self.num_of_loops = random.randrange(4, 60)
//    self.previous_time = time_percent
//
//            loop = math.floor(time_percent * self.num_of_loops)
//        if (loop != self.loop_num):
//    self.loop_num =  self.loop_num + 1 if (self.loop_num < self.num_of_loops) else 0
//            self.create_effects()
//
//            for effect in self.effects:
//            effect.apply(time_percent, parent_array)



}
