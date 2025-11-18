package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public abstract class AbstractMissile extends LifetimeLimitedGameObject {

    private final double initAngle;
    private final int initVelocity;
    protected IMovingStrategy movingStrategy;

    protected AbstractMissile(Position initPosition, double initAngle, int initVelocity, IMovingStrategy movingStrategy) {
        super(initPosition);
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
        this.movingStrategy = movingStrategy;
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

    public void move(){
        movingStrategy.updatePosition(this);
    }
}
