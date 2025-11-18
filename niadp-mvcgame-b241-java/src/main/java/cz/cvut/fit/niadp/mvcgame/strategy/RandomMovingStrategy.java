package cz.cvut.fit.niadp.mvcgame.strategy;

import java.util.Random;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;

public class RandomMovingStrategy implements IMovingStrategy {

    private final Random random;

    public RandomMovingStrategy(){
        this.random = new Random();
    }

    @Override
    public void updatePosition(AbstractMissile missile) {
        missile.move(
            new Vector(
                random.nextInt(-MvcGameConfig.MOVE_STEP, 2* MvcGameConfig.MOVE_STEP),
                random.nextInt(-MvcGameConfig.MOVE_STEP, MvcGameConfig.MOVE_STEP))
        );
    }

    @Override
    public IMovingStrategy getNextStrategy(GameModel model) {
        return model.getNextMovingStrategy(this);
    }
   
}
