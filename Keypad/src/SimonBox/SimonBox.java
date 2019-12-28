import java.awt.*;
import java.awt.event.KeyEvent;

public class SimonBox implements ISimonBoxListener {
    public static final int RED_BIG = 0;
    public static final int GREEN_BIG = 1;
    public static final int BLUE_BIG = 2;
    public static final int YELLOW_BIG = 3;
    public static final int RED_SMALL = 4;
    public static final int GREEN_SMALL = 5;
    public static final String[] BUTTONS_NAMES = {"RED", "GREEN", "BLUE", "YELLOW", "RED SMALL", "GREEN SMALL"};

    private static final int NUM_OF_BUTTONS = 6;
    private boolean[] buttonStates = new boolean[NUM_OF_BUTTONS];
    private boolean[] previousButtonStates = new boolean[NUM_OF_BUTTONS];

    private SimonBoxGPIO gpio;
    private SimonBoxKeyboard keyboard;

    public SimonBox(boolean runKeyboard, boolean runGPIO) {
        if (runKeyboard) keyboard = new SimonBoxKeyboard(this);
        if (runGPIO) gpio = new SimonBoxGPIO(this);
    }

    @Override
    public void setButtonState(int buttonId, boolean state) {
        if (buttonStates[buttonId] != state) {
            System.out.println(BUTTONS_NAMES[buttonId] + " Button - " + (state ? "Pressed" : "Released"));
        }

        buttonStates[buttonId] = state;
    }

    // returns the current state
    public boolean[] getButtonStates() {
        return buttonStates;
    }

    // updates the previous state with the current one
    public void updatePreviousState() {
        System.arraycopy(buttonStates, 0, previousButtonStates, 0, NUM_OF_BUTTONS);
    }

    // return true is there's at least one change from the previous state
    public boolean hasChanges() {
        for (int i = 0; i < NUM_OF_BUTTONS; i++) {
            if (previousButtonStates[i] != buttonStates[i])
                return true;
        }
        return false;
    }

    // return true if more than one button is currently pressed
    public boolean hasMoreThanOnePressed() {
        int numberOfPressed = 0;
        for (int i = 0; i < NUM_OF_BUTTONS; i++) {
           if (buttonStates[i]) {
               numberOfPressed++;
           }
        }
        return numberOfPressed > 1;
    }

    // return the first newly released id, or -1 if none.
    public int newlyReleasedId() {
        for (int i = 0; i < NUM_OF_BUTTONS; i++) {
            if (previousButtonStates[i] && !buttonStates[i]) {
                return i;
            }
        }
        return -1;
    }

    // return true if there is any change in the states or if any button is pressed
    public boolean hasInteraction() {
        for (int i = 0; i < NUM_OF_BUTTONS; i++) {
            if (previousButtonStates[i] != buttonStates[i])
                return true;
            if (buttonStates[i])
                return true;
        }
        return false;
    }

    // returns the numbers of currently pressed buttons
    public int numberOfPressed() {
        int numberOfPressed = 0;
        for (boolean state: buttonStates) {
            if (state) {
                numberOfPressed++;
            }
        }
        return numberOfPressed;
    }
}
