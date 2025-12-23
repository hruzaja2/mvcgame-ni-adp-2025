package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class TogglePiercingCommand extends AbstractGameCommand {

    public TogglePiercingCommand(IGameModel model){
        this.model = model;
    }

    @Override
    protected void execute() {
        model.togglePiercingMissiles();
    }
}
