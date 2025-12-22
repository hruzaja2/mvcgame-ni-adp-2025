package cz.cvut.fit.niadp.mvcgame.state;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractCannon;

public class SingleShootingMode implements IShootingMode{

    @Override
    public String getName() {
        return "SingleShootingMode";
    }

    @Override
    public void shoot(AbstractCannon cannon) {
        cannon.primitiveShoot();
    }

}
