public class Home implements IProject<HomeObject> {

    @Override
    public HomeObject createLEDObject() {
        return new HomeObject();
    }

    @Override
    public ISimulatedLEDObject<HomeObject> createSimulatedLEDObject() {
        return new HomeSimulated();
    }

    @Override
    public IAnimationsRunner createAnimationsRunner(boolean runGPIO, String statsFile) {
        return new HomeAnimationsRunner(null);
    }

    @Override
    public INetworkDataSource<HomeObject> CreateNetworkDataSource() {
        return new HomeNetworkDataSource();
    }
}
