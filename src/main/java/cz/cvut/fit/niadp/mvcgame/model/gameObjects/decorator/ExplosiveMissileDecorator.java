package cz.cvut.fit.niadp.mvcgame.model.gameObjects.decorator;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;

public class ExplosiveMissileDecorator extends AbstractMissileDecorator {

    public ExplosiveMissileDecorator(AbstractMissile missile) {
        super(missile);
    }

    @Override
    public int getCollisionRadius() {
        return 50; // Larger collision radius for explosive missiles
    }
}
