package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import cz.cvut.fit.niadp.mvcgame.model.Position;

public abstract class LifetimeLimitedGameObject extends GameObject {

    private LocalDateTime bornAt;

    protected LifetimeLimitedGameObject(Position initPosition) {
        position = initPosition;
        bornAt = LocalDateTime.now();
    }
    public long getAge() {
        return ChronoUnit.MILLIS.between(bornAt, LocalDateTime.now());
    }

    /**
     * Resets the birth time for Object Pool pattern reuse
     */
    protected void resetAge() {
        bornAt = LocalDateTime.now();
    }

}
