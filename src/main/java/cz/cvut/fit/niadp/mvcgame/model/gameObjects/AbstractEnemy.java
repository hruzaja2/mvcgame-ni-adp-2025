package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbstractEnemy extends GameObject {

    protected int health;
    protected int speed;
    protected int scoreValue;

    protected AbstractEnemy(Position position, int health, int speed, int scoreValue) {
        this.position = position;
        this.health = health;
        this.speed = speed;
        this.scoreValue = scoreValue;
    }

    // Template Method - defines the skeleton of enemy behavior
    public final void move() {
        beforeMove();
        performMove();
        afterMove();
    }

    // Hook methods - subclasses can override these
    protected void beforeMove() {
        // Default: do nothing
    }

    protected abstract void performMove();

    protected void afterMove() {
        // Default: do nothing
    }

    public void hit() {
        health--;
        onHit();
    }

    protected void onHit() {
        // Hook method - subclasses can override
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}