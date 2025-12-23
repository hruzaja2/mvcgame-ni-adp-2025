package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class ToggleFastCommand extends AbstractGameCommand {

    public ToggleFastCommand(IGameModel model){
        this.model = model;
    }

    @Override
    protected void execute() {
        model.toggleFastMissiles();
    }
}
