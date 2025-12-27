package cz.cvut.fit.niadp.mvcgame.model;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameInfo;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.pool.MissilePool;
import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RandomMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

public class GameModel implements IGameModel{
    private final AbstractCannon cannon;
    private final Set<AbstractMissile> missiles;
    private final Set<AbstractEnemy> enemies;
    private final Set<IObserver> observers;
    private final GameInfo gameInfo;
    private IGameObjectsFactory factory;
    private final MissilePool missilePool;
    protected IMovingStrategy movingStrategy;
    protected final Queue<AbstractGameCommand> unexecutedCommands;
    protected final Stack<AbstractGameCommand> executedCommands;
    private int frameCount = 0;

    // Power-ups
    private boolean explosiveMissiles = false;
    private boolean fastMissiles = false;
    private boolean piercingMissiles = false;

    // Help
    private boolean showHelp = false;

    // Pause
    private boolean isPaused = false;

    public GameModel(){
        movingStrategy = new RealMovingStrategy();
        missilePool = new MissilePool(MvcGameConfig.MAX_MISSILES, movingStrategy);
        factory = new GameObjectsFactoryA(this, missilePool);
        observers = new HashSet<IObserver>();
        cannon = factory.createCannon();
        missiles = new HashSet<AbstractMissile>();
        enemies = new HashSet<AbstractEnemy>();
        gameInfo = new GameInfo(this);
        unexecutedCommands = new LinkedBlockingQueue<>();
        executedCommands = new Stack<>();
    }

    public void update() {
        // Execute commands first (allows restart and pause commands to work)
        executeCommands();

        // Don't update game if game over or paused
        if(gameInfo.isGameOver() || isPaused){
            return;
        }

        frameCount++;
        moveMissiles();
        moveEnemies();
        spawnEnemies();
        checkCollisions();
    }

    private void executeCommands(){
        while (!unexecutedCommands.isEmpty()) {
            executedCommands.push(unexecutedCommands.poll().doExecute());
        }
    }

    protected void moveMissiles(){
        missiles.forEach(missile -> missile.move());
        destroyMissiles();
        notifyObservers();
    }

    protected void destroyMissiles(){
        var missilesToRemove = missiles.stream().filter(missile ->
            missile.getPosition().getX() > MvcGameConfig.MAX_X
            || missile.getPosition().getX() < MvcGameConfig.MIN_X
            || missile.getPosition().getY() > MvcGameConfig.MAX_Y
            || missile.getPosition().getY() < MvcGameConfig.MIN_Y
        ).toList();

        // Return missiles to pool (unwrap decorators first)
        missilesToRemove.forEach(missile -> missilePool.release(missile.unwrap()));

        missiles.removeAll(missilesToRemove);
    }

    protected void moveEnemies(){
        enemies.forEach(enemy -> enemy.move());
        destroyEnemies();
        notifyObservers();
    }

    protected void destroyEnemies(){
        var enemiesToRemove = enemies.stream().filter(enemy ->
            enemy.getPosition().getX() < MvcGameConfig.MIN_X
            || !enemy.isAlive()
        ).toList();

        // Add score for killed enemies (using their scoreValue)
        int scoreGained = enemiesToRemove.stream()
            .filter(enemy -> !enemy.isAlive())
            .mapToInt(AbstractEnemy::getScoreValue)
            .sum();

        if(scoreGained > 0){
            gameInfo.incrementScore(scoreGained);
        }

        // Decrease lives for enemies that reached the left edge (escaped)
        long escapedEnemies = enemiesToRemove.stream()
            .filter(enemy -> enemy.getPosition().getX() < MvcGameConfig.MIN_X && enemy.isAlive())
            .count();

        for(int i = 0; i < escapedEnemies; i++){
            gameInfo.decrementLives();
        }

        enemies.removeAll(enemiesToRemove);
    }

    protected void spawnEnemies(){
        // Don't spawn enemies if game is over
        if(gameInfo.isGameOver()){
            return;
        }

        // Spawn enemy every 60 frames (roughly 1 second at 60 FPS)
        if(frameCount % 60 == 0){
            // Random Y position in the middle area of the screen
            int randomY = MvcGameConfig.MIN_Y + 100 + (int)(Math.random() * (MvcGameConfig.MAX_Y - 200));
            Position spawnPosition = new Position(MvcGameConfig.MAX_X, randomY);
            enemies.add(factory.createEnemy(spawnPosition));
        }
    }

    public boolean isGameOver(){
        return gameInfo.isGameOver();
    }

    public void restartGame(){
        // Clear all game objects
        missiles.clear();
        enemies.clear();

        // Reset game info
        gameInfo.setScore(0);
        gameInfo.setLives(MvcGameConfig.INIT_LIVES);

        // Reset cannon position and settings
        cannon.getPosition().setX(MvcGameConfig.CANNON_POS_X);
        cannon.getPosition().setY(MvcGameConfig.CANNON_POS_Y);
        cannon.setAngle(MvcGameConfig.INIT_ANGLE);
        cannon.setPower(MvcGameConfig.INIT_POWER);

        // Reset power-ups
        explosiveMissiles = false;
        fastMissiles = false;
        piercingMissiles = false;

        // Reset frame count
        frameCount = 0;

        // Clear command history
        executedCommands.clear();
        unexecutedCommands.clear();

        notifyObservers();
    }

    protected void checkCollisions(){
        Set<AbstractMissile> missilesToRemove = new HashSet<>();

        for(AbstractMissile missile : missiles){
            boolean hitSomething = false;
            for(AbstractEnemy enemy : enemies){
                if(isColliding(missile, enemy)){
                    enemy.hit();
                    hitSomething = true;
                    if(missile.shouldDestroyOnHit()){
                        missilesToRemove.add(missile);
                        break; // Non-piercing missile stops at first hit
                    }
                    // Piercing missiles continue to next enemy
                }
            }
        }

        // Return missiles to pool (unwrap decorators first)
        missilesToRemove.forEach(missile -> missilePool.release(missile.unwrap()));

        missiles.removeAll(missilesToRemove);
        if(!missilesToRemove.isEmpty()){
            notifyObservers();
        }
    }

    protected boolean isColliding(AbstractMissile missile, AbstractEnemy enemy){
        int collisionRadius = missile.getCollisionRadius();

        int dx = missile.getPosition().getX() - enemy.getPosition().getX();
        int dy = missile.getPosition().getY() - enemy.getPosition().getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        return distance < collisionRadius;
    }

    public Position getCannonPosition(){
        return cannon.getPosition();
    }

    public void moveCannonUp(){
        if(gameInfo.isGameOver() || isPaused) return;
        cannon.moveUp();
        notifyObservers();
    }

    public void moveCannonDown(){
        if(gameInfo.isGameOver() || isPaused) return;
        cannon.moveDown();
        notifyObservers();
    }

    public void cannonShoot(){
        if(gameInfo.isGameOver() || isPaused) return;
        // No need to check missile limit - pool handles it automatically
        var newMissiles = cannon.shoot();
        // Filter out nulls (in case pool is empty)
        newMissiles.stream()
            .filter(missile -> missile != null)
            .forEach(missiles::add);
        if(!newMissiles.isEmpty()){
            notifyObservers();
        }
    }

    public void aimCannonUp() {
        if(gameInfo.isGameOver() || isPaused) return;
        cannon.aimUp();
        notifyObservers();
    }
    public void aimCannonDown() {
        if(gameInfo.isGameOver() || isPaused) return;
        cannon.aimDown();
        notifyObservers();
    }
    public void cannonPowerUp() {
        if(gameInfo.isGameOver() || isPaused) return;
        cannon.powerUp();
        notifyObservers();
    }
    public void cannonPowerDown() {
        if(gameInfo.isGameOver() || isPaused) return;
        cannon.powerDown();
        notifyObservers();
    }

    public Set<AbstractMissile> getMissiles(){
        return missiles;
    }

    public Set<GameObject> getGameObjects(){
        var gameObjects = new HashSet<GameObject>();
        gameObjects.addAll(missiles);
        gameObjects.addAll(enemies);
        gameObjects.add(cannon);
        gameObjects.add(gameInfo);
        return gameObjects;
    }

    public AbstractCannon getCannon(){
        return cannon;
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
        if(gameInfo.isGameOver() || isPaused) return;
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
        if(gameInfo.isGameOver() || isPaused) return;
        cannon.toggleShootingMode();
    }

    public void toggleExplosiveMissiles(){
        if(gameInfo.isGameOver() || isPaused) return;
        explosiveMissiles = !explosiveMissiles;
    }

    public void toggleFastMissiles(){
        if(gameInfo.isGameOver() || isPaused) return;
        fastMissiles = !fastMissiles;
    }

    public void togglePiercingMissiles(){
        if(gameInfo.isGameOver() || isPaused) return;
        piercingMissiles = !piercingMissiles;
    }

    public boolean isExplosiveMissiles(){
        return explosiveMissiles;
    }

    public boolean isFastMissiles(){
        return fastMissiles;
    }

    public boolean isPiercingMissiles(){
        return piercingMissiles;
    }

    public void toggleHelp(){
        showHelp = !showHelp;
    }

    public boolean isShowHelp(){
        return showHelp;
    }

    public void togglePause(){
        isPaused = !isPaused;
    }

    public boolean isPaused(){
        return isPaused;
    }

    private static class Memento{
        private int cannonPositionX;
        private int cannonPositionY;
        private double cannonAngle;
        private int cannonPower;
        private IShootingMode shootingMode;
        private IMovingStrategy movingStrategy;
        private int score;
        private int lives;
    }

    public Object createMemento(){
        Memento gameModelSnapshot = new Memento();
        gameModelSnapshot.cannonPositionX = cannon.getPosition().getX();
        gameModelSnapshot.cannonPositionY = cannon.getPosition().getY();
        gameModelSnapshot.cannonAngle = cannon.getAngle();
        gameModelSnapshot.cannonPower = cannon.getPower();
        gameModelSnapshot.shootingMode = cannon.getShootingMode();
        gameModelSnapshot.movingStrategy = movingStrategy;
        gameModelSnapshot.score = gameInfo.getScore();
        // Don't save lives in command memento - lives loss should be permanent
        // gameModelSnapshot.lives = gameInfo.getLives();
        return gameModelSnapshot;
    }

    public void setMemento(Object memento){
        Memento gameModelSnapshot = (Memento)memento;
        cannon.getPosition().setX(gameModelSnapshot.cannonPositionX);
        cannon.getPosition().setY(gameModelSnapshot.cannonPositionY);
        cannon.setAngle(gameModelSnapshot.cannonAngle);
        cannon.setPower(gameModelSnapshot.cannonPower);
        cannon.setShootingMode(gameModelSnapshot.shootingMode);
        movingStrategy = gameModelSnapshot.movingStrategy;
        gameInfo.setScore(gameModelSnapshot.score);
        // Don't restore lives from command memento - lives loss should be permanent
        // gameInfo.setLives(gameModelSnapshot.lives);
    }

    @Override
    public void registerCommand(AbstractGameCommand command) {
        unexecutedCommands.add(command);
    }

    @Override
    public void undoLastCommand() {
        if(gameInfo.isGameOver() || isPaused) return;
        if(!executedCommands.isEmpty()){
            executedCommands.pop().unExecute();
            notifyObservers();
        }
    }
}
