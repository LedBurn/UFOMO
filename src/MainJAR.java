public class MainJAR {

    public static void main(String[] args) {
        Main main = new Main<>(false, false, "/home/pi/leds.csv");
        main.addProject(Config.HOME);
        main.run();
    }
}
