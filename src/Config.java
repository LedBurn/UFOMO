public class Config {

    final public static IProject<LinesObject> LINES = new Lines();
    final public static IProject<HomeObject> HOME = new Home();
    final public static IProject<BabushkaObject> BABUSHKA = new Babushka();
    final public static IProject<SignLEDObject> SIMON = new Simon();

//    public static String statsFile = "/home/pi/simon_stats.csv";
    public static String statsFile = "~/simon/simon.csv";
}
