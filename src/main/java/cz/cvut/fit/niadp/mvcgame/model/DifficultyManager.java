package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.strategy.difficulty.EasyDifficultyStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.difficulty.ExpertDifficultyStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.difficulty.HardDifficultyStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.difficulty.IDifficultyStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.difficulty.MediumDifficultyStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.difficulty.NightmareDifficultyStrategy;

/**
 * Manages game difficulty based on player score.
 * Uses Strategy pattern to switch between difficulty levels.
 */
public class DifficultyManager {

    private static final int SCORE_PER_LEVEL = 50;

    private IDifficultyStrategy currentStrategy;
    private int currentLevel;

    // Pre-instantiate strategies for reuse
    private final IDifficultyStrategy easyStrategy = new EasyDifficultyStrategy();
    private final IDifficultyStrategy mediumStrategy = new MediumDifficultyStrategy();
    private final IDifficultyStrategy hardStrategy = new HardDifficultyStrategy();
    private final IDifficultyStrategy expertStrategy = new ExpertDifficultyStrategy();
    private final IDifficultyStrategy nightmareStrategy = new NightmareDifficultyStrategy();

    public DifficultyManager() {
        currentStrategy = easyStrategy;
        currentLevel = 1;
    }

    /**
     * Update difficulty based on current score.
     *
     * @param score current player score
     */
    public void updateDifficulty(int score) {
        int newLevel = calculateLevel(score);

        // Only update if level changed
        if (newLevel != currentLevel) {
            currentLevel = newLevel;
            currentStrategy = getStrategyForLevel(currentLevel);
        }
    }

    /**
     * Calculate difficulty level from score.
     * Level 1 = 0-49 points, Level 2 = 50-99 points, etc.
     *
     * @param score current player score
     * @return difficulty level (1-based)
     */
    private int calculateLevel(int score) {
        return (score / SCORE_PER_LEVEL) + 1;
    }

    /**
     * Get the appropriate strategy for a given level.
     *
     * @param level difficulty level
     * @return difficulty strategy
     */
    private IDifficultyStrategy getStrategyForLevel(int level) {
        if (level <= 2) {
            return easyStrategy;
        } else if (level <= 4) {
            return mediumStrategy;
        } else if (level <= 6) {
            return hardStrategy;
        } else if (level <= 9) {
            return expertStrategy;
        } else {
            return nightmareStrategy;
        }
    }

    /**
     * Get the current difficulty strategy.
     *
     * @return current difficulty strategy
     */
    public IDifficultyStrategy getCurrentStrategy() {
        return currentStrategy;
    }

    /**
     * Get the current difficulty level.
     *
     * @return current level (1-based)
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Reset difficulty to initial state.
     */
    public void reset() {
        currentStrategy = easyStrategy;
        currentLevel = 1;
    }
}
