package cz.cvut.fit.niadp.mvcgame.pool;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;
import cz.cvut.fit.niadp.mvcgame.strategy.RealMovingStrategy;

/**
 * Test for Object Pool pattern implementation
 * Verifies that missiles are properly reused and recycled
 */
public class MissilePoolTest {

    private static final int POOL_SIZE = 10;
    private MissilePool pool;
    private RealMovingStrategy strategy;

    @Before
    public void setUp() {
        strategy = new RealMovingStrategy();
        pool = new MissilePool(POOL_SIZE, strategy);
    }

    @Test
    public void testPoolInitialization() {
        // Pool should start with all missiles available
        Assert.assertEquals(POOL_SIZE, pool.getAvailableCount());
        Assert.assertEquals(0, pool.getInUseCount());
    }

    @Test
    public void testAcquireMissile() {
        // Acquire one missile
        AbstractMissile missile = pool.acquire(new Position(100, 200), 45.0, 50, strategy);

        Assert.assertNotNull(missile);
        Assert.assertEquals(100, missile.getPosition().getX());
        Assert.assertEquals(200, missile.getPosition().getY());
        Assert.assertEquals(45.0, missile.getInitAngle(), 0.001);
        Assert.assertEquals(50, missile.getInitVelocity());

        // Pool should have one less missile available
        Assert.assertEquals(POOL_SIZE - 1, pool.getAvailableCount());
        Assert.assertEquals(1, pool.getInUseCount());
    }

    @Test
    public void testReleaseMissile() {
        // Acquire and then release a missile
        AbstractMissile missile = pool.acquire(new Position(100, 200), 45.0, 50, strategy);
        Assert.assertEquals(POOL_SIZE - 1, pool.getAvailableCount());

        pool.release(missile);

        // Pool should be back to full
        Assert.assertEquals(POOL_SIZE, pool.getAvailableCount());
        Assert.assertEquals(0, pool.getInUseCount());
    }

    @Test
    public void testMissileReuse() {
        // Acquire a missile
        AbstractMissile missile1 = pool.acquire(new Position(100, 200), 45.0, 50, strategy);
        Assert.assertNotNull(missile1);

        // Release it back
        pool.release(missile1);

        // Acquire again - should get the same missile object (reused)
        AbstractMissile missile2 = pool.acquire(new Position(300, 400), 90.0, 75, strategy);
        Assert.assertNotNull(missile2);
        Assert.assertSame(missile1, missile2); // Same object reference

        // But with new properties
        Assert.assertEquals(300, missile2.getPosition().getX());
        Assert.assertEquals(400, missile2.getPosition().getY());
        Assert.assertEquals(90.0, missile2.getInitAngle(), 0.001);
        Assert.assertEquals(75, missile2.getInitVelocity());
    }

    @Test
    public void testPoolExhaustion() {
        // Acquire all missiles from pool
        for (int i = 0; i < POOL_SIZE; i++) {
            AbstractMissile missile = pool.acquire(new Position(i, i), 0, 0, strategy);
            Assert.assertNotNull(missile);
        }

        Assert.assertEquals(0, pool.getAvailableCount());
        Assert.assertEquals(POOL_SIZE, pool.getInUseCount());

        // Try to acquire one more - should return null (pool empty)
        AbstractMissile extraMissile = pool.acquire(new Position(0, 0), 0, 0, strategy);
        Assert.assertNull(extraMissile);
    }

    @Test
    public void testMultipleAcquireReleaseCycles() {
        // Simulate multiple game cycles
        for (int cycle = 0; cycle < 5; cycle++) {
            // Acquire half the pool
            AbstractMissile[] missiles = new AbstractMissile[POOL_SIZE / 2];
            for (int i = 0; i < POOL_SIZE / 2; i++) {
                missiles[i] = pool.acquire(new Position(i, i), i, i, strategy);
                Assert.assertNotNull(missiles[i]);
            }

            Assert.assertEquals(POOL_SIZE / 2, pool.getAvailableCount());
            Assert.assertEquals(POOL_SIZE / 2, pool.getInUseCount());

            // Release them all
            for (AbstractMissile missile : missiles) {
                pool.release(missile);
            }

            Assert.assertEquals(POOL_SIZE, pool.getAvailableCount());
            Assert.assertEquals(0, pool.getInUseCount());
        }
    }
}
