public interface IProject<T extends ILEDObject> {

    // Create a new LED object
    T createLEDObject();

    // Create a new simulated LED object
    ISimulatedLEDObject createSimulatedLEDObject();

    // Create a new animations provider
    IAnimationsProvider createAnimationsProvider();

    // Create a new network data source
    INetworkDataSource<T> CreateNetworkDataSource();
}
