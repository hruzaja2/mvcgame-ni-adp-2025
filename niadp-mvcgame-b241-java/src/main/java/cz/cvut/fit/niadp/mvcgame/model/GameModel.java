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
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RandomMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

public class GameModel implements IObservable{
    private final AbstractCannon cannon;
    private final Set<AbstractMissile> missiles;
    private final Set<IObserver> observers;
    private IGameObjectsFactory factory;
    protected IMovingStrategy movingStrategy;

    public GameModel(){
        factory = new GameObjectsFactoryA(this);
        observers = new HashSet<IObserver>();
        cannon = factory.createCannon();
        missiles = new HashSet<AbstractMissile>();
        movingStrategy = new RealMovingStrategy();
    }

    public void update() {
        // remove killed enemies
        moveMissiles();
        // check for collisions
    }

    protected void moveMissiles(){
        missiles.forEach(missile -> missile.move());
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
        missiles.addAll(cannon.shoot());
        notifyObservers();
    }

    public void aimCannonUp() {
        cannon.aimUp();
        notifyObservers();
    }
    public void aimCannonDown() {
        cannon.aimDown();
        notifyObservers();
    }
    public void cannonPowerUp() {
        cannon.powerUp();
        notifyObservers();
    }
    public void cannonPowerDown() {
        cannon.powerDown();
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

    public IMovingStrategy getMovingStrategy(){
        return movingStrategy;
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

    public void toggleMovingStrategy(){
        /*if(movingStrategy instanceof SimpleMovingStrategy){
            movingStrategy = new RandomMovingStrategy();
        }else if(movingStrategy instanceof RandomMovingStrategy){
            movingStrategy = new RealMovingStrategy();
        }else if( movingStrategy instanceof RealMovingStrategy){
            movingStrategy = new SimpleMovingStrategy();
        }else{

        }*/
        movingStrategy = movingStrategy.getNextStrategy(this);

    }


    public IMovingStrategy getNextMovingStrategy(SimpleMovingStrategy strategy){
        return new RandomMovingStrategy();
    }

    public IMovingStrategy getNextMovingStrategy(RandomMovingStrategy strategy){
        return new RealMovingStrategy();
    }

    public IMovingStrategy getNextMovingStrategy(RealMovingStrategy strategy){
        return new SimpleMovingStrategy();
    }

    public void toggleShootingMode(){
        cannon.toggleShootingMode();
    }
}
