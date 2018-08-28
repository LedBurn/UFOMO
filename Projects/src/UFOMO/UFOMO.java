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
    public IAnimationsProvider createAnimationsProvider() {
        return new UFOMOAnimationsProvider();
    }

    @Override
    public INetworkDataSource<UFOMOObject> CreateNetworkDataSource() {
        return new UFOMONetworkDataSource();
    }
}
