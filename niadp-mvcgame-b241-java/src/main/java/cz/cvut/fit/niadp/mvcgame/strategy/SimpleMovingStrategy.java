package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;

public class SimpleMovingStrategy implements IMovingStrategy{

    @Override
    public void updatePosition(AbstractMissile missile) {
        missile.move(new Vector(MvcGameConfig.MOVE_STEP, 0));
    }

    @Override
    public IMovingStrategy getNextStrategy(GameModel model) {
        return model.getNextMovingStrategy(this);
    }
}
