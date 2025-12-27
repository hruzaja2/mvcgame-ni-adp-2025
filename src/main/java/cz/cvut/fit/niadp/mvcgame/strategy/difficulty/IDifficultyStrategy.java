package cz.cvut.fit.niadp.mvcgame.strategy.difficulty;

/**
 * Strategy interface for game difficulty levels.
 * Each strategy defines how frequently enemies spawn.
 */
public interface IDifficultyStrategy {

    int getSpawnInterval();


    String getName();
}
