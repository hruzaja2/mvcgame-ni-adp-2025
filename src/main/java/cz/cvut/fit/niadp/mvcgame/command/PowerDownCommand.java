package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class PowerDownCommand extends AbstractGameCommand {

    public PowerDownCommand(IGameModel model){
        this.model = model;
    }

    @Override
    protected void execute() {
        model.cannonPowerDown();
    }
}