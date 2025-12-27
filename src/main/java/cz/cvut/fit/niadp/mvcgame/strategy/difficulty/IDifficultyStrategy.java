package cz.cvut.fit.niadp.mvcgame.strategy.difficulty;

/**
 * Strategy interface for game difficulty levels.
 * Each strategy defines how frequently enemies spawn.
 */
public interface IDifficultyStrategy {

    /**
     * Get the spawn interval in frames.
     * Lower values mean more frequent spawns (harder difficulty).
     *
     * @return number of frames between enemy spawns
     */
    int getSpawnInterval();

    /**
     * Get the name of this difficulty level.
     *
     * @return difficulty level name
     */
    String getName();
}
