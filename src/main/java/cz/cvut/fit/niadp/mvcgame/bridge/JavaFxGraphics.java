package cz.cvut.fit.niadp.mvcgame.bridge;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class JavaFxGraphics  implements IGameGraphicsImplementor {
    
    private final GraphicsContext graphicsContext;

    public JavaFxGraphics(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void drawImage(String path, Position position) {
        graphicsContext.drawImage(new Image(path), position.getX(), position.getY());
    }

    @Override
    public void drawText(String text, Position position) {
        graphicsContext.fillText(text, position.getX(), position.getY());
    }

    @Override
    public void drawLine(Position startPosition, Position endPosition) {
        graphicsContext.strokeLine(startPosition.getX(), startPosition.getY(), endPosition.getX(), endPosition.getY());
    }

    @Override
    public void clear() {
        graphicsContext.clearRect(MvcGameConfig.MIN_X, MvcGameConfig.MIN_Y, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
    }

}
