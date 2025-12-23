package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class PowerUpCommand extends AbstractGameCommand {

    public PowerUpCommand(IGameModel model){
        this.model = model;
    }

    @Override
    protected void execute() {
        model.cannonPowerUp();
    }
}