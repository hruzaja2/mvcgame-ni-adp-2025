package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class ShootCommand extends AbstractGameCommand {

    public ShootCommand(IGameModel model){
        this.model = model;
    }

    @Override
    protected void execute() {
        model.cannonShoot();
    }
}