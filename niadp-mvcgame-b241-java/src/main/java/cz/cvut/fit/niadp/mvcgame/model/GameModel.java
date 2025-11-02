package cz.cvut.fit.niadp.mvcgame.model;

import java.util.HashSet;
import java.util.Set;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.observer.IObservable;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;

public class GameModel implements IObservable{
    private final AbstractCannon cannon;
    private final Set<AbstractMissile> missiles;
    private final Set<IObserver> observers;
    private IGameObjectsFactory factory;

    public GameModel(){
        factory = new GameObjectsFactoryA(this);
        observers = new HashSet<IObserver>();
        cannon = factory.createCannon();
        missiles = new HashSet<AbstractMissile>();
    }

    public void update() {
        // remove killed enemies
        moveMissiles();
        // check for collisions
    }

    protected void moveMissiles(){
        missiles.forEach(missile -> missile.move(new Vector(MvcGameConfig.MOVE_STEP, 0)));
        destroyMissiles();
        notifyObservers();
    }

    protected void destroyMissiles(){
        missiles.removeAll(
            missiles.stream().filter(missile -> missile.getPosition().getX() > MvcGameConfig.MAX_X)
            .toList()
        );
    }

    public Position getCannonPosition(){
        return cannon.getPosition();
    }

    public void moveCannonUp(){
        cannon.moveUp();
        notifyObservers();
    }

    public void moveCannonDown(){
        cannon.moveDown();
        notifyObservers();
    }

    public void cannonShoot(){
        missiles.add(cannon.shoot());
        notifyObservers();
    }

    public Set<AbstractMissile> getMissiles(){
        return missiles;
    }

    public Set<GameObject> getGameObjects(){
        var gameObjects = new HashSet<GameObject>();
        gameObjects.addAll(missiles);
        gameObjects.add(cannon);
        return gameObjects;
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
       observers.forEach(IObserver::update);
    }

}
