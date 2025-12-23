package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbstractEnemy extends GameObject {

    protected int health;
    protected int speed;

    protected AbstractEnemy(Position position, int health, int speed) {
        this.position = position;
        this.health = health;
        this.speed = speed;
    }

    public abstract void move();

    public void hit() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}