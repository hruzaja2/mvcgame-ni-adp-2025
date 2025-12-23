package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class ToggleStrategyCommand extends AbstractGameCommand {

    public ToggleStrategyCommand(IGameModel model){
        this.model = model;
    }

    @Override
    protected void execute() {
        model.toggleMovingStrategy();
    }
}