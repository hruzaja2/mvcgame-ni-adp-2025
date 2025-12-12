package cz.cvut.fit.niadp;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;

import cz.cvut.fit.niadp.mvcgame.MvcGame;
import cz.cvut.fit.niadp.mvcgame.bridge.GameGraphics;
import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.bridge.JavaFxGraphics;

public class MvcGameJavaFxLauncher extends Application {

    private static final MvcGame theMvcGame = new MvcGame();

    @Override
    public void init() {
        theMvcGame.init();
    }

    @Override
    public void start(Stage stage) {
        String winTitle = theMvcGame.getWindowTitle();
        int winWidth = theMvcGame.getWindowWidth();
        int winHeigth = theMvcGame.getWindowHeight();
        stage.setTitle( winTitle );
        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );
        Canvas canvas = new Canvas( winWidth, winHeigth );
        root.getChildren().add( canvas );
        GraphicsContext gc = canvas.getGraphicsContext2D();
        IGameGraphics gameGraphics = new GameGraphics(new JavaFxGraphics(gc));
        ArrayList<String> pressedKeysCodes = new ArrayList<>();
        theScene.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    // only add once... prevent duplicates
                    if (!pressedKeysCodes.contains(code))
                        pressedKeysCodes.add(code);
                }
        );
        theScene.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    pressedKeysCodes.remove( code );
                }
        );
        // the game-loop
        theMvcGame.setGraphicsContext(gameGraphics);
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                theMvcGame.processPressedKeys(pressedKeysCodes);
                //theMvcGame.update();
                //theMvcGame.render(gc);
            }
        }.start();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}