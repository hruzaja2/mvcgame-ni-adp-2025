package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class SaveGameCommand extends AbstractGameCommand {

    private static final String DEFAULT_SAVE_FILE = "savegame.json";

    public SaveGameCommand(IGameModel model){
        this.model = model;
    }

    @Override
    protected void execute() {
        model.saveGameToFile(DEFAULT_SAVE_FILE);
    }
}