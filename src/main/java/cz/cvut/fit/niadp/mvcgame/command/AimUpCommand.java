package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class AimUpCommand extends AbstractGameCommand {

    public AimUpCommand(IGameModel model){
        this.model = model;
    }

    @Override
    protected void execute() {
        model.aimCannonUp();
    }
}