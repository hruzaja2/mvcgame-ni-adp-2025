package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public class MissileA extends AbstractMissile { 

    public MissileA(Position initPosition, double initAngle, int initVelocity, IMovingStrategy movingStrategy) {
        super(initPosition, initAngle, initVelocity, movingStrategy);
        position = initPosition;
    }
}
