public class TestLeds {

    private int frameNum = 0;
    private int code = 1;

    public void startWithCode(int code){
        this.code = code;
        frameNum = 0;
    }

    public int getCode() {
        return code;
    }

    // return true if done
    public boolean apply(UFOMOObject ufomoObject) {
        System.currentTimeMillis();

        ufomoObject.clear();

        switch (code) {
            case 2: {
                test(ufomoObject,1);
                break;
            }
            case 3: {
                test(ufomoObject,3);
                break;
            }
            case 4: {
                test(ufomoObject,8);
                break;
            }
        }

        frameNum++;
        return false;
    }

    private void test(UFOMOObject ufomoObject, int speed) {
        HSBColor color = new HSBColor(Math.random(), 1.0, 1.0);
        if (frameNum / speed == ufomoObject.bigCircle.numOfPixels()) {
            frameNum = 0;
        }
        ufomoObject.bigCircle.setColor(frameNum / speed, color);

        if (frameNum / speed < ufomoObject.mediumCircle.numOfPixels()) {
            ufomoObject.mediumCircle.setColor(frameNum / speed, color);
        }

        if (frameNum / speed < ufomoObject.smallCircle.numOfPixels()) {
            ufomoObject.smallCircle.setColor(frameNum / speed, color);
        }

        if (frameNum / speed < ufomoObject.octagon[0].numOfPixels() * 8) {
            int i = (frameNum / speed) / ufomoObject.octagon[0].numOfPixels();
            int j = (frameNum / speed) % ufomoObject.octagon[0].numOfPixels();
            ufomoObject.octagon[i].setColor(j, color);
        }

        for (int i = 0; i < 8; i++) {
            int startPoint1 = 2 + i * ufomoObject.octagon[i].numOfPixels();
            int startPoint2 = (i + 1) * ufomoObject.octagon[i].numOfPixels() - 2;
            if (frameNum / speed >= startPoint1 && frameNum / speed - startPoint1 < ufomoObject.lines[i*2].numOfPixels()) {
                ufomoObject.lines[i*2].setColor(frameNum / speed - startPoint1, color);
            }
            if (frameNum / speed >= startPoint2 && frameNum / speed - startPoint2 < ufomoObject.lines[i*2+1].numOfPixels()) {
                ufomoObject.lines[i*2+1].setColor(frameNum / speed - startPoint2, color);
            }
        }

        for (int i = 0; i < 8; i++) {
            int startPoint = i * ufomoObject.octagon[i].numOfPixels();
            if (frameNum / speed >= startPoint && frameNum / speed - startPoint < ufomoObject.beam[i].numOfPixels()) {
                ufomoObject.beam[i].setColor(frameNum / speed - startPoint, color);
            }
        }
    }
}
