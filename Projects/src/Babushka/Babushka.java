public class Babushka implements IProject<BabushkaObject>{

    @Override
    public BabushkaObject createLEDObject() {
        return new BabushkaObject();
    }

    @Override
    public ISimulatedLEDObject createSimulatedLEDObject() {
        return new BabushkaSimulated();
    }

    @Override
    public IAnimationsRunner createAnimationsRunner(boolean runGPIO) {
        return new SimpleAnimationsRunner(new BabushkaAnimationsProvider());
    }

    @Override
    public INetworkDataSource<BabushkaObject> CreateNetworkDataSource() {
        return new BabushkaNetworkDataSource();
    }
}

