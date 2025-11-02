package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.visitor.GameDrawer;
import javafx.scene.canvas.GraphicsContext;

public class GameView implements IObserver{
    private final GameModel model;
    private final GameController controller;
    private GraphicsContext graphicsContext;
    protected GameDrawer gameDrawer;

    public GameView(GameModel model){
        this.model = model;
        this.controller = new GameController(model);
        this.model.registerObserver(this);
        this.gameDrawer = new GameDrawer();
    }

    public GameController getController(){
        return controller;
    }

    public void render() {
        if(graphicsContext != null) {
            graphicsContext.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
            model.getGameObjects().forEach(gameObject -> gameObject.accept(gameDrawer));
        }
    }

    public void setGraphicsContext(GraphicsContext graphicsContext){
        this.graphicsContext = graphicsContext;
        this.gameDrawer.setGraphicsContext(graphicsContext);
        this.render();
    }

    @Override
    public void update(){
        render();
    }
}
