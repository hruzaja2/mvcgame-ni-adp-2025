package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public abstract class AbstractMissile extends LifetimeLimitedGameObject {

    private double initAngle;
    private int initVelocity;
    protected IMovingStrategy movingStrategy;

    protected AbstractMissile(Position initPosition, double initAngle, int initVelocity, IMovingStrategy movingStrategy) {
        super(initPosition);
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
        this.movingStrategy = movingStrategy;
    }

    /**
     * Resets missile state for Object Pool pattern reuse
     */
    public void reset(Position newPosition, double newAngle, int newVelocity, IMovingStrategy newStrategy) {
        this.position.setX(newPosition.getX());
        this.position.setY(newPosition.getY());
        this.initAngle = newAngle;
        this.initVelocity = newVelocity;
        this.movingStrategy = newStrategy;
        this.resetAge(); // Reset birth time for RealMovingStrategy
    }

    public void accept(IVisitor visitor){
        visitor.visit(this);
    }

    public double getInitAngle() {
        return initAngle;
    }
    public int getInitVelocity() {
        return initVelocity;
    }

    public IMovingStrategy getMovingStrategy() {
        return movingStrategy;
    }

    public void move(){
        movingStrategy.updatePosition(this);
    }

    public int getCollisionRadius(){
        return 30; // Default collision radius
    }

    public boolean shouldDestroyOnHit(){
        return true; // Default: destroy on hit
    }

    /**
     * Unwraps decorated missiles to get the base missile for Object Pool
     * Override in decorators to unwrap
     */
    public AbstractMissile unwrap() {
        return this; // Base missiles return themselves
    }
}
