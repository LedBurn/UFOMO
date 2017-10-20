import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class KeyPedServer implements Runnable {

    public void clearCurrentInupt() {
        synchronized (this) {
            this.lastInput = null;
        }
    }

    public Integer checkForInput() {
        Integer valToReturn;
        synchronized (this) {
            valToReturn = this.lastInput;
            this.lastInput = null;
        }
        return valToReturn;
    }

    @Override
    public void run() {

        while(true) {

            if(this.shouldCheckHeartbeat()) {
                boolean isOk = this.checkHeartBeat();
                if(!isOk) {
                    this.disconnected();
                }
            }

            if(this.welcomeSocket == null) {
                try {
                    this.welcomeSocket = new ServerSocket(5007);
                }
                catch (java.io.IOException exception) {
                    System.out.println(exception.getMessage());
                    continue;
                }
            }

            if(this.connectionSocket == null) {
                try {
                    this.connectionSocket = this.welcomeSocket.accept();
                    this.connectionSocket.setSoTimeout(100);
                    this.inFromClient = new BufferedReader(new InputStreamReader(this.connectionSocket.getInputStream()));
                    this.outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                    this.lastHeartBeatTime = System.currentTimeMillis();
                    System.out.println("keypad client connected successfully");
                }
                catch (java.io.IOException exception) {
                    this.connectionSocket = null;
                    this.inFromClient = null;
                    this.outToClient = null;
                    System.out.println(exception.getMessage());
                    continue;
                }
            }

            String clientSentence = null;
            try {
                clientSentence = this.inFromClient.readLine();
            }
            catch (java.net.SocketTimeoutException timeoutException) {
                continue;
            }
            catch (java.io.IOException exception) {
                System.out.println(exception.getClass());
                System.out.println(exception.getMessage());
                continue;
            }

            if (clientSentence != null) {
                for(int i=0; i<clientSentence.length(); i++) {
                    char c = clientSentence.charAt(i);
                    if(c == 'H') {
                        this.lastHeartBeatTime = System.currentTimeMillis();
                    }
                    else {
                        int choice = (int)(c - '0');
                        if(choice < 0 || choice > 9) {
                            System.out.println("received invalid char from keypad server. ignoring it. char is: " + c);
                            continue;
                        }
                        System.out.println("received keypad: " + choice);
                        synchronized (this) {
                            this.lastInput = choice;
                        }
                    }
                }
            }

        }

    }

    private boolean shouldCheckHeartbeat() {
        if(this.lastHeartBeatCheck == null ) {
            return true;
        }
        long timeFromLastCheck = System.currentTimeMillis() - this.lastHeartBeatCheck;
        if(timeFromLastCheck > 1000) {
            return true;
        }
        return false;
    }

    private void disconnected() {
        try {
            this.connectionSocket.close();
        }
        catch (java.io.IOException exception) {
            // TODO: what should we do here?
        }
        this.connectionSocket = null;
        this.inFromClient = null;
        this.outToClient = null;
    }

    private boolean checkHeartBeat() {

        long currMilis = System.currentTimeMillis();
        this.lastHeartBeatCheck = currMilis;

        // we don't have a socket, so nothing to check.
        if(this.connectionSocket == null) {
            return true;
        }

        // no heart beats for long time
        long timeFromLastHeartbeat = currMilis - this.lastHeartBeatTime;
        if(timeFromLastHeartbeat > (10 * 1000) ) {
            System.out.println("its been more then 10 seconds of quite from the keypad client. assuming it is dead");
            return false;
        }

        try {
            this.outToClient.write('H');
        }
        catch (java.io.IOException exception) {
            // nothing to do
        }

        return true;
    }

    private ServerSocket welcomeSocket;
    private Socket connectionSocket;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;

    private Long lastHeartBeatTime;
    private Long lastHeartBeatCheck = null;

    private Integer lastInput = null;
}
