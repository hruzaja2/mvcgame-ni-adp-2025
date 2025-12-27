package cz.cvut.fit.niadp.mvcgame.state;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractCannon;

public class DoubleShootingMode implements IShootingMode {

    private static final int VERTICAL_OFFSET = MvcGameConfig.MOVE_STEP * 2;

    @Override
    public String getName() {
        return "DoubleShootingMode";
    }

    @Override
    public void shoot(AbstractCannon cannon) {
        // Shoot first missile slightly above
        cannon.move(new Vector(0, -VERTICAL_OFFSET));
        cannon.primitiveShoot();

        // Shoot second missile slightly below
        cannon.move(new Vector(0, 2 * VERTICAL_OFFSET));
        cannon.primitiveShoot();

        // Restore original position
        cannon.move(new Vector(0, -VERTICAL_OFFSET));
    }

}
