package cz.cvut.fit.niadp.mvcgame.model;

import org.junit.Assert;
import org.junit.Test;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

public class GameModelMockedTest {
    private static final double INIT_ANGLE = 0;
    private static final int INIT_VELOCITY = 0;
    private static final int CANNON_POS_X = 123;
    private static final int CANNON_POS_Y = 456;

    @Mocked
    private GameModel model;
    @Test
    public void createMissileTest() {
        generalMocks();
        IGameObjectsFactory factory = new GameObjectsFactoryA(model);
        AbstractMissile missile = factory.createMissile(INIT_ANGLE, INIT_VELOCITY);
        Assert.assertEquals(CANNON_POS_X, missile.getPosition().getX());
        Assert.assertEquals(CANNON_POS_Y, missile.getPosition().getY());
    }
    private void generalMocks() {
        new MockUp<GameModel>() {
            @Mock
            public Position getCannonPosition() {
                return new Position(CANNON_POS_X, CANNON_POS_Y);
            }
        };
    }

}
