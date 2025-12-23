package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class ToggleExplosiveCommand extends AbstractGameCommand {

    public ToggleExplosiveCommand(IGameModel model){
        this.model = model;
    }

    @Override
    protected void execute() {
        model.toggleExplosiveMissiles();
    }
}
