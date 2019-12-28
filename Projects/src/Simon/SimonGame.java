import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

// simon game logic
public class SimonGame {
    private ArrayList<Integer> gameSequence;
    private int[] buttonIds;

    // user info
    private boolean gameOver = false;
    private boolean levelDone = false;
    private int userIndex;

    public SimonGame(int[] buttonIds) {
        this.buttonIds = buttonIds;
    }

    // clears the current sequence and start a new one
    public void newGame() {
        System.out.println("new game");
        gameSequence = new ArrayList<>();
        gameOver = false;
        nextLevel();
    };

    // adds a new element to the sequence and clear user data
    public void nextLevel() {
        Random random = new Random();
        Integer newElement = buttonIds[random.nextInt(buttonIds.length)];
        gameSequence.add(newElement);
        System.out.println("next level " + gameSequence);
        levelDone = false;
        userIndex = 0;
    }

    // handle a button pressed by the user
    public void userPressed(int buttonId) {
        boolean success = gameSequence.get(userIndex) == buttonId;
        userIndex++;

        if (success) {
            System.out.println("success");
            if (userIndex == gameSequence.size()) {
                levelDone = true;
            }
        } else {
            System.out.println("error");
            gameOver = true;
        }
    }

    // returns true if the user was able to complete the current sequence
    public boolean isLevelDone() {
        return levelDone;
    }

    // returns true if the user made a mistake and the game is over
    public boolean isGameOver() {
        return gameOver;
    }

    public ArrayList<Integer> getGameSequence() {
        return gameSequence;
    }
}
