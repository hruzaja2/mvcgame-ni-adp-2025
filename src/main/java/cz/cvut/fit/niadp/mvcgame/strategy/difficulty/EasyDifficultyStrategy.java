package cz.cvut.fit.niadp.mvcgame.strategy.difficulty;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;

/**
 * Easy difficulty - slowest enemy spawn rate.
 * Suitable for levels 1-2 (0-99 score).
 */
public class EasyDifficultyStrategy implements IDifficultyStrategy {

    @Override
    public int getSpawnInterval() {
        return 60; // Spawn every 60 frames (1 second at 60 FPS)
    }

    @Override
    public String getName() {
        return MvcGameConfig.DIFFICULTY_EASY;
    }
}
