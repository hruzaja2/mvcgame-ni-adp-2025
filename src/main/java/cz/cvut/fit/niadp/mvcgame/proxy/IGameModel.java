package cz.cvut.fit.niadp.mvcgame.proxy;

import java.util.Set;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.observer.IObservable;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RandomMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

public interface IGameModel extends IObservable {
    public void update();
    public Position getCannonPosition();
    public void moveCannonUp();
    public void moveCannonDown();
    public void cannonShoot();
    public void aimCannonUp();
    public void aimCannonDown();
    public void cannonPowerUp();
    public void cannonPowerDown();
    public Set<AbstractMissile> getMissiles();
    public Set<GameObject> getGameObjects();
    public IMovingStrategy getMovingStrategy();
    public void toggleMovingStrategy();
    public IMovingStrategy getNextMovingStrategy(SimpleMovingStrategy strategy);
    public IMovingStrategy getNextMovingStrategy(RandomMovingStrategy strategy);
    public IMovingStrategy getNextMovingStrategy(RealMovingStrategy strategy);
    public void toggleShootingMode();
    public Object createMemento();
    public void setMemento(Object memento);
    public void registerCommand(AbstractGameCommand command);
    public void undoLastCommand();
}
