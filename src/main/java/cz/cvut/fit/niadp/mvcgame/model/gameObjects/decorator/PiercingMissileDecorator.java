package cz.cvut.fit.niadp.mvcgame.model.gameObjects.decorator;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;

public class PiercingMissileDecorator extends AbstractMissileDecorator {

    public PiercingMissileDecorator(AbstractMissile missile) {
        super(missile);
    }

    @Override
    public boolean shouldDestroyOnHit() {
        return false; // Piercing missiles don't destroy on hit
    }
}
