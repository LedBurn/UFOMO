import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class SingleControllerData {

    /*
    data - array of RGBColor. The number of pixels in the segment is givin by to the array length.
    the RGBColor data type already has the pixel color in the correct format which is 3 integers in the range [0-255]
     */
    public void addSegment(RGBColor[] data, int stripId, int pixelId) {
        SegmentData newSegment = new SegmentData();
        newSegment.data = data;
        newSegment.stripId = stripId;
        newSegment.pixelId = pixelId;
        this.storedSegments.add(newSegment);
    }

    public void send(long currFrameId) {

        if(!createSocket()) {
            this.storedSegments.clear();
            return;
        }

        if(!hasAddress()) {
            this.storedSegments.clear();
            return;
        }

        long segId = 0;
        for(SegmentData segmentData : this.storedSegments) {

            byte[] msgContent = new byte[24 + (3 * segmentData.data.length)];

            // protocol prefix
            msgContent[0] = 'L'; msgContent[1] = 'e'; msgContent[2] = 'd'; msgContent[3] = 'B'; msgContent[4] = 'u'; msgContent[5] = 'r'; msgContent[6] = 'n';

            // protocol version
            msgContent[7] = 0;

            // frame id
            EncodeUint32ToByteArray(currFrameId, msgContent, 8);

            // total segments for this frame and controller
            EncodeUint32ToByteArray(this.storedSegments.size(), msgContent, 12);

            // current segment id
            EncodeUint32ToByteArray(segId, msgContent, 16);
            segId++;

            // strip id (in the specific controller)
            EncodeUint16ToByteArray(segmentData.stripId, msgContent, 20);

            // pixel id (in the specific controller and strip id)
            EncodeUint16ToByteArray(segmentData.pixelId, msgContent, 22);

            int msgIndex=24;
            for(RGBColor color : segmentData.data) {
                msgContent[msgIndex++] = color.r;
                msgContent[msgIndex++] = color.g;
                msgContent[msgIndex++] = color.b;
            }

            DatagramPacket sendPacket = new DatagramPacket(msgContent, msgContent.length, ipAddress, 2000);
            try {
                clientSocket.send(sendPacket);
            }
            catch (IOException e) {
                // TODO: log?
            }

        }
        this.storedSegments.clear();
    }

    private boolean hasAddress() {

        // check if we already have a valid ip address
        if(this.ipAddress != null) {
            return true;
        }

        try {
            // TODO: get the ip as configuration
            // TODO: add broadcast to the controller (new message in LedBurn protocol) so it will publish the ip by name
            this.ipAddress = InetAddress.getByName("beaglebone.local");
            return true;
        }
        catch (UnknownHostException e) {
            // TODO: log?
            return false;
        }
    }
    private InetAddress ipAddress;

    private boolean createSocket() {

        // check if we already have a working socket
        if(this.clientSocket != null) {
            return true;
        }

        // try to create a socket
        try {
            this.clientSocket = new DatagramSocket();
            return true;
        }
        catch (SocketException e) {
            // TODO: log? Excpetion? why should it happen?
            return false;
        }
    }
    private DatagramSocket clientSocket;

    private static void EncodeUint32ToByteArray(long val, byte[] arr, int arrPos) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putLong(val);
        arr[arrPos + 0] = bytes[7];
        arr[arrPos + 1] = bytes[6];
        arr[arrPos + 2] = bytes[5];
        arr[arrPos + 3] = bytes[4];
    }

    private static void EncodeUint16ToByteArray(long val, byte[] arr, int arrPos) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putLong(val);
        arr[arrPos + 0] = bytes[7];
        arr[arrPos + 1] = bytes[6];
    }

    static class SegmentData {
        public RGBColor[] data;
        public int stripId;
        public int pixelId;
    }
    private List<SegmentData> storedSegments = new LinkedList<>();
}

public class Network {

    public void configure() {
        // TODO: use actual configuration
        this.controllers.put("test", new SingleControllerData());
    }

    public void send() {
        //iterating over values only
        for (SingleControllerData controller: this.controllers.values()) {
            controller.send(this.lastFrameId);
        }
        this.lastFrameId++;
    }

    public void addSegment(String controllerName, RGBColor[] data, int stripId, int pixelId) {
        SingleControllerData controller = this.controllers.get(controllerName);
        if(controller == null) {
            // TODO: log
            // TODO: exception?
            return;
        }
        controller.addSegment(data, stripId, pixelId);
    }

    private long lastFrameId = ThreadLocalRandom.current().nextInt(0, 1000000);

    private Map<String, SingleControllerData> controllers = new TreeMap<>();
}
