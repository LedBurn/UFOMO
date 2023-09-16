import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class SimonAnimationsRunner implements IAnimationsRunner {

    private SimonGame game;
    private SimonBox box;

    private ArrayList<SimonAnimation> animations = new ArrayList<>();
    private SimonPressedAnimation pressedAnimation = new SimonPressedAnimation();

    private long lastUserInteractionTime;

    private ISimpleRunnerAnimationsProvider simpleRunnerProvider = new SignSimpleAnimationsProvider();
    private SimpleAnimationsRunner runner = new SimpleAnimationsRunner(simpleRunnerProvider);

    private String statsFile;

    public SimonAnimationsRunner(boolean runGPIO, String statsFile) {
        box = new SimonBox(true, runGPIO);
        game = new SimonGame(new int[]{SimonBox.RED_BIG, SimonBox.GREEN_BIG, SimonBox.BLUE_BIG, SimonBox.YELLOW_BIG});
        game.newGame();
        animations.add(new SimonSequenceAnimation(game.getGameSequence()));
        this.statsFile = statsFile;
        lastUserInteractionTime = Long.MIN_VALUE;
    }

    @Override
    public void apply(ILEDObject ledObject) {
        long currentTime = System.currentTimeMillis();

        if (box.newlyReleasedId() == SimonBox.RED_SMALL || box.newlyReleasedId() == SimonBox.GREEN_SMALL) { // force animation - stop game
            lastUserInteractionTime = 0;
            animations.clear();
            runner.forceNewAnimation();

        } else if (animations.size() > 0) { // we have animations to run
            SimonAnimation animation = animations.get(0);
            if (animation.startTime == 0) animation.startTime = currentTime;
            if (currentTime - animation.startTime >= animation.totalTime) { // animation is done
                animations.remove(0);
            } else {
                double timePercent = (currentTime - animation.startTime) / animation.totalTime;
                animation.apply((SignLEDObject) ledObject, timePercent);
                lastUserInteractionTime = currentTime;
            }

        } else if (currentTime - lastUserInteractionTime > 10 * 1000) { // wait for a player
            if (box.hasGameInteraction()) {
                game.newGame();
                animations.add(new SimonSequenceAnimation(game.getGameSequence()));
            }
            runner.apply(ledObject);
        } else { // play game

            int pressedButtonId = box.newlyPressedId();
            if (pressedButtonId != -1 && pressedButtonId < 4) {
                Sound.bips[pressedButtonId].play();
            }

            if (!box.hasChanges()) { // no change
                pressedAnimation.apply((SignLEDObject) ledObject, box.getButtonStates());

            } else if (box.hasMoreThanOnePressed()) { // invalid change
                printStats(game.getGameSequence().size());
                game.newGame();
                animations.add(new SimonFailedAnimation());
                animations.add(new SimonSequenceAnimation(game.getGameSequence()));
                Sound.failed.play();

            } else {
                int releasedButtonId = box.newlyReleasedId();
                if (releasedButtonId != -1) { // button pressed
                    game.userPressed(releasedButtonId);
                    if (game.isLevelDone()) {
                        game.nextLevel();
                        animations.add(new SimonSuccessAnimation());
                        animations.add(new SimonSequenceAnimation(game.getGameSequence()));
                        Sound.success.play();
                    } else if (game.isGameOver()) {
                        printStats(game.getGameSequence().size());
                        game.newGame();
                        animations.add(new SimonFailedAnimation());
                        animations.add(new SimonSequenceAnimation(game.getGameSequence()));
                        Sound.failed.play();
                    }
                }
            }
        }

        box.updatePreviousState();
    }


    private void printStats(int size) {
        if (size < 2) return;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        Path p = Paths.get(statsFile);
        String s = System.lineSeparator() + size + ", " + dtf.format(now);

        try (BufferedWriter writer = Files.newBufferedWriter(p, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(s);
        } catch (IOException ioe) {
            System.err.format("IOException: %s%n", ioe);
        }
    }
}
