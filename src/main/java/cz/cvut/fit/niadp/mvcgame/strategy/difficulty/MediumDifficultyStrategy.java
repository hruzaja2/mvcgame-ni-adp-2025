package cz.cvut.fit.niadp.mvcgame.strategy.difficulty;

/**
 * Medium difficulty - moderate enemy spawn rate.
 * Suitable for levels 3-4 (100-199 score).
 */
public class MediumDifficultyStrategy implements IDifficultyStrategy {

    @Override
    public int getSpawnInterval() {
        return 45; // Spawn every 45 frames (0.75 seconds at 60 FPS)
    }

    @Override
    public String getName() {
        return "Medium";
    }
}
