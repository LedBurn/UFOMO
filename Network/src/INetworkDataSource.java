public interface INetworkDataSource<T extends ILEDObject> {

    void configure(Network network);
    
    void setData(Network network, T t);
}
