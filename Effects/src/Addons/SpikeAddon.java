//import java.util.LinkedList;
//
//public class SpikeAddon extends Addon {
//    private LinkedList<Double> positions = new LinkedList<>();
//    private boolean reversed = false;
//    private boolean onCircle = false;
//    private boolean onBeat = false;
//    private int spikeIndex = 7;
//
//    public SpikeAddon() {}
//    public SpikeAddon(boolean reversed, boolean onCircle) {
//        this.reversed = reversed;
//        this.onCircle = onCircle;
//    }
//
//    public SpikeAddon(boolean reversed, boolean onCircle, boolean onBeat, int spikeIndex) {
//        this.reversed = reversed;
//        this.onCircle = onCircle;
//        this.onBeat = onBeat;
//        this.spikeIndex = spikeIndex;
//    }
//
//    @Override
//    public void change(IPixelsArray ledObject, double level, boolean newBeat, boolean isOn, int[] eq) {
//        if (onBeat && newBeat) {
//            positions.add(0.0);
//        } else if (eq[spikeIndex]/128.0 > 0.75) {
//            positions.add(0.0);
//        }
//
//        for (int i = 0; i < positions.size(); i++) {
//            int position = (int)Math.floor(positions.get(i));
//            if (onCircle) {
//                if (position >= 0 && position < ledObject.numOfPixels()/2) {
//                    ledObject.getColor(position).saturation = 0.0;
//                    ledObject.getColor(position).brightness = 1.0;
//                }
//                int position2 = ledObject.numOfPixels() - position - 1;
//                if (position2 >= ledObject.numOfPixels()/2 && position2 < ledObject.numOfPixels()) {
//                    ledObject.getColor(position2).saturation = 0.0;
//                    ledObject.getColor(position2).brightness = 1.0;
//                }
//            } else {
//                if (reversed) {
//                    position = ledObject.numOfPixels() - position - 1;
//                }
//                if (position >= 0 && position < ledObject.numOfPixels()) {
//                    ledObject.getColor(position).saturation = 0.0;
//                    ledObject.getColor(position).brightness = 1.0;
//                }
//
//            }
//            positions.set(i, positions.get(i) + 1);
//        }
//
//        LinkedList<Double> copy = new LinkedList<Double>(positions);
//        for( Double position : copy ) {
//            if (position > 1000) positions.remove(position);
//        }
//    }
//}
