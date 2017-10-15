public class MainLocal {

    public static void main(String[] args) {
        String soundsPath = Config.SOUNDS_FOLDER_PATH_LOCAL;
        if (soundsPath == null) {
            throw new RuntimeException("Local sounds path isn't configured. Please update the Config class");
        }
        boolean runSimulator = true;

        MakeEverythingOK.run(soundsPath, runSimulator);
    }
}
