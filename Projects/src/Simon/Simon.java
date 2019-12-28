

public class Simon implements IProject<SignLEDObject> {

    @Override
    public SignLEDObject createLEDObject() {
        return new SignLEDObject();
    }

    @Override
    public ISimulatedLEDObject createSimulatedLEDObject() {
        return new SignSimulated();
    }

    @Override
    public IAnimationsRunner createAnimationsRunner(boolean runGPIO, String statsFile) {
        return new SimonAnimationsRunner(runGPIO, statsFile);
    }

    @Override
    public INetworkDataSource<SignLEDObject> CreateNetworkDataSource() {
        return new SignNetworkDataSource();
    }
}
