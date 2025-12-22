package cz.cvut.fit.niadp.mvcgame.proxy;

import java.util.Set;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
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
}
