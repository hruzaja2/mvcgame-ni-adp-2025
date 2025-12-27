package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameResources;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameInfo;
import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;
import cz.cvut.fit.niadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.niadp.mvcgame.strategy.RandomMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

public class GameDrawer implements IVisitor{
    protected IGameGraphics gameGraphics;

    public void setGraphicsContext(IGameGraphics gameGraphics){
        this.gameGraphics = gameGraphics;
    }

    @Override
    public void visit(AbstractCannon cannon) {
        drawGameObject(cannon, MvcGameResources.CANNON_RESOURCE);
    }

    @Override
    public void visit(AbstractMissile missile) {
        drawGameObject(missile, MvcGameResources.MISSILE_RESOURCE);
    }

    @Override
    public void visit(AbstractEnemy enemy) {
        drawGameObject(enemy, MvcGameResources.ENEMY_RESOURCE);
    }

    @Override
    public void visit(GameInfo gameInfo) {
        if(gameGraphics == null) return;

        IGameModel model = gameInfo.getModel();
        AbstractCannon cannon = model.getCannon();

        // Get strategy name
        String strategyName = "Unknown";
        if(model.getMovingStrategy() instanceof SimpleMovingStrategy){
            strategyName = "Simple";
        }else if(model.getMovingStrategy() instanceof RandomMovingStrategy){
            strategyName = "Random";
        }else if(model.getMovingStrategy() instanceof RealMovingStrategy){
            strategyName = "Real";
        }

        // Get shooting mode
        String shootingMode;
        if (cannon.getShootingMode() instanceof DoubleShootingMode) {
            shootingMode = "Double";
        } else {
            shootingMode = "Single";
        }

        // Draw HUD information
        int startY = 20;
        int lineHeight = 20;

        gameGraphics.drawText("Score: " + gameInfo.getScore(), new Position(10, startY));
        gameGraphics.drawText("Lives: " + gameInfo.getLives(), new Position(10, startY + lineHeight));
        gameGraphics.drawText("Difficulty: Level " + model.getDifficultyLevel(), new Position(10, startY + 2 * lineHeight));
        gameGraphics.drawText("Angle: " + String.format("%.1f", cannon.getAngle()), new Position(10, startY + 3 * lineHeight));
        gameGraphics.drawText("Power: " + cannon.getPower(), new Position(10, startY + 4 * lineHeight));
        gameGraphics.drawText("Mode: " + shootingMode, new Position(10, startY + 5 * lineHeight));
        gameGraphics.drawText("Strategy: " + strategyName, new Position(10, startY + 6 * lineHeight));

        // Power-ups display
        String powerups = "Power-ups: ";
        if(model.isExplosiveMissiles()) powerups += "[E]Explosive ";
        if(model.isFastMissiles()) powerups += "[Q]Fast ";
        if(model.isPiercingMissiles()) powerups += "[W]Piercing ";
        if(!model.isExplosiveMissiles() && !model.isFastMissiles() && !model.isPiercingMissiles()) {
            powerups += "None";
        }
        gameGraphics.drawText(powerups, new Position(10, startY + 7 * lineHeight));

        // Pause message
        if(model.isPaused()) {
            int centerX = 960; // Half of MAX_X (1920)
            int centerY = 400;
            gameGraphics.drawText("=== PAUSED ===", new Position(centerX - 80, centerY));
            gameGraphics.drawText("Press P to resume", new Position(centerX - 90, centerY + 40));
        }

        // Game Over message
        if(gameInfo.isGameOver()) {
            int centerX = 960; // Half of MAX_X (1920)
            int centerY = 400;
            gameGraphics.drawText("=== GAME OVER ===", new Position(centerX - 100, centerY));
            gameGraphics.drawText("Final Score: " + gameInfo.getScore(), new Position(centerX - 80, centerY + 40));
            gameGraphics.drawText("Press R to restart", new Position(centerX - 90, centerY + 80));
        }

        // Help text
        if(model.isShowHelp()) {
            int helpX = 600;
            int helpY = 50;
            int helpLineHeight = 25;
            int line = 0;

            gameGraphics.drawText("=== CONTROLS (Press H to hide) ===", new Position(helpX, helpY + line++ * helpLineHeight));
            line++;
            gameGraphics.drawText("Movement:", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  UP/DOWN - Move cannon", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  A/Y - Aim up/down", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  F/D - Power up/down", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  SPACE - Shoot", new Position(helpX, helpY + line++ * helpLineHeight));
            line++;
            gameGraphics.drawText("Game modes:", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  M - Toggle moving strategy", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  N - Toggle shooting mode", new Position(helpX, helpY + line++ * helpLineHeight));
            line++;
            gameGraphics.drawText("Power-ups:", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  E - Explosive missiles", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  Q - Fast missiles", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  W - Piercing missiles", new Position(helpX, helpY + line++ * helpLineHeight));
            line++;
            gameGraphics.drawText("Other:", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  P - Pause/Resume", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  S - Save snapshot (memory)", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  X - Restore snapshot (memory)", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  C - Undo last command", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  F5 - Save to file", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  F9 - Load from file", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  R - Restart game", new Position(helpX, helpY + line++ * helpLineHeight));
            gameGraphics.drawText("  ESC - Exit game", new Position(helpX, helpY + line++ * helpLineHeight));
        } else {
            gameGraphics.drawText("Press H for help", new Position(10, startY + 8 * lineHeight));
        }
    }

    protected void drawGameObject(GameObject gameObject, String resource){
        if(gameGraphics != null)
            gameGraphics.drawImage(resource, gameObject.getPosition());
    }

}

