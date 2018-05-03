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
            case 91: {
                HSBColor color = new HSBColor(Math.random(), 1.0, 1.0);
                if (frameNum == ufomoObject.bigCircle.numOfPixels()) {
                    frameNum = 0;
                }
                ufomoObject.bigCircle.setColor(frameNum, color);

                if (frameNum < ufomoObject.mediumCircle.numOfPixels()) {
                    ufomoObject.mediumCircle.setColor(frameNum, color);
                }

                if (frameNum < ufomoObject.smallCircle.numOfPixels()) {
                    ufomoObject.smallCircle.setColor(frameNum, color);
                }

                if (frameNum < ufomoObject.octagon[0].numOfPixels() * 8) {
                    int i = frameNum / ufomoObject.octagon[0].numOfPixels();
                    int j = frameNum % ufomoObject.octagon[0].numOfPixels();
                    ufomoObject.octagon[i].setColor(j, color);
                }

                for (int i = 0; i < 8; i++) {
                    int startPoint1 = 10 + i * ufomoObject.octagon[i].numOfPixels();
                    int startPoint2 = (i + 1) * ufomoObject.octagon[i].numOfPixels() - 10;
                    if (frameNum >= startPoint1 && frameNum - startPoint1 < ufomoObject.lines[i*2].numOfPixels()) {
                        ufomoObject.lines[i*2].setColor(frameNum - startPoint1, color);
                    }
                    if (frameNum >= startPoint2 && frameNum - startPoint2 < ufomoObject.lines[i*2+1].numOfPixels()) {
                        ufomoObject.lines[i*2+1].setColor(frameNum - startPoint2, color);
                    }
                }
            }
        }

        frameNum++;
        return false;
    }
}
