package SimonBox;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SimonBox {
    public static final int RED_BIG = 0;
    public static final int GREEN_BIG = 1;
    public static final int BLUE_BIG = 2;
    public static final int YELLOW_BIG = 3;
    public static final int RED_SMALL = 4;
    public static final int GREEN_SMALL = 5;

    private static final int NUM_OF_BUTTONS = 6;
    private boolean[] buttonStates = new boolean[NUM_OF_BUTTONS];
    private boolean[] previousButtonStates = new boolean[NUM_OF_BUTTONS];

    public void startListening() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                KeyboardFocusManager.getCurrentKeyboardFocusManager()
                        .addKeyEventDispatcher(new KeyEventDispatcher() {
                            @Override
                            public boolean dispatchKeyEvent(KeyEvent ke) {
                                setState(ke);
                                return false;
                            }
                        });
            }
        }).start();
    }

    public boolean[] getButtonStates() {
        return buttonStates;
    }

    // compare current state with previous state.
    // returns a new array with 0 if no change, +1 if now turned on, -1 if now turned off
    public int[] compareButtonStates() {
        int[] change = new int[NUM_OF_BUTTONS];
        for (int i = 0; i < NUM_OF_BUTTONS; i++) {
            if (previousButtonStates[i] && !buttonStates[i])
                change[i] = -1;
            else if (!previousButtonStates[i] && buttonStates[i]) {
                change[i] = 1;
            } else {
                change[i] = 0;
            }
        }
        return change;
    }

    public boolean hasChanges() {
        for (int i = 0; i < NUM_OF_BUTTONS; i++) {
            if (previousButtonStates[i] != buttonStates[i])
                return true;
        }
        return false;
    }

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

    // return true if there is a change in the buttons state or if a button is currently pressed
    public boolean hasInteraction() {
        boolean hasInteraction = false;
        for (int i = 0; i < NUM_OF_BUTTONS; i++) {
            if (previousButtonStates[i] != buttonStates[i])
                return true;
            if (buttonStates[i])
                return true;
        }
        return false;
    }

    public boolean hasSingleChange() {
        int numberOfChanges = 0;
        for (int i = 0; i < NUM_OF_BUTTONS; i++) {
            if (previousButtonStates[i] != buttonStates[i])
                numberOfChanges++;
        }
        return true;
    }


    // updates the previous state with the current one
    public void updatePreviousState() {
        System.arraycopy(buttonStates, 0, previousButtonStates, 0, NUM_OF_BUTTONS);
    }

    private void setState(KeyEvent ke) {
        boolean isPressed = ke.getID() == KeyEvent.KEY_PRESSED;
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_1:
                setState(RED_BIG, isPressed);
                break;
            case KeyEvent.VK_2:
                setState(GREEN_BIG, isPressed);
                break;
            case KeyEvent.VK_3:
                setState(BLUE_BIG, isPressed);
                break;
            case KeyEvent.VK_4:
                setState(YELLOW_BIG, isPressed);
                break;
        }
    }

    private void setState(int buttonKey, boolean state) {
        buttonStates[buttonKey] = state;
    }

    // return true if this button is currently pressed
    public boolean isButtonPressed(int buttonKey) {
        return buttonStates[buttonKey];
    }

    // return true if this button is currently pressed and no other button is pressed
    public boolean isButtonPressedExclusively(int buttonKey) {
        return buttonStates[buttonKey] && numberOfPressed() == 1;
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
