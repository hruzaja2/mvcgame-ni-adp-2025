package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameResources;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameDrawer implements IVisitor{
    protected GraphicsContext graphicsContext;

    public void setGraphicsContext(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void visit(AbstractCannon cannon) {
        drawGameObject(cannon, MvcGameResources.CANNON_RESOURCE);
    }

    @Override
    public void visit(AbstractMissile missile) {
        drawGameObject(missile, MvcGameResources.MISSILE_RESOURCE);
    }

    protected void drawGameObject(GameObject gameObject, String resource){
        if(graphicsContext != null)
            graphicsContext.drawImage(new Image(resource), gameObject.getPosition().getX(), gameObject.getPosition().getY());
    }

}

