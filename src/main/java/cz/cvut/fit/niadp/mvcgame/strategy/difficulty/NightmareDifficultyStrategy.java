package cz.cvut.fit.niadp.mvcgame.strategy.difficulty;

/**
 * Nightmare difficulty - extremely fast enemy spawn rate.
 * Suitable for levels 10+ (450+ score).
 */
public class NightmareDifficultyStrategy implements IDifficultyStrategy {

    @Override
    public int getSpawnInterval() {
        return 15; // Spawn every 15 frames (0.25 seconds at 60 FPS)
    }

    @Override
    public String getName() {
        return "Nightmare";
    }
}
