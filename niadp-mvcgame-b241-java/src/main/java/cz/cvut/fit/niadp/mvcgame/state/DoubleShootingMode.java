package cz.cvut.fit.niadp.mvcgame.state;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractCannon;

public class DoubleShootingMode implements IShootingMode {

    @Override
    public String getName() {
        return "DoubleShootingMode";
    }

    @Override
    public void shoot(AbstractCannon cannon) {
        cannon.aimUp();
        cannon.primitiveShoot();
        cannon.aimDown();
        cannon.aimDown();
        cannon.primitiveShoot();
        cannon.aimUp();
    }

}
