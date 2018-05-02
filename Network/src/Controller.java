import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    private String name;
    private InetAddress ipAddress;
    private List<Segment> storedSegments = new LinkedList<>();

    public Controller(String name, InetAddress ipAddress){
        this.name = name;
        this.ipAddress = ipAddress;
    }

    public void addSegments(Segment[] segments) {
        this.storedSegments.addAll(Arrays.asList(segments));
    }

    public void send(DatagramSocket socket, long currFrameId) {
        long segId = 0;
        for(Segment segmentData : this.storedSegments) {

            byte[] msgContent = new byte[24 + (3 * segmentData.getData().length)];

            // protocol prefix
            msgContent[0] = 'L'; msgContent[1] = 'e'; msgContent[2] = 'd'; msgContent[3] = 'B'; msgContent[4] = 'u'; msgContent[5] = 'r'; msgContent[6] = 'n';

            // protocol version
            msgContent[7] = 0;

            // frame id
            EncodeUInt32ToByteArray(currFrameId, msgContent, 8);

            // total segments for this frame and controller
            EncodeUInt32ToByteArray(this.storedSegments.size(), msgContent, 12);

            // current segment id
            EncodeUInt32ToByteArray(segId, msgContent, 16);
            segId++;

            // strip id (in the specific controller)
            EncodeUInt16ToByteArray(segmentData.getStripId(), msgContent, 20);

            // pixel id (in the specific controller and strip id)
            EncodeUInt16ToByteArray(segmentData.getStartingPixelId(), msgContent, 22);

            int msgIndex=24;
            for(RGBColor color : segmentData.getData()) {
                msgContent[msgIndex++] = color.r;
                msgContent[msgIndex++] = color.g;
                msgContent[msgIndex++] = color.b;
            }

            DatagramPacket sendPacket = new DatagramPacket(msgContent, msgContent.length, ipAddress, 2000);
            try {
                socket.send(sendPacket);
            }
            catch (IOException e) {
                // TODO: log?
            }

        }
    }

    private static void EncodeUInt32ToByteArray(long val, byte[] arr, int arrPos) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putLong(val);
        arr[arrPos + 0] = bytes[7];
        arr[arrPos + 1] = bytes[6];
        arr[arrPos + 2] = bytes[5];
        arr[arrPos + 3] = bytes[4];
    }

    private static void EncodeUInt16ToByteArray(long val, byte[] arr, int arrPos) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putLong(val);
        arr[arrPos + 0] = bytes[7];
        arr[arrPos + 1] = bytes[6];
    }
}