public class MainLocal {

    public static void main(String[] args) {
        Main main = new Main<>(true, false);
        main.addProject(Config.SIMON);
        main.run();
    }
}
