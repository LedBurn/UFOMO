import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;

public class SimonBoxGPIO {

    public SimonBoxGPIO(ISimonBoxListener listener) {
        GpioController gpio = GpioFactory.getInstance();


        // RED BIG - grey line
        GpioPinDigitalInput redBigPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_01,
                SimonBox.BUTTONS_NAMES[SimonBox.RED_BIG], PinPullResistance.PULL_DOWN);


        // GREEN BIG - orange line
        GpioPinDigitalInput greenBigPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05,
                SimonBox.BUTTONS_NAMES[SimonBox.GREEN_BIG], PinPullResistance.PULL_DOWN);


        // BLUE BIG - purple line
        GpioPinDigitalInput blueBigPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00,
                SimonBox.BUTTONS_NAMES[SimonBox.BLUE_BIG], PinPullResistance.PULL_DOWN);


        // YELLOW BIG - brown line
        GpioPinDigitalInput yellowBigPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04,
                SimonBox.BUTTONS_NAMES[SimonBox.YELLOW_BIG], PinPullResistance.PULL_DOWN);


        // RED SMALL - green line
        GpioPinDigitalInput redSmallPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03,
                SimonBox.BUTTONS_NAMES[SimonBox.RED_SMALL], PinPullResistance.PULL_DOWN);


        // GREEN SMALL - yellow line
        GpioPinDigitalInput greenSmallPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,
                SimonBox.BUTTONS_NAMES[SimonBox.GREEN_SMALL], PinPullResistance.PULL_DOWN);


        // listen to changes
        GpioPinListenerDigital gpioPinListener = new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

                // button id
                int buttonId = Arrays.asList(SimonBox.BUTTONS_NAMES).indexOf(event.getPin().getName());
                if (buttonId == -1) return;

                // state
                boolean state = event.getState().isHigh();

                // notify
                listener.setButtonState(buttonId, state);
            }
        };
        redBigPin.addListener(gpioPinListener);
        greenBigPin.addListener(gpioPinListener);
        blueBigPin.addListener(gpioPinListener);
        yellowBigPin.addListener(gpioPinListener);
        redSmallPin.addListener(gpioPinListener);
        greenSmallPin.addListener(gpioPinListener);
    }
}
