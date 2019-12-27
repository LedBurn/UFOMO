

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
    public IAnimationsRunner createAnimationsRunner() {
        return new SimonAnimationsRunner();
    }

    @Override
    public INetworkDataSource<SignLEDObject> CreateNetworkDataSource() {
        return null;
    }
}
