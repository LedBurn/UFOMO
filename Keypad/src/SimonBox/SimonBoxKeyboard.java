import java.awt.*;
import java.awt.event.KeyEvent;

public class SimonBoxKeyboard {

    public SimonBoxKeyboard(ISimonBoxListener listener) {

        KeyEventDispatcher dispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {
                // button id
                int buttonId = -1;
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_1:
                        buttonId = SimonBox.RED_BIG;
                        break;
                    case KeyEvent.VK_2:
                        buttonId = SimonBox.GREEN_BIG;
                        break;
                    case KeyEvent.VK_3:
                        buttonId = SimonBox.BLUE_BIG;
                        break;
                    case KeyEvent.VK_4:
                        buttonId = SimonBox.YELLOW_BIG;
                        break;
                    case KeyEvent.VK_5:
                        buttonId = SimonBox.RED_SMALL;
                        break;
                    case KeyEvent.VK_6:
                        buttonId = SimonBox.GREEN_SMALL;
                        break;
                }
                if (buttonId == -1) return false;

                // state
                boolean isPressed = event.getID() == KeyEvent.KEY_PRESSED;

                // notify
                listener.setButtonState(buttonId, isPressed);
                return false;
            }
        };

        // start listening
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
    }
}
