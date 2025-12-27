package cz.cvut.fit.niadp.mvcgame.pool;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.MissileA;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Object Pool pattern for missile reuse
 * Pre-creates missiles and reuses them instead of constant creation/destruction
 */
public class MissilePool {
    private final List<AbstractMissile> availableMissiles;
    private final int poolSize;

    public MissilePool(int size, IMovingStrategy defaultStrategy) {
        this.poolSize = size;
        this.availableMissiles = new ArrayList<>(size);

        // Pre-create all missiles
        for (int i = 0; i < size; i++) {
            availableMissiles.add(new MissileA(
                new Position(0, 0),
                0,
                0,
                defaultStrategy
            ));
        }
    }

    public AbstractMissile acquire(Position position, double angle, int velocity, IMovingStrategy strategy) {
        if (availableMissiles.isEmpty()) {
            return null; // No missiles available
        }

        AbstractMissile missile = availableMissiles.remove(availableMissiles.size() - 1);
        missile.reset(position, angle, velocity, strategy);
        return missile;
    }

    public void release(AbstractMissile missile) {
        if (availableMissiles.size() < poolSize) {
            availableMissiles.add(missile);
        }
    }

    public int getAvailableCount() {
        return availableMissiles.size();
    }

    public int getInUseCount() {
        return poolSize - availableMissiles.size();
    }
}
