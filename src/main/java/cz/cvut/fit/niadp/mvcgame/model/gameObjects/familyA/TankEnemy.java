package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractEnemy;

public class TankEnemy extends AbstractEnemy {

    public TankEnemy(Position position) {
        super(position, 5, 1, 3); // High HP, low speed, medium score
    }

    @Override
    protected void performMove() {
        // Tank enemy moves slowly to the left
        move(new Vector(-speed, 0));
    }

    @Override
    protected void onHit() {
        // Tank makes a small movement when hit (recoil effect)
        move(new Vector(2, 0));
    }
}
