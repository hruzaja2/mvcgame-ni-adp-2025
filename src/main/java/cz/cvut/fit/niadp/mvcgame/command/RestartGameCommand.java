package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class RestartGameCommand extends AbstractGameCommand {

    public RestartGameCommand(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        this.model.restartGame();
    }

    @Override
    public void unExecute() {
        // Cannot undo a restart
    }
}
