public class MainJAR {

    public static void main(String[] args) {
        Main main = new Main<>(false, true);
        main.addProject(Config.SIMON);
        main.run();
    }
}
