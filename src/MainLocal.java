public class MainLocal {

    public static void main(String[] args) {
        Main main = new Main<>(true);
        main.addProject(Config.BABUSHKA);
        main.addProject(Config.LINES);
        main.run();
    }
}
