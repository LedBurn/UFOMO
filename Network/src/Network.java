import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

// Network is the main object to send data to teensy.
// It uses the LED BURN protocol.
// You can add several controllers to the network, each controller has a different IP address.
// For each controller you can add segments. each segment is an actual pin in the teensy.
public class Network<T extends ILEDObject> {

    private LinkedList<Controller> controllers = new LinkedList<>();
    private long frameId = ThreadLocalRandom.current().nextInt(0, 1000000);
    private DatagramSocket socket;
    private INetworkDataSource<T> dataSource;

    public Network(INetworkDataSource<T> dataSource) {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        this.dataSource = dataSource;
        dataSource.configure(this);
    }

    public void addController(Controller controller) {
        this.controllers.add(controller);
    }

    public void send(T t) {
        dataSource.setData(this, t);
        for (Controller controller: this.controllers) {
            controller.send(socket, frameId);
        }
        frameId++;
    }
}
