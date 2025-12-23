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
import cz.cvut.fit.niadp.mvcgame.pool.MissilePool;
import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class GameObjectsFactoryA implements IGameObjectsFactory{

    protected final IGameModel model;
    protected final MissilePool missilePool;

    public GameObjectsFactoryA(IGameModel model, MissilePool missilePool){
        this.model = model;
        this.missilePool = missilePool;
    }

    @Override
    public CannonA createCannon() {
        return new CannonA(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this);
    }

    @Override
    public AbstractMissile createMissile(double initAngle, int initVelocity) {
        // Use Object Pool instead of creating new missile
        AbstractMissile missile = missilePool.acquire(
            new Position(model.getCannonPosition().getX(), model.getCannonPosition().getY()),
            initAngle,
            initVelocity,
            model.getMovingStrategy()
        );

        // If pool is empty, return null
        if(missile == null) {
            return null;
        }

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
    public AbstractEnemy createEnemy(Position position) {
        // Randomly create different enemy types
        // 60% Fast, 30% Tank, 10% Boss
        double random = Math.random();
        if(random < 0.6) {
            return new cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.FastEnemy(position);
        } else if(random < 0.9) {
            return new cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.TankEnemy(position);
        } else {
            return new cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.BossEnemy(position);
        }
    }
}
