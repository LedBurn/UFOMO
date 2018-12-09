public class BabushkaRippleAnimations {

    public static class Ripple1 extends Animation<BabushkaObject>{

        private double currentHue;
        private int currentCenter;
        private ConstColoring coloring;
        private ReduceBrightnessAddon addon;
        private boolean newCycle;

        public Ripple1(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            if (coloring == null) coloring = new ConstColoring();
            currentHue = Math.random();
            coloring.changeColor(new HSBColor(currentHue, 1.0, 1.0));
            currentCenter = (int) Math.floor(Math.random() * ledObject.babushkas.length);
            addon = new ReduceBrightnessAddon();
            newCycle = true;
        }

        @Override
        protected void newBeat() {}


        @Override
        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
            super.apply(timePercent, newBeat, isOn, eq);

            timePercent *= 6.0;
            while (timePercent >= 1.0) timePercent -= 1;

            if (timePercent < 0.1 && !newCycle) {
                configAnimations();
                coloring.color(ledObject.babushkas[currentCenter]);
            }

            if (timePercent >= 0.1) {
                newCycle = false;
            }

            addon.change(ledObject.babushkas[currentCenter], timePercent, false, false, new int[]{});
        }
    }

    public static class Ripple2 extends Animation<BabushkaObject>{

        private double currentHue;
        private int currentCenter;
        private ConstColoring coloring;
        private ReduceBrightnessAddon addon;
        private int cycle;

        public Ripple2(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            if (coloring == null) coloring = new ConstColoring();
            currentHue = Math.random();
            coloring.changeColor(new HSBColor(currentHue, 1.0, 1.0));
            currentCenter = (int) Math.floor(Math.random() * ledObject.babushkas.length);
            addon = new ReduceBrightnessAddon();
            cycle = 0;
        }

        @Override
        protected void newBeat() {}


        @Override
        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
            super.apply(timePercent, newBeat, isOn, eq);

            timePercent *= 3.0;
            while (timePercent >= 1.0) timePercent -= 1;

            double relativeTimePercent = timePercent;
            while (relativeTimePercent - 0.2 >= 0) {
                relativeTimePercent -= 0.2;
            }
            relativeTimePercent *= 5;

            if (timePercent < 0.2 && cycle != 0) {
                configAnimations();
            }

            timePercent = Math.max(0.0 , Math.min(1.0, timePercent));
            cycle = (int)Math.floor(timePercent * 5);

            if (cycle == 0) {
                coloring.color(ledObject.babushkas[currentCenter]);
                addon.change(ledObject.babushkas[currentCenter], relativeTimePercent, false, false, new int[]{});
                return;
            }

            if (currentCenter - cycle >= 0) {
                coloring.color(ledObject.babushkas[currentCenter - cycle]);
                addon.change(ledObject.babushkas[currentCenter - cycle], relativeTimePercent, false, false, new int[]{});
            }

            if (currentCenter + cycle < ledObject.babushkas.length) {
                coloring.color(ledObject.babushkas[currentCenter + cycle]);
                addon.change(ledObject.babushkas[currentCenter + cycle], relativeTimePercent, false, false, new int[]{});
            }
        }
    }

    public static class RippleIn extends Animation<BabushkaObject>{

        private double currentHue;
        private int currentCenter;
        private ConstColoring coloring;
        private ReduceBrightnessAddon addon;
        private int cycle;

        public RippleIn(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            if (coloring == null) coloring = new ConstColoring();
            currentHue = Math.random();
            coloring.changeColor(new HSBColor(currentHue, 1.0, 1.0));
            currentCenter = 0;
            addon = new ReduceBrightnessAddon();
            cycle = 0;
        }

        @Override
        protected void newBeat() {}


        @Override
        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
            super.apply(timePercent, newBeat, isOn, eq);

            timePercent *= 3.0;
            while (timePercent >= 1.0) timePercent -= 1;

            double relativeTimePercent = timePercent;
            while (relativeTimePercent - 0.2 >= 0) {
                relativeTimePercent -= 0.2;
            }
            relativeTimePercent *= 5;

            if (timePercent < 0.2 && cycle != 0) {
                configAnimations();
            }

            timePercent = Math.max(0.0 , Math.min(1.0, timePercent));
            cycle = (int)Math.floor(timePercent * 5);

            if (cycle == 0) {
                coloring.color(ledObject.babushkas[currentCenter]);
                addon.change(ledObject.babushkas[currentCenter], relativeTimePercent, false, false, new int[]{});
                return;
            }

            if (currentCenter - cycle >= 0) {
                coloring.color(ledObject.babushkas[currentCenter - cycle]);
                addon.change(ledObject.babushkas[currentCenter - cycle], relativeTimePercent, false, false, new int[]{});
            }

            if (currentCenter + cycle < ledObject.babushkas.length) {
                coloring.color(ledObject.babushkas[currentCenter + cycle]);
                addon.change(ledObject.babushkas[currentCenter + cycle], relativeTimePercent, false, false, new int[]{});
            }
        }
    }

    public static class RippleOut extends Animation<BabushkaObject>{

        private double currentHue;
        private int currentCenter;
        private ConstColoring coloring;
        private ReduceBrightnessAddon addon;
        private int cycle;

        public RippleOut(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            if (coloring == null) coloring = new ConstColoring();
            currentHue = Math.random();
            coloring.changeColor(new HSBColor(currentHue, 1.0, 1.0));
            currentCenter = ledObject.babushkas.length-1;
            addon = new ReduceBrightnessAddon();
            cycle = 0;
        }

        @Override
        protected void newBeat() {}


        @Override
        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
            super.apply(timePercent, newBeat, isOn, eq);

            timePercent *= 3.0;
            while (timePercent >= 1.0) timePercent -= 1;

            double relativeTimePercent = timePercent;
            while (relativeTimePercent - 0.2 >= 0) {
                relativeTimePercent -= 0.2;
            }
            relativeTimePercent *= 5;

            if (timePercent < 0.2 && cycle != 0) {
                configAnimations();
            }

            timePercent = Math.max(0.0 , Math.min(1.0, timePercent));
            cycle = (int)Math.floor(timePercent * 5);

            if (cycle == 0) {
                coloring.color(ledObject.babushkas[currentCenter]);
                addon.change(ledObject.babushkas[currentCenter], relativeTimePercent, false, false, new int[]{});
                return;
            }

            if (currentCenter - cycle >= 0) {
                coloring.color(ledObject.babushkas[currentCenter - cycle]);
                addon.change(ledObject.babushkas[currentCenter - cycle], relativeTimePercent, false, false, new int[]{});
            }

            if (currentCenter + cycle < ledObject.babushkas.length) {
                coloring.color(ledObject.babushkas[currentCenter + cycle]);
                addon.change(ledObject.babushkas[currentCenter + cycle], relativeTimePercent, false, false, new int[]{});
            }
        }
    }

    public static class RippleInOut extends Animation<BabushkaObject>{

        private double currentHue;
        private int currentCenter;
        private ConstColoring coloring;
        private ReduceBrightnessAddon addon;
        private int cycle;

        public RippleInOut(BabushkaObject babushkaObject) {
            super(babushkaObject);
        }

        @Override
        protected void configAnimations() {
            if (coloring == null) coloring = new ConstColoring();
            currentHue = Math.random();
            coloring.changeColor(new HSBColor(currentHue, 1.0, 1.0));
            currentCenter = currentCenter == 0 ? ledObject.babushkas.length-1 : 0;
            addon = new ReduceBrightnessAddon();
            cycle = 0;
        }

        @Override
        protected void newBeat() {}


        @Override
        public void apply(double timePercent, boolean newBeat, boolean isOn, int[] eq) {
            super.apply(timePercent, newBeat, isOn, eq);

            timePercent *= 3.0;
            while (timePercent >= 1.0) timePercent -= 1;

            double relativeTimePercent = timePercent;
            while (relativeTimePercent - 0.2 >= 0) {
                relativeTimePercent -= 0.2;
            }
            relativeTimePercent *= 5;

            if (timePercent < 0.2 && cycle != 0) {
                configAnimations();
            }

            timePercent = Math.max(0.0 , Math.min(1.0, timePercent));
            cycle = (int)Math.floor(timePercent * 5);

            if (cycle == 0) {
                coloring.color(ledObject.babushkas[currentCenter]);
                addon.change(ledObject.babushkas[currentCenter], relativeTimePercent, false, false, new int[]{});
                return;
            }

            if (currentCenter - cycle >= 0) {
                coloring.color(ledObject.babushkas[currentCenter - cycle]);
                addon.change(ledObject.babushkas[currentCenter - cycle], relativeTimePercent, false, false, new int[]{});
            }

            if (currentCenter + cycle < ledObject.babushkas.length) {
                coloring.color(ledObject.babushkas[currentCenter + cycle]);
                addon.change(ledObject.babushkas[currentCenter + cycle], relativeTimePercent, false, false, new int[]{});
            }
        }
    }
}
