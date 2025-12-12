package cz.cvut.fit.niadp.mvcgame.model;

import org.junit.Assert;
import org.junit.Test;

import cz.cvut.fit.niadp.mvcgame.command.MoveCannonUpCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class GameModelBasicTest {

    @Test
    public void undoLastCommandTest(){
        IGameModel gameModel = new GameModel();
        int beginCannonPositionX = gameModel.getCannonPosition().getX();
        int beginCannonPositionY = gameModel.getCannonPosition().getY();
        gameModel.registerCommand(new MoveCannonUpCommand(gameModel));
        gameModel.update();
        Assert.assertEquals(beginCannonPositionX, gameModel.getCannonPosition().getX());
        Assert.assertEquals(beginCannonPositionY - MvcGameConfig.MOVE_STEP, gameModel.getCannonPosition().getY());
        gameModel.undoLastCommand();
        Assert.assertEquals(beginCannonPositionX, gameModel.getCannonPosition().getX());
        Assert.assertEquals(beginCannonPositionY, gameModel.getCannonPosition().getY());
    }
}
