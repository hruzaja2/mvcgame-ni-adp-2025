package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;

public interface IGameObjectsFactory {
    public AbstractCannon createCannon();
    public AbstractMissile createMissile(double initAngle, int initVelocity);
}
