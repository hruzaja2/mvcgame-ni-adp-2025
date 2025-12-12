package cz.cvut.fit.niadp.mvcgame.bridge;

import cz.cvut.fit.niadp.mvcgame.model.Position;

public class GameGraphics implements IGameGraphics {

    protected IGameGraphicsImplementor implementor;

    public GameGraphics(IGameGraphicsImplementor implementor){
        this.implementor = implementor;
    }

    @Override
    public void drawImage(String path, Position position) {
        implementor.drawImage(path, position);
    }

    @Override
    public void drawText(String text, Position position) {
        implementor.drawText(text, position);
    }

    @Override
    public void drawRectangle(Position leftTop, Position rightBottom) {
        Position rightTop = new Position(rightBottom.getX(), leftTop.getY());
        Position leftBottom = new Position(leftTop.getX(), rightBottom.getY());
        implementor.drawLine(leftTop, rightTop);
        implementor.drawLine(rightTop, rightBottom);
        implementor.drawLine(rightBottom, leftBottom);
        implementor.drawLine(leftBottom, leftTop);
    }

    @Override
    public void clear() {
        implementor.clear();
    }

}
