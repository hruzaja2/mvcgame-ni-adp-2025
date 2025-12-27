package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class LoadGameCommand extends AbstractGameCommand {

    private static final String DEFAULT_SAVE_FILE = "savegame.json";

    public LoadGameCommand(IGameModel model){
        this.model = model;
    }

    @Override
    protected void execute() {
        model.loadGameFromFile(DEFAULT_SAVE_FILE);
    }
}