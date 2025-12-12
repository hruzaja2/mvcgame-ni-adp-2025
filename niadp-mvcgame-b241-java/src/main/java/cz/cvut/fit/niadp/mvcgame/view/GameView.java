package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;
import cz.cvut.fit.niadp.mvcgame.visitor.GameDrawer;

public class GameView implements IObserver{
    private final IGameModel model;
    private final GameController controller;
    private IGameGraphics gameGraphics;
    protected GameDrawer gameDrawer;

    public GameView(IGameModel model){
        this.model = model;
        this.controller = new GameController(model);
        this.model.registerObserver(this);
        this.gameDrawer = new GameDrawer();
    }

    public GameController getController(){
        return controller;
    }

    public void render() {
        if(gameGraphics != null) {
            gameGraphics.clear();
            model.getGameObjects().forEach(gameObject -> gameObject.accept(gameDrawer));
        }
    }

    public void setGraphicsContext(IGameGraphics gameGraphics){
        this.gameGraphics = gameGraphics;
        this.gameDrawer.setGraphicsContext(gameGraphics);
        this.render();
    }

    @Override
    public void update(){
        render();
    }
}
