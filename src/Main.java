
public class Main<T extends ILEDObject> {

    private final INetworkDataSource<T> networkDataSource;
    private final Network<T> network;

    private final T ledObject;

    private final Simulator simulator;
    private final ISimulatedLEDObject simulatedObject;

    private final AnimationsRunner animationsRunner;
    private final IAnimationsProvider animationsProvider;

    private final Keypad keypad;

    // What's missing:
    // - testing mode
    // - brightness level
    // - gpio
    // - user codes

    public Main(IProject<T> project, boolean runSimulator, boolean runGPIO) {
        // main LED object
        ledObject = project.createLEDObject();

        // network
        networkDataSource =  project.CreateNetworkDataSource();
        network = new Network<T>(networkDataSource);

        // simulator
        simulatedObject = runSimulator ? project.createSimulatedLEDObject() : null;
        simulator = runSimulator ? new Simulator(simulatedObject) : null;

        // animations
        animationsProvider = project.createAnimationsProvider();
        animationsRunner = new AnimationsRunner(animationsProvider);

        // keypad
        keypad = new Keypad();
        keypad.startListening();
    }

    public void run() {
        try {
            while (true) {

                // user code
//                handleUserCode(keypad.userCode);


                long currentTime = System.currentTimeMillis();
                if (currentTime - keypad.lastBeatTime > 1000) { // if there's no beat for a long time - fake one
                    keypad.lastBeatTime = currentTime;
                    keypad.nextBeatTime = currentTime + 1000;
                    keypad.newBeat = true;
                }
                if (currentTime - keypad.lastEqStatus > 1000) {
                    for (int i = 0; i < keypad.eq.length; i++) { // if there's no eq for a long time - fake one
                        keypad.eq[i] = (int)Math.floor(Math.random() * 70) + 50;
                    }
                }

                animationsRunner.apply(ledObject, keypad.newBeat, keypad.onBeat, keypad.eq);

                keypad.newBeat = false;

                // show in simulator
                if (simulator != null) simulator.draw(ledObject, simulatedObject, 0, 10);

                // send network
                network.send(ledObject);

                Thread.sleep(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}