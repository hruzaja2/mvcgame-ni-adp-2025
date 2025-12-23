package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractEnemy;

public class FastEnemy extends AbstractEnemy {

    public FastEnemy(Position position) {
        super(position, 1, 4, 1); // Low HP, high speed, low score
    }

    @Override
    protected void performMove() {
        // Fast enemy moves quickly to the left
        move(new Vector(-speed, 0));
    }
}
