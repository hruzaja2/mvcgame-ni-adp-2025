package cz.cvut.fit.niadp.mvcgame.model.gameObjects.decorator;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public abstract class AbstractMissileDecorator extends AbstractMissile {

    protected AbstractMissile decoratedMissile;

    protected AbstractMissileDecorator(AbstractMissile missile) {
        super(missile.getPosition(), missile.getInitAngle(), missile.getInitVelocity(), missile.getMovingStrategy());
        this.decoratedMissile = missile;
    }

    @Override
    public Position getPosition() {
        return decoratedMissile.getPosition();
    }

    @Override
    public void move() {
        decoratedMissile.move();
    }

    @Override
    public int getCollisionRadius() {
        return decoratedMissile.getCollisionRadius();
    }

    @Override
    public boolean shouldDestroyOnHit() {
        return decoratedMissile.shouldDestroyOnHit();
    }

    @Override
    public AbstractMissile unwrap() {
        return decoratedMissile.unwrap(); // Recursively unwrap all decorators
    }
}
