package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public class GameInfo extends GameObject {

    private final IGameModel model;
    private int score;

    public GameInfo(IGameModel model) {
        this.model = model;
        this.position = new Position(10, 10); // Top-left corner for HUD
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public void incrementScore(int points) {
        score += points;
    }

    public IGameModel getModel() {
        return model;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}