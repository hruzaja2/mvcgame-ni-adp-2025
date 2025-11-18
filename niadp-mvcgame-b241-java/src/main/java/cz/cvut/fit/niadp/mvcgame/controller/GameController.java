package cz.cvut.fit.niadp.mvcgame.controller;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameKeys;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;

public class GameController {
    private final GameModel model;

    public GameController(GameModel model){
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        for(String code : pressedKeysCodes) {
            switch(code) {
                case MvcGameKeys.UP_KEY:
                    model.moveCannonUp();
                    break;
                case MvcGameKeys.DOWN_KEY:
                    model.moveCannonDown();
                    break;
                case MvcGameKeys.SPACE_KEY:
                    model.cannonShoot();
                    break;
                case MvcGameKeys.ESCAPE_KEY:
                    System.exit(0);
                    break;
                case MvcGameKeys.AIM_UP_KEY:
                    model.aimCannonUp();
                    break;
                case MvcGameKeys.AIM_DOWN_KEY:
                    model.aimCannonDown();
                    break;
                case MvcGameKeys.POWER_UP_KEY:
                    model.cannonPowerUp();
                    break;
                case MvcGameKeys.POWER_DOWN_KEY:
                    model.cannonPowerDown();
                    break;
                case MvcGameKeys.TOGGLE_MOVING_STRATEGY_KEY:
                    model.toggleMovingStrategy();
                    break;
                case MvcGameKeys.TOGGLE_SHOOTING_MODE_KEY:
                    model.toggleShootingMode();
                    break;
                default: 
                    //nothing
            }
        }
        model.update();
        pressedKeysCodes.clear(); //solves problem with multiple shots being fired for single press, as a side effect causes delay in the movement input
    }
}
