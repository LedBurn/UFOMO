import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class Network {

    private LinkedList<Controller> controllers = new LinkedList<>();
    private long frameId = ThreadLocalRandom.current().nextInt(0, 1000000);
    private DatagramSocket socket = new DatagramSocket();

    public Network() throws SocketException {
    }

    public void addController(Controller controller) {
        this.controllers.add(controller);
    }

    public void send() {
        for (Controller controller: this.controllers) {
            controller.send(socket, frameId);
        }
        frameId++;
    }
}
