import java.util.*;

/**
 * Mid to sides animation is an animation that moves chunks of leds from the middle to the line
 * to the sides (beginning and end) of the line.
 */
public class MidToSidesChunkAnimation extends Animation {

    private final ArrayList<Chunk> chunks = new ArrayList<>();
    private long currentCycleNum = -1;

    private final double chunkSpeed;
    private final double chunkSize;

    private final FADE_STYLE fadeStyle;

    private final double fadeSize;

    private final HSBColor color;

    public enum FADE_STYLE {
        NONE,
        BEGINNING,
        END,
        BEGINNING_AND_END
    }

    /**
     * @param ledObject  the led object this animation will run on
     * @param chunkSize  0-1 percent of chunk size from the size of mid to corner.
     *                   1(=100%) means that a new chunk will be started the moment the previous chunk has finished.
     * @param chunkSpeed 0-infinity percent of cycle time it will take for the chunk to move to the corner.
     *                   this value starting point is the first pixel of the chunk appearing the disappearing.
     *                   1(=100%) means that the chunk will start to exit when the cycle ends.
     * @param fadeStyle  enum FADE_STYLE
     * @param fadeSize   0-1 percent of chunk fade size out of the chunk size.
     *                   if BEGINNING_AND_END then the fade size is out of half the chunk size.
     * @param chunkColor the actual color of the chunk
     */

    public MidToSidesChunkAnimation(IPixelsArray ledObject, double chunkSize, double chunkSpeed,
                                    FADE_STYLE fadeStyle, double fadeSize, HSBColor chunkColor) {
        super(ledObject);
        this.chunkSpeed = chunkSpeed;
        this.chunkSize = chunkSize;
        this.fadeStyle = fadeStyle;
        this.fadeSize = fadeSize;
        this.color = chunkColor;
    }

    @Override
    public void animate(long cycleNum, double cycleTimePercent) {

        // clear
        ledObject.clear();

        // new chunk
        if (this.currentCycleNum != cycleNum) {
            this.currentCycleNum = cycleNum;
            this.chunks.add(new Chunk(cycleNum, this.color));
        }

        Iterator<Chunk> itr = this.chunks.iterator();
        while (itr.hasNext()) {
            Chunk chunk = itr.next();
            long cyclesCount = this.currentCycleNum - chunk.startedCycleNum;
            int effectedLeds = 0;

            double forwardChunkStartPoint = 0.5 + (cyclesCount + cycleTimePercent) * (1 / this.chunkSpeed) / 2;
            double forwardChunkEndPoint = forwardChunkStartPoint - this.chunkSize;
            double backwardChunkStartPoint = 0.5 - (cyclesCount + cycleTimePercent) * (1 / this.chunkSpeed) / 2;
            double backwardChunkEndPoint = backwardChunkStartPoint + this.chunkSize;

            for (int i = 0; i < this.ledObject.numOfPixels(); i++) {
                double pixelEffectedAreaForward = pixelEffectedArea(i, forwardChunkStartPoint,  Math.max(forwardChunkEndPoint, 0.5));
                double pixelEffectedAreaBackward = pixelEffectedArea(i, backwardChunkStartPoint, Math.min(backwardChunkEndPoint, 0.5));
                double effectedArea = Math.min(pixelEffectedAreaForward + pixelEffectedAreaBackward, 1.0);
                double fadeLevel = pixelFadeLevel(i,
                        forwardChunkStartPoint, forwardChunkEndPoint,
                        backwardChunkStartPoint, backwardChunkEndPoint);
                if (effectedArea <= 0.5) {
                    fadeLevel = 1.0;
                }

                if (effectedArea > 0) {
                    effectedLeds++;
                    HSBColor color = new HSBColor(chunk.chunkColor.hue, chunk.chunkColor.saturation,
                            chunk.chunkColor.brightness * effectedArea * (1 - fadeLevel));
                    if (this.ledObject.getColor(i).equals(HSBColor.BLACK)) {
                        this.ledObject.setColor(i, color);
                    } else {
                        this.ledObject.blendColor(i, color, HSBColor.BLEND_TYPE.ADDITIVE);
                    }
                }
            }

            if (effectedLeds == 0 && chunk.startedCycleNum != cycleNum) {
                itr.remove();
            }
        }
    }

    private double pixelFadeLevel(int pixelNum,
                                  double forwardAreaStart, double forwardAreaEnd,
                                  double backwardAreaStart, double backwardAreaEnd) {

        ArrayList<double[]> fades = new ArrayList<>();
        double fadeSizeScaled = this.chunkSize * this.fadeSize;
        double pixelScaled = pixelNum / (double) this.ledObject.numOfPixels();
        switch (this.fadeStyle) {
            case BEGINNING:
                if (pixelScaled > 0.5) {
                    fades.add(new double[]{forwardAreaStart - fadeSizeScaled, forwardAreaStart});
                } else {
                    fades.add(new double[]{backwardAreaStart + fadeSizeScaled, backwardAreaStart});
                }
                break;
            case END:
                if (pixelScaled > 0.5) {
                    fades.add(new double[]{forwardAreaEnd + fadeSizeScaled, forwardAreaEnd});
                } else {
                    fades.add(new double[]{backwardAreaEnd - fadeSizeScaled, backwardAreaEnd});
                }
                break;
            case BEGINNING_AND_END:
                fadeSizeScaled = fadeSizeScaled / 2;
                if (pixelScaled > 0.5) {
                    fades.add(new double[]{forwardAreaStart - fadeSizeScaled, forwardAreaStart});
                    fades.add(new double[]{forwardAreaEnd + fadeSizeScaled, forwardAreaEnd});
                } else {
                    fades.add(new double[]{backwardAreaStart + fadeSizeScaled, backwardAreaStart});
                    fades.add(new double[]{backwardAreaEnd - fadeSizeScaled, backwardAreaEnd});
                }
                break;
            case NONE:
                break;
        }

        ArrayList<Double> fadeLevels = new ArrayList<>();

        double pixelScaledStart = pixelScaled;
        double pixelScaledMid = (pixelNum + 0.5) / (double) this.ledObject.numOfPixels();
        double pixelScaledEnd = (pixelNum + 1) / (double) this.ledObject.numOfPixels();

        for (double[] fade : fades) {
            double fadeStart = fade[0];
            double fadeEnd = fade[1];
            if (fadeStart < fadeEnd) {
                if (pixelScaledMid > fadeStart && pixelScaledMid < fadeEnd) {
                    fadeLevels.add((pixelScaledMid - fadeStart) / (fadeEnd - fadeStart));
                } else if (pixelScaledMid <= fadeStart && pixelScaledEnd >= fadeStart) {
                    fadeLevels.add(0.0);
                } else if (pixelScaledMid >= fadeEnd && pixelScaledStart <= fadeEnd) {
                    fadeLevels.add(1.0);
                }
            } else {
                if (pixelScaledMid < fadeStart && pixelScaledMid > fadeEnd) {
                    fadeLevels.add((fadeStart - pixelScaledMid) / (fadeStart - fadeEnd));
                } else if (pixelScaledMid >= fadeStart && pixelScaledStart <= fadeStart) {
                    fadeLevels.add(0.0);
                } else if (pixelScaledMid <= fadeEnd && pixelScaledEnd >= fadeEnd) {
                    fadeLevels.add(1.0);
                }
            }
        }

        double min = Double.MAX_VALUE;
        for (double fadeLevel : fadeLevels) {
            min = Math.min(min, fadeLevel);
        }
       return fadeLevels.isEmpty() ? 0 : min;
    }

    private double pixelEffectedArea(int pixelNum, double areaStart, double areaEnd) {
        if (areaStart > areaEnd) {
            double temp = areaStart;
            areaStart = areaEnd;
            areaEnd = temp;
        }

        areaStart = areaStart * this.ledObject.numOfPixels();
        areaEnd = areaEnd * this.ledObject.numOfPixels();

        double pixelStart = pixelNum - 0.5;
        double pixelEnd = pixelNum + 0.5;

        if (pixelStart >= areaStart && pixelEnd <= areaEnd) {
            return 1.0;
        }

        if (pixelStart >= areaEnd || pixelEnd <= areaStart) {
            return 0;
        }

        if (pixelStart >= areaStart) {
            return areaEnd - pixelStart;
        }

        return  pixelEnd - areaStart;
    }

    private static class Chunk {
        private final HSBColor chunkColor;
        private final long startedCycleNum;

        public Chunk(long startedCycleNum, HSBColor chunkColor) {
            this.startedCycleNum = startedCycleNum;
            this.chunkColor = chunkColor;
        }
    }
}
