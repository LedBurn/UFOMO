import java.util.ArrayList;

public class SimonAnimationsRunner implements IAnimationsRunner {

    private SimonGame game;
    private SimonBox box;

    private ArrayList<SimonAnimation> animations = new ArrayList<>();
    private SimonPressedAnimation pressedAnimation = new SimonPressedAnimation();

    private long lastUserInteractionTime;

    private ISimpleRunnerAnimationsProvider simpleRunnerProvider = new SignSimpleAnimationsProvider();
    private SimpleAnimationsRunner runner = new SimpleAnimationsRunner(simpleRunnerProvider);

    public SimonAnimationsRunner(boolean runGPIO) {
        box = new SimonBox(true, runGPIO);
        game = new SimonGame(new int[]{SimonBox.RED_BIG, SimonBox.GREEN_BIG, SimonBox.BLUE_BIG, SimonBox.YELLOW_BIG});
        game.newGame();
        animations.add(new SimonSequenceAnimation(game.getGameSequence()));
    }

    @Override
    public void apply(ILEDObject ledObject, boolean newBeat, boolean isOn, int[] eq) {
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
            runner.apply(ledObject, newBeat, isOn, eq);

        } else { // play game
            if (!box.hasChanges()) { // no change
                pressedAnimation.apply((SignLEDObject) ledObject, box.getButtonStates());

            } else if (box.hasMoreThanOnePressed()) { // invalid change
                game.newGame();
                animations.add(new SimonFailedAnimation());
                animations.add(new SimonSequenceAnimation(game.getGameSequence()));

            } else {
                int buttonId = box.newlyReleasedId();
                if (buttonId != -1) { // button pressed
                    game.userPressed(buttonId);
                    if (game.isLevelDone()) {
                        game.nextLevel();
                        animations.add(new SimonSuccessAnimation());
                        animations.add(new SimonSequenceAnimation(game.getGameSequence()));
                    } else if (game.isGameOver()) {
                        game.newGame();
                        animations.add(new SimonFailedAnimation());
                        animations.add(new SimonSequenceAnimation(game.getGameSequence()));
                    }
                }
            }
        }

        box.updatePreviousState();
    }
}
