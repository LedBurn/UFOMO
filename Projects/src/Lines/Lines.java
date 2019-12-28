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
    public IAnimationsRunner createAnimationsRunner(boolean runGPIO) {
        return new SimpleAnimationsRunner(new LinesAnimationsProvider());
    }

    @Override
    public INetworkDataSource<LinesObject> CreateNetworkDataSource() {
        return new LinesNetworkDataSource();
    }
}
