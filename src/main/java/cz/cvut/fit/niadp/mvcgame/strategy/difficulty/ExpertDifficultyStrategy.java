package cz.cvut.fit.niadp.mvcgame.strategy.difficulty;

/**
 * Expert difficulty - very fast enemy spawn rate.
 * Suitable for levels 7-9 (300-449 score).
 */
public class ExpertDifficultyStrategy implements IDifficultyStrategy {

    @Override
    public int getSpawnInterval() {
        return 20; // Spawn every 20 frames (~0.33 seconds at 60 FPS)
    }

    @Override
    public String getName() {
        return "Expert";
    }
}
