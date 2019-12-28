public class UFOMO implements IProject<UFOMOObject> {

    @Override
    public UFOMOObject createLEDObject() {
        return new UFOMOObject();
    }

    @Override
    public ISimulatedLEDObject createSimulatedLEDObject() {
        return new UFOMOSimulated();
    }

    @Override
    public IAnimationsRunner createAnimationsRunner(boolean runGPIO) {
        return new SimpleAnimationsRunner(new UFOMOAnimationsProvider());
    }

    @Override
    public INetworkDataSource<UFOMOObject> CreateNetworkDataSource() {
        return new UFOMONetworkDataSource();
    }
}
