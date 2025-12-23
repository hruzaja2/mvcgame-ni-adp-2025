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
        String shootingMode = cannon.getShootingMode() instanceof DoubleShootingMode ? "Double" : "Single";

        // Draw HUD information
        int startY = 20;
        int lineHeight = 20;

        gameGraphics.drawText("Score: " + gameInfo.getScore(), new Position(10, startY));
        gameGraphics.drawText("Angle: " + String.format("%.1f", cannon.getAngle()), new Position(10, startY + lineHeight));
        gameGraphics.drawText("Power: " + cannon.getPower(), new Position(10, startY + 2 * lineHeight));
        gameGraphics.drawText("Mode: " + shootingMode, new Position(10, startY + 3 * lineHeight));
        gameGraphics.drawText("Strategy: " + strategyName, new Position(10, startY + 4 * lineHeight));
    }

    protected void drawGameObject(GameObject gameObject, String resource){
        if(gameGraphics != null)
            gameGraphics.drawImage(resource, gameObject.getPosition());
    }

}

