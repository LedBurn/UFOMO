import SimonBox.SimonBox;

import java.util.ArrayList;
import java.util.Arrays;

public class Main<T extends ILEDObject> {

    private final boolean runSimulator;

    private final ArrayList<INetworkDataSource<T>> networkDataSources;
    private final ArrayList<Network<T>> networks;

    private final ArrayList<T> ledObjects;

    private final ArrayList<Simulator> simulators;
    private final ArrayList<ISimulatedLEDObject> simulatedObjects;

    private final ArrayList<IAnimationsRunner> animationsRunners;

    private final Keypad keypad;

//    private final SimonBox simonBox;

    // What's missing:
    // - testing mode
    // - brightness level
    // - gpio
    // - user codes
    public Main(boolean runSimulator) {

        this.runSimulator = runSimulator;

        // main LED object
        ledObjects = new ArrayList<>();

        // network
        networkDataSources =  new ArrayList<>();
        networks = new ArrayList<>();

        // simulator
        simulatedObjects = new ArrayList<>();
        simulators = new ArrayList<>();

        // animations
        animationsRunners = new ArrayList<>();

        // keypad
        keypad = new Keypad();
        keypad.startListening();

//        simonBox = new SimonBox();
//        simonBox.startListening();
    }

    public void addProject(IProject<T> project) {

        // main LED object
        ledObjects.add(project.createLEDObject());

        // network
//        INetworkDataSource<T> networkDataSource = project.CreateNetworkDataSource();
//        networkDataSources.add(networkDataSource);
//        networks.add(new Network<T>(networkDataSource));

        // simulator
        ISimulatedLEDObject simulatedObject = runSimulator ? project.createSimulatedLEDObject() : null;
        if (simulatedObject != null) simulatedObjects.add(simulatedObject);
        if (simulatedObject != null) simulators.add(new Simulator(simulatedObject));

        // animations
        animationsRunners.add(project.createAnimationsRunner());
    }

    public void run() {
        try {
            while (true) {
//                for (ISimpleRunnerAnimationsProvider animationsProvider : animationsProviders) {
//                    animationsProvider.handleUserCode(keypad.userCode);
//                }
                keypad.userCode = 0;
//                System.out.println(Arrays.toString(simonBox.compareButtonStates()));
//                System.out.println(Arrays.toString(simonBox.compareButtonStates(simonState, simonBox.getButtonStates())));
//                simonBox.updatePreviousState();

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

                for (int i = 0; i < ledObjects.size(); i++) {
                    animationsRunners.get(i).apply(ledObjects.get(i), keypad.newBeat, keypad.onBeat, keypad.eq);
                }

                keypad.newBeat = false;

                for (int i = 0; i < ledObjects.size(); i++) {
                    // show in simulator
                    if (runSimulator) simulators.get(i).draw(ledObjects.get(i), simulatedObjects.get(i), 0, 10);

                    // send network
//                    networks.get(i).send(ledObjects.get(i));
                }

                Thread.sleep(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}