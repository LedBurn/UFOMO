import jdk.nashorn.api.scripting.JSObject;
import org.json.simple.JSONObject;

import java.util.Map;

public class HomeAnimationsRunner implements IAnimationsRunner<HomeObject>, ServerListener {

    private long cycleTime = 5000; //milliseconds
    private long currentAnimationStartTime = 0; // milliseconds

    private double brightnessLevel = 1.0;
    private Animation currentAnimation;

    Server server;

//    private ISimpleRunnerAnimationsProvider provider;

    public HomeAnimationsRunner(ISimpleRunnerAnimationsProvider provider) {
        server = new Server(this);
        server.startListening();
    }

    private boolean isOn = true;
    private HomeObject homeObject;

    @Override
    public void apply(ILEDObject<HomeObject> ledObject) {
        this.homeObject = (HomeObject) ledObject;

        long currentTime = System.currentTimeMillis();
        long animationCurrentRunningTime = currentTime - currentAnimationStartTime;
        long cycleNum = animationCurrentRunningTime / this.cycleTime;
        double cycleProgress = (animationCurrentRunningTime % this.cycleTime) / (double) this.cycleTime;

        if (currentAnimation != null) {
            currentAnimation.animate(cycleNum, cycleProgress);
        } else {
//            currentAnimation = new HomeAlternateAnimation(homeObject, userInput);
//            currentAnimationStartTime = System.currentTimeMillis();
//            this.isOn = true;
        }

        if (!this.isOn) {
            ledObject.clear();
        }

        if (this.brightnessLevel < 1) {
            for (IPixelsArray pixelsArray : homeObject.all) {
                pixelsArray.reduceBrightness(this.brightnessLevel);
            }
        }
    }


    public void forceNewAnimation() {
//        setNewAnimation(provider.randomNewAnimation());
    }

    // force a new animation to start now
    private void setNewAnimation(Animation animation) {
//        currentAnimation = animation;
//        currentAnimationStartTime = System.currentTimeMillis();
//        nextAnimation = null;
//        nextAnimationStartTime = 0;
    }

    @Override
    public JSONObject handleRequest(Map<String, String> userInput) {

        JSONObject jsonObject = new JSONObject();
        // handle input
        if (userInput != null) {

            // brightness
            String brightnessLevel = userInput.get("brightness-level");
            if (brightnessLevel != null) {
                this.brightnessLevel = new Double(brightnessLevel);
            }

            // animations
            String actionType = userInput.get("action-type");
            if (actionType != null && actionType.equals("animation")) {
                String animationType = userInput.get("animation-type");
                if (animationType != null && animationType.equals("mid-to-corner")) {
                    currentAnimation = new HomeMidToCornerAnimation(this.homeObject, userInput);
                } else if (animationType != null && animationType.equals("kelvin-scale")) {
                    currentAnimation = new HomeKelvinAnimation(this.homeObject, userInput);
                } else if (animationType != null && animationType.equals("alternate")) {
                    currentAnimation = new HomeAlternate2Animation(this.homeObject, userInput);
                }

                String cycleTime = userInput.get("cycle-time");
                if (cycleTime != null) {
                    this.cycleTime = new Long(cycleTime);
                }
                currentAnimationStartTime = System.currentTimeMillis();
                this.isOn = true;
            } else if (actionType != null && actionType.equals("state")) {
                jsonObject.put("brightness", this.brightnessLevel);
            }
        }

        return jsonObject;
    }
}
