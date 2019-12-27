public class MainLocal {

    public static void main(String[] args) {
        Main main = new Main<>(true);
        main.addProject(Config.SIMON);
        main.run();
    }
}
