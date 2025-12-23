package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.decorator.ExplosiveMissileDecorator;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.decorator.FastMissileDecorator;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.decorator.PiercingMissileDecorator;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.CannonA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.EnemyA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.MissileA;
import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class GameObjectsFactoryA implements IGameObjectsFactory{

    protected final IGameModel model;

    public GameObjectsFactoryA(IGameModel model){
        this.model = model;
    }

    @Override
    public CannonA createCannon() {
        return new CannonA(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this);
    }

    @Override
    public AbstractMissile createMissile(double initAngle, int initVelocity) {
        AbstractMissile missile = new MissileA(
            new Position(model.getCannonPosition().getX(), model.getCannonPosition().getY()),
            initAngle,
            initVelocity,
            model.getMovingStrategy()
        );

        // Apply decorators based on active power-ups
        if(model.isExplosiveMissiles()){
            missile = new ExplosiveMissileDecorator(missile);
        }
        if(model.isFastMissiles()){
            missile = new FastMissileDecorator(missile);
        }
        if(model.isPiercingMissiles()){
            missile = new PiercingMissileDecorator(missile);
        }

        return missile;
    }

    @Override
    public AbstractEnemy createEnemy(Position position, int health, int speed) {
        return new EnemyA(position, health, speed);
    }
}
