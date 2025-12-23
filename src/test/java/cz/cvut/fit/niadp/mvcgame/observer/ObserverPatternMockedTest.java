package cz.cvut.fit.niadp.mvcgame.observer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Verifications;

/**
 * Test for Observer pattern implementation using mocking
 * Verifies that observers are properly notified on game state changes
 */
public class ObserverPatternMockedTest {

    private GameModel model;

    @Mocked
    private IObserver mockObserver1;

    @Mocked
    private IObserver mockObserver2;

    @Before
    public void setUp() {
        model = new GameModel();
    }

    @Test
    public void testObserverRegistration() {
        // Register observers
        model.registerObserver(mockObserver1);
        model.registerObserver(mockObserver2);

        // Move cannon - should notify observers
        model.moveCannonUp();

        // Verify both observers were notified
        new Verifications() {{
            mockObserver1.update();
            times = 1;

            mockObserver2.update();
            times = 1;
        }};
    }

    @Test
    public void testObserverUnregistration() {
        model.registerObserver(mockObserver1);
        model.registerObserver(mockObserver2);

        // Unregister one observer
        model.unregisterObserver(mockObserver1);

        // Move cannon
        model.moveCannonDown();

        // Verify only observer2 was notified
        new Verifications() {{
            mockObserver1.update();
            times = 0; // Should not be called

            mockObserver2.update();
            times = 1;
        }};
    }

    @Test
    public void testMultipleNotifications() {
        model.registerObserver(mockObserver1);

        // Perform multiple actions that trigger notifications
        model.moveCannonUp();
        model.moveCannonDown();
        model.aimCannonUp();

        // Verify observer was notified 3 times
        new Verifications() {{
            mockObserver1.update();
            times = 3;
        }};
    }

    @Test
    public void testShootNotifiesObservers() {
        model.registerObserver(mockObserver1);

        // Shoot - should notify observer
        model.cannonShoot();

        // Verify observer was notified
        new Verifications() {{
            mockObserver1.update();
            times = 1;
        }};
    }

    @Test
    public void testUpdateNotifiesObservers() {
        // Create a mock to track notification count
        final int[] notificationCount = {0};

        new MockUp<IObserver>() {
            @Mock
            public void update() {
                notificationCount[0]++;
            }
        };

        IObserver countingObserver = new IObserver() {
            @Override
            public void update() {
                notificationCount[0]++;
            }
        };

        model.registerObserver(countingObserver);

        // Shoot to add missiles, then update multiple times
        model.cannonShoot();
        int initialCount = notificationCount[0];

        // Call update (which moves missiles and may trigger notifications)
        model.update();

        // Should have more notifications after update
        Assert.assertTrue(notificationCount[0] > initialCount);
    }

    @Test
    public void testNoObserversNoErrors() {
        // Should not throw exception even with no observers
        model.moveCannonUp();
        model.moveCannonDown();
        model.cannonShoot();
        model.update();

        // Test passes if no exception thrown
        Assert.assertTrue(true);
    }
}
