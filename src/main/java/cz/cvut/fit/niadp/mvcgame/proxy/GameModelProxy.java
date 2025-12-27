package cz.cvut.fit.niadp.mvcgame.proxy;

import java.util.Set;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RandomMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

public class GameModelProxy implements IGameModel{
    private final GameModel subject;

    public GameModelProxy(GameModel subject){
        this.subject = subject;
    }

    @Override
    public void registerObserver(IObserver observer) {
        subject.registerObserver(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        subject.unregisterObserver(observer);
    }

    @Override
    public void notifyObservers() {
        subject.notifyObservers();
    }

    @Override
    public void update() {
        subject.update();
    }

    @Override
    public Position getCannonPosition() {
        return subject.getCannonPosition();
     }

    @Override
    public void moveCannonUp() {
        subject.moveCannonUp();
    }

    @Override
    public void moveCannonDown() {
        subject.moveCannonDown();
    }

    @Override
    public void cannonShoot() {
        subject.cannonShoot();
    }

    @Override
    public void aimCannonUp() {
        subject.aimCannonUp();
    }

    @Override
    public void aimCannonDown() {
        subject.aimCannonDown();
    }

    @Override
    public void cannonPowerUp() {
        subject.cannonPowerUp();
    }

    @Override
    public void cannonPowerDown() {
        subject.cannonPowerDown();
    }

    @Override
    public Set<AbstractMissile> getMissiles() {
        return subject.getMissiles();
    }

    @Override
    public Set<GameObject> getGameObjects() {
        return subject.getGameObjects();
    }

    @Override
    public AbstractCannon getCannon() {
        return subject.getCannon();
    }

    @Override
    public IMovingStrategy getMovingStrategy() {
        return subject.getMovingStrategy();
    }

    @Override
    public void toggleMovingStrategy() {
        subject.toggleMovingStrategy();
    }

    @Override
    public IMovingStrategy getNextMovingStrategy(SimpleMovingStrategy strategy) {
        return subject.getNextMovingStrategy(strategy);
    }

    @Override
    public IMovingStrategy getNextMovingStrategy(RandomMovingStrategy strategy) {
        return subject.getNextMovingStrategy(strategy);
    }

    @Override
    public IMovingStrategy getNextMovingStrategy(RealMovingStrategy strategy) {
        return subject.getNextMovingStrategy(strategy);
    }

    @Override
    public void toggleShootingMode() {
        subject.toggleShootingMode();
    }

    @Override
    public void toggleExplosiveMissiles() {
        subject.toggleExplosiveMissiles();
    }

    @Override
    public void toggleFastMissiles() {
        subject.toggleFastMissiles();
    }

    @Override
    public void togglePiercingMissiles() {
        subject.togglePiercingMissiles();
    }

    @Override
    public boolean isExplosiveMissiles() {
        return subject.isExplosiveMissiles();
    }

    @Override
    public boolean isFastMissiles() {
        return subject.isFastMissiles();
    }

    @Override
    public boolean isPiercingMissiles() {
        return subject.isPiercingMissiles();
    }

    @Override
    public void toggleHelp() {
        subject.toggleHelp();
    }

    @Override
    public boolean isShowHelp() {
        return subject.isShowHelp();
    }

    @Override
    public Object createMemento() {
       return subject.createMemento();
    }

    @Override
    public void setMemento(Object memento) {
       subject.setMemento(memento);
    }

    @Override
    public void registerCommand(AbstractGameCommand command) {
        subject.registerCommand(command);
    }

    @Override
    public void undoLastCommand() {
        subject.undoLastCommand();
    }

    @Override
    public boolean isGameOver() {
        return subject.isGameOver();
    }

    @Override
    public void restartGame() {
        subject.restartGame();
    }

    @Override
    public void togglePause() {
        subject.togglePause();
    }

    @Override
    public boolean isPaused() {
        return subject.isPaused();
    }

    @Override
    public void saveGameToFile(String filename) {
        subject.saveGameToFile(filename);
    }

    @Override
    public void loadGameFromFile(String filename) {
        subject.loadGameFromFile(filename);
    }

    @Override
    public int getDifficultyLevel() {
        return subject.getDifficultyLevel();
    }
}
