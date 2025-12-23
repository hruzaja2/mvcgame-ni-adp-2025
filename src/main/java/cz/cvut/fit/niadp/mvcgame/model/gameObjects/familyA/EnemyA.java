package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractEnemy;

public class EnemyA extends AbstractEnemy {

    public EnemyA(Position position, int health, int speed) {
        super(position, health, speed);
    }

    @Override
    public void move() {
        // Nepřítel se pohybuje DOLEVA (vpravo → vlevo)
        move(new Vector(-speed, 0));  // Negativní X = doleva
    }
}