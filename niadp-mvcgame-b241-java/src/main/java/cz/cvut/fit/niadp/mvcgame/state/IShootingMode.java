package cz.cvut.fit.niadp.mvcgame.state;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractCannon;

public interface IShootingMode {
    String getName();
    void shoot(AbstractCannon cannon);
}
