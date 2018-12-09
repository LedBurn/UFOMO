import java.util.ArrayList;

public class BabushkaBuildingAnimation {

    public static class BuildingAnimation extends Animation<BabushkaObject>{

        private ArrayList<int[]> runningDots;
        private boolean reversed;

        public BuildingAnimation(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            runningDots = new ArrayList<>();
            reversed = Math.random() < 0.5;
        }

        @Override
        protected void newBeat() {}

        @Override
        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
            super.apply(timePercent, newBeat, isOn, eq);

            if (Math.random() < 0.6) {
                int random = ledObject.randomBabushka();
                int[] newDot = {random, reversed ? ledObject.babushkas[random].numOfPixels() - 1 : 0};
                int counter = 0;


                while (ledObject.babushkas[newDot[0]].getColor(newDot[1]) != HSBColor.BLACK && counter < 4) {
                    random = ledObject.randomBabushka();
                    newDot = new int[]{random, reversed ? ledObject.babushkas[random].numOfPixels() - 1 : 0};
                    counter++;
                }
                if (counter < 4) {
                    ledObject.babushkas[newDot[0]].setColor(newDot[1], new HSBColor(Math.random(), 1.0, 0.5 + Math.random() / 2));
                    runningDots.add(newDot);
                }
            }

            for (int i = 0; i < runningDots.size(); i++) {
                int[] dot = runningDots.get(i);
                IPixelsArray babushka = ledObject.babushkas[dot[0]];

                if (!reversed && dot[1] + 1 < babushka.numOfPixels() && babushka.getColor(dot[1] + 1) == HSBColor.BLACK) {
                    babushka.setColor(dot[1] + 1, babushka.getColor(dot[1]));
                    babushka.setColor(dot[1], HSBColor.BLACK);
                    dot[1]++;
                } else if (reversed && dot[1] - 1 >= 0 && babushka.getColor(dot[1] - 1) == HSBColor.BLACK) {
                    babushka.setColor(dot[1] - 1, babushka.getColor(dot[1]));
                    babushka.setColor(dot[1], HSBColor.BLACK);
                    dot[1]--;
                }
            }
        }
    }


    public static class BuildingAnimationHalf1 extends Animation<BabushkaObject>{

        private ArrayList<int[]> runningDots;

        public BuildingAnimationHalf1(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            runningDots = new ArrayList<>();
        }

        @Override
        protected void newBeat() {}

        @Override
        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
            super.apply(timePercent, newBeat, isOn, eq);

            if (Math.random() < 0.6) {
                int random = ledObject.randomBabushka();
                int[] newDot = {random, Math.random() < 0.5 ? ledObject.babushkas[random].numOfPixels() - 1 : 0};
                int counter = 0;


                while (ledObject.babushkas[newDot[0]].getColor(newDot[1]) != HSBColor.BLACK && counter < 8) {
                    random = ledObject.randomBabushka();
                    newDot = new int[]{random, Math.random() < 0.5 ? ledObject.babushkas[random].numOfPixels() - 1 : 0};
                    counter++;
                }
                if (counter < 8) {
                    ledObject.babushkas[newDot[0]].setColor(newDot[1], new HSBColor(Math.random(), 1.0, 0.5 + Math.random() / 2));
                    runningDots.add(newDot);
                }
            }

            for (int i = 0; i < runningDots.size(); i++) {
                int[] dot = runningDots.get(i);
                IPixelsArray babushka = ledObject.babushkas[dot[0]];

                if (dot[1] + 1 < babushka.numOfPixels()/2.0 && babushka.getColor(dot[1] + 1) == HSBColor.BLACK) {
                    babushka.setColor(dot[1] + 1, babushka.getColor(dot[1]));
                    babushka.setColor(dot[1], HSBColor.BLACK);
                    dot[1]++;
                } else if (dot[1] - 1 >= babushka.numOfPixels()/2.0 && babushka.getColor(dot[1] - 1) == HSBColor.BLACK) {
                    babushka.setColor(dot[1] - 1, babushka.getColor(dot[1]));
                    babushka.setColor(dot[1], HSBColor.BLACK);
                    dot[1]--;
                }
            }
        }
    }

    public static class BuildingAnimationHalf2 extends Animation<BabushkaObject>{

        private ArrayList<int[]> runningDots;
        private ArrayList<int[]> centerDots;
        private int cycleNum;

        public BuildingAnimationHalf2(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            runningDots = new ArrayList<>();
            centerDots = new ArrayList<>();
        }

        @Override
        protected void newBeat() {}

        @Override
        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
            super.apply(timePercent, newBeat, isOn, eq);
            cycleNum++;
            if (cycleNum % 2 != 0) return;

            if (Math.random() < 0.6) {
                int random = ledObject.randomBabushka();
                int center = (int) Math.floor(ledObject.babushkas[random].numOfPixels()/2.0);
                int counter = 0;


                while (ledObject.babushkas[random].getColor(center) != HSBColor.BLACK && counter < 8) {
                    random = ledObject.randomBabushka();
                    center = (int) Math.floor(ledObject.babushkas[random].numOfPixels()/2.0);
                    counter++;
                }

                if (counter < 8) {
                    boolean odd = ledObject.babushkas[random].numOfPixels() % 2 == 1;
                    if (odd) {
                        int[] newDot = {random, center};
                        centerDots.add(newDot);
                        ledObject.babushkas[newDot[0]].setColor(newDot[1], new HSBColor(Math.random(), 1.0, 0.5 + Math.random() / 2));
                    } else {

                        HSBColor color = new HSBColor(Math.random(), 1.0, 0.5 + Math.random() / 2);
                        int[] newDot1 = {random, center, 1};
                        runningDots.add(newDot1);
                        ledObject.babushkas[newDot1[0]].setColor(newDot1[1], color);

                        int[] newDot2 = {random, center-1, -1};
                        runningDots.add(newDot2);
                        ledObject.babushkas[newDot2[0]].setColor(newDot2[1], color);
                    }
                }
            }

            ArrayList<int[]> addToRunning = new ArrayList<>();
            ArrayList<int[]> toRemove = new ArrayList<>();
            for (int[] dot : centerDots) {
                IPixelsArray babushka = ledObject.babushkas[dot[0]];

                if (babushka.getColor(dot[1] - 1) == HSBColor.BLACK) {
                    int[] newDot1 = {dot[0], dot[1]-1, -1};
                    addToRunning.add(newDot1);
                    ledObject.babushkas[newDot1[0]].setColor(newDot1[1], babushka.getColor(dot[1]));

                    int[] newDot2 = {dot[0], dot[1]+1, 1};
                    addToRunning.add(newDot2);
                    ledObject.babushkas[newDot2[0]].setColor(newDot2[1], babushka.getColor(dot[1]));

                    ledObject.babushkas[dot[0]].setColor(dot[1], HSBColor.BLACK);

                    toRemove.add(dot);
                }
            }
            centerDots.removeAll(toRemove);

            for (int i = 0; i < runningDots.size(); i++) {
                int[] dot = runningDots.get(i);
                IPixelsArray babushka = ledObject.babushkas[dot[0]];

                if (dot[2] == 1 && dot[1]+1 < babushka.numOfPixels() && babushka.getColor(dot[1] + 1) == HSBColor.BLACK) {
                    babushka.setColor(dot[1] + 1, babushka.getColor(dot[1]));
                    babushka.setColor(dot[1], HSBColor.BLACK);
                    dot[1]++;
                } else if (dot[2] == -1 && dot[1] - 1 >=0 && babushka.getColor(dot[1] - 1) == HSBColor.BLACK) {
                    babushka.setColor(dot[1] - 1, babushka.getColor(dot[1]));
                    babushka.setColor(dot[1], HSBColor.BLACK);
                    dot[1]--;
                }
            }

            runningDots.addAll(addToRunning);
        }
    }
}
