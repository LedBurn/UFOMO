public class MainJAR {

    public static void main(String[] args) {
        Main main = new Main<>(false);
        main.addProject(Config.BABUSHKA);
        main.addProject(Config.LINES);
        main.run();
    }
}
