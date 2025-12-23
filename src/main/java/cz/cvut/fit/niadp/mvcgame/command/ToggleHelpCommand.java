package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class ToggleHelpCommand extends AbstractGameCommand {

    public ToggleHelpCommand(IGameModel model){
        this.model = model;
    }

    @Override
    protected void execute() {
        model.toggleHelp();
    }
}
