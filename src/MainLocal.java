public class MainLocal {

    public static void main(String[] args) {
        Main main = new Main<>(true, false, "/Users/mbenarie/leds.csv");
        main.addProject(Config.SIMON);
        main.run();
    }
}
