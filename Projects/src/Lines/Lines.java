public class Lines implements IProject<LinesObject> {

    @Override
    public LinesObject createLEDObject() {
        return new LinesObject();
    }

    @Override
    public ISimulatedLEDObject createSimulatedLEDObject() {
        return new LinesSimulated();
    }

    @Override
    public IAnimationsProvider createAnimationsProvider() {
        return new LinesAnimationsProvider();
    }

    @Override
    public INetworkDataSource<LinesObject> CreateNetworkDataSource() {
        return new LinesNetworkDataSource();
    }
}
