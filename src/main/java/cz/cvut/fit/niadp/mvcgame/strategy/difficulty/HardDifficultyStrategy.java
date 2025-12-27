package cz.cvut.fit.niadp.mvcgame.strategy.difficulty;

/**
 * Hard difficulty - fast enemy spawn rate.
 * Suitable for levels 5-6 (200-299 score).
 */
public class HardDifficultyStrategy implements IDifficultyStrategy {

    @Override
    public int getSpawnInterval() {
        return 30; // Spawn every 30 frames (0.5 seconds at 60 FPS)
    }

    @Override
    public String getName() {
        return "Hard";
    }
}
