package cz.cvut.fit.niadp.mvcgame.bridge;

import cz.cvut.fit.niadp.mvcgame.model.Position;

public interface IGameGraphicsImplementor {
    void drawImage(String path, Position position);
    void drawText(String text, Position position);
    void drawLine(Position startPosition, Position endPosition);
    void clear();
}
