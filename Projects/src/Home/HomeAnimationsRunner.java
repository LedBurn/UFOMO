

import de.ralleytn.simple.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeAnimationsRunner implements IAnimationsRunner<HomeObject>, IServerListener {

    private final TimingHelper timingHelper = new TimingHelper();

    private double brightnessLevel = 1.0;
    private Animation currentAnimation;

    Server server;

    private DB db;

    private boolean isOn = true;
    private HomeObject homeObject = new HomeObject();

//    private ISimpleRunnerAnimationsProvider provider;

    public HomeAnimationsRunner(ISimpleRunnerAnimationsProvider provider) {
        server = new Server(this);
        server.startListening();
        db = new DB();

        this.brightnessLevel = this.db.jsonObject.getObject("state").getDouble("brightness-level");
        this.timingHelper.setCycleTime(this.db.jsonObject.getObject("state").getLong("cycle-time"));
    }

    @Override
    public void apply(ILEDObject<HomeObject> ledObject) {
        this.homeObject = (HomeObject) ledObject;
        if (currentAnimation != null) {
            timingHelper.newFrame();
            currentAnimation.animate(timingHelper.getCycleNum(), timingHelper.getCycleProgress());
        } else {
            // read from state
            this.currentAnimation = this.getAnimationForMap(this.db.jsonObject.getObject("state").getObject("animation"));
            this.timingHelper.newAnimation();
            this.isOn = true;
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

    @Override
    public JSONObject handleRequest(Map<String, String> userInput) {
        JSONObject jsonObject = new JSONObject();

        // handle input
        if (userInput != null) {

            // brightness
            String brightnessLevel = userInput.get("brightness-level");
            if (brightnessLevel != null) {
                this.brightnessLevel = new Double(brightnessLevel);
                this.db.jsonObject.getObject("state").put("brightness-level", brightnessLevel);
                this.db.saveDB();
            }

            // speed
            String cycleTime = userInput.get("cycle-time");
            if (cycleTime != null) {
                this.timingHelper.setCycleTime(new Long(cycleTime));
                this.db.jsonObject.getObject("state").put("cycle-time", cycleTime);
                this.db.saveDB();
            }

            // animations
            String actionType = userInput.get("action-type");
            if (actionType != null && actionType.equals("animation")) {
                this.currentAnimation = this.getAnimationForMap(userInput);
                this.timingHelper.newAnimation();
                this.isOn = true;
                this.db.jsonObject.getObject("state").put("animation", new JSONObject(userInput));
                this.db.saveDB();
            } else if (actionType != null && actionType.equals("state")) {
                jsonObject.put("brightness", this.brightnessLevel);
                jsonObject.put("cycleTime", this.timingHelper.getCycleTime());
            }
        }

        return jsonObject;
    }

    private HomeAnimation getAnimationForMap(Map map) {
        String animationType = (String) map.get("animation-type");
        HomeAnimation animation = null;
        if (animationType != null && animationType.equals("mid-to-corner")) {
            animation = new HomeMidToCornerAnimation(this.homeObject, map);
        } else if (animationType != null && animationType.equals("kelvin-scale")) {
            animation = new HomeKelvinAnimation(this.homeObject, map);
        } else if (animationType != null && animationType.equals("alternate")) {
            animation = new HomeAlternateAnimation(this.homeObject, map);
        } else if (animationType != null && animationType.equals("2-color-pwm")) {
            animation = new HomeAlternate2Animation(this.homeObject, map);
        }
        return animation;
    }
}
