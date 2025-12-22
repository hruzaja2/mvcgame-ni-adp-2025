package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;

public interface IMovingStrategy {
    public void updatePosition(AbstractMissile missile); 

    public IMovingStrategy getNextStrategy(GameModel model);
}
