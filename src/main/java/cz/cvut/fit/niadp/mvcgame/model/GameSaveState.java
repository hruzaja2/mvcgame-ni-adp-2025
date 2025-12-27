package cz.cvut.fit.niadp.mvcgame.model;

/**
 * Represents a complete saveable state of the game.
 * Used for serialization/deserialization to JSON file.
 */
public class GameSaveState {
    // Cannon state
    private int cannonPositionX;
    private int cannonPositionY;
    private double cannonAngle;
    private int cannonPower;
    private String shootingMode; // "Single", "Double"

    // Game info
    private int score;
    private int lives;
    private int frameCount;

    // Power-ups
    private boolean explosiveMissiles;
    private boolean fastMissiles;
    private boolean piercingMissiles;

    // Strategy
    private String movingStrategy; // "Simple", "Random", "Real"

    // Game state
    private boolean isPaused;

    // Constructor for Gson
    public GameSaveState() {
    }

    // Getters and setters
    public int getCannonPositionX() {
        return cannonPositionX;
    }

    public void setCannonPositionX(int cannonPositionX) {
        this.cannonPositionX = cannonPositionX;
    }

    public int getCannonPositionY() {
        return cannonPositionY;
    }

    public void setCannonPositionY(int cannonPositionY) {
        this.cannonPositionY = cannonPositionY;
    }

    public double getCannonAngle() {
        return cannonAngle;
    }

    public void setCannonAngle(double cannonAngle) {
        this.cannonAngle = cannonAngle;
    }

    public int getCannonPower() {
        return cannonPower;
    }

    public void setCannonPower(int cannonPower) {
        this.cannonPower = cannonPower;
    }

    public String getShootingMode() {
        return shootingMode;
    }

    public void setShootingMode(String shootingMode) {
        this.shootingMode = shootingMode;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public boolean isExplosiveMissiles() {
        return explosiveMissiles;
    }

    public void setExplosiveMissiles(boolean explosiveMissiles) {
        this.explosiveMissiles = explosiveMissiles;
    }

    public boolean isFastMissiles() {
        return fastMissiles;
    }

    public void setFastMissiles(boolean fastMissiles) {
        this.fastMissiles = fastMissiles;
    }

    public boolean isPiercingMissiles() {
        return piercingMissiles;
    }

    public void setPiercingMissiles(boolean piercingMissiles) {
        this.piercingMissiles = piercingMissiles;
    }

    public String getMovingStrategy() {
        return movingStrategy;
    }

    public void setMovingStrategy(String movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }
}