package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;

public interface IGameObjectsFactory {
    public AbstractCannon createCannon();
    public AbstractMissile createMissile(double initAngle, int initVelocity);
    public AbstractEnemy createEnemy(Position position);
}
