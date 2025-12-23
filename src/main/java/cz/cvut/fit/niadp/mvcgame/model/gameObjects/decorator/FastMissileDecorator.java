package cz.cvut.fit.niadp.mvcgame.model.gameObjects.decorator;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;

public class FastMissileDecorator extends AbstractMissileDecorator {

    public FastMissileDecorator(AbstractMissile missile) {
        super(missile);
    }

    @Override
    public void move() {
        // Move twice for double speed
        decoratedMissile.move();
        decoratedMissile.move();
    }
}
