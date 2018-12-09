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
    public IAnimationsProvider createAnimationsProvider() {
        return new BabushkaAnimationsProvider();
    }

    @Override
    public INetworkDataSource<BabushkaObject> CreateNetworkDataSource() {
        return new BabushkaNetworkDataSource();
    }
}

