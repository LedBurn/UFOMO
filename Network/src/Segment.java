public class Segment {

    private String name;                // can be any string
    private int stripId;                // the strip number in the controller
    private int startingPixelId;        // the index of the first pixel we want to send
    private int numOfLeds;              // number of leds in this segment
    private RGBColor[] data;            // the actual RGB data


    public Segment(String name, int stripId, int startingPixelId, int numOfLeds) {
        this.name = name;
        this.stripId = stripId;
        this.startingPixelId = startingPixelId;
        this.numOfLeds = numOfLeds;

        if (numOfLeds > 300) {
            System.out.println("Error: Max num of leds to segment is 300");
        }
    }

    public void setData(RGBColor[] data) {
        if (data.length != numOfLeds) {
            System.out.println("Error: Can't send " + data.length + " leds to segment " + name);
            return;
        }
        this.data = data;
    }


    public String getName() {
        return name;
    }

    public int getStripId() {
        return stripId;
    }

    public int getStartingPixelId() {
        return startingPixelId;
    }

    public RGBColor[] getData() {
        return data;
    }
}
