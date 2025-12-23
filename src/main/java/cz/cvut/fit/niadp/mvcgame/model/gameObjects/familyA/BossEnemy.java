package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractEnemy;

public class BossEnemy extends AbstractEnemy {

    private int movementCounter = 0;

    public BossEnemy(Position position) {
        super(position, 10, 1, 10); // Very high HP, slow speed, high score
    }

    @Override
    protected void performMove() {
        // Boss moves slowly left
        move(new Vector(-speed, 0));

        // Boss also moves up and down in a wave pattern
        movementCounter++;
        int waveAmplitude = 2;
        int waveSpeed = 10;
        int yOffset = (int)(Math.sin(movementCounter / (double)waveSpeed) * waveAmplitude);
        move(new Vector(0, yOffset));
    }

    @Override
    protected void onHit() {
        // Boss shows resistance when hit (moves forward slightly)
        move(new Vector(1, 0));
    }
}
