package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class TogglePauseCommand extends AbstractGameCommand {

    public TogglePauseCommand(IGameModel model){
        this.model = model;
    }

    @Override
    protected void execute() {
        model.togglePause();
    }
}
