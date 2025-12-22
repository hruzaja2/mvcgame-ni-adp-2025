package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class MoveCannonDownCommand extends AbstractGameCommand{

    public MoveCannonDownCommand(IGameModel model){
        this.model = model;
    }

    @Override
    protected void execute() {
        model.moveCannonDown();
    }
}
