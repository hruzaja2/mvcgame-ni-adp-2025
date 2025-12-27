package cz.cvut.fit.niadp.mvcgame.controller;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.command.AimDownCommand;
import cz.cvut.fit.niadp.mvcgame.command.AimUpCommand;
import cz.cvut.fit.niadp.mvcgame.command.LoadGameCommand;
import cz.cvut.fit.niadp.mvcgame.command.MoveCannonDownCommand;
import cz.cvut.fit.niadp.mvcgame.command.MoveCannonUpCommand;
import cz.cvut.fit.niadp.mvcgame.command.PowerDownCommand;
import cz.cvut.fit.niadp.mvcgame.command.PowerUpCommand;
import cz.cvut.fit.niadp.mvcgame.command.RestartGameCommand;
import cz.cvut.fit.niadp.mvcgame.command.SaveGameCommand;
import cz.cvut.fit.niadp.mvcgame.command.ShootCommand;
import cz.cvut.fit.niadp.mvcgame.command.ToggleExplosiveCommand;
import cz.cvut.fit.niadp.mvcgame.command.ToggleFastCommand;
import cz.cvut.fit.niadp.mvcgame.command.ToggleHelpCommand;
import cz.cvut.fit.niadp.mvcgame.command.TogglePauseCommand;
import cz.cvut.fit.niadp.mvcgame.command.TogglePiercingCommand;
import cz.cvut.fit.niadp.mvcgame.command.ToggleShootingModeCommand;
import cz.cvut.fit.niadp.mvcgame.command.ToggleStrategyCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameKeys;
import cz.cvut.fit.niadp.mvcgame.memento.CareTaker;
import cz.cvut.fit.niadp.mvcgame.proxy.IGameModel;

public class GameController {
    private final IGameModel model;

    public GameController(IGameModel model){
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        for(String code : pressedKeysCodes) {
            switch(code) {
                case MvcGameKeys.UP_KEY:
                    model.registerCommand(new MoveCannonUpCommand(model));
                    break;
                case MvcGameKeys.DOWN_KEY:
                    model.registerCommand(new MoveCannonDownCommand(model));
                    break;
                case MvcGameKeys.SPACE_KEY:
                    model.registerCommand(new ShootCommand(model));
                    break;
                case MvcGameKeys.ESCAPE_KEY:
                    System.exit(0);
                    break;
                case MvcGameKeys.AIM_UP_KEY:
                    model.registerCommand(new AimUpCommand(model));
                    break;
                case MvcGameKeys.AIM_DOWN_KEY:
                    model.registerCommand(new AimDownCommand(model));
                    break;
                case MvcGameKeys.POWER_UP_KEY:
                    model.registerCommand(new PowerUpCommand(model));
                    break;
                case MvcGameKeys.POWER_DOWN_KEY:
                    model.registerCommand(new PowerDownCommand(model));
                    break;
                case MvcGameKeys.TOGGLE_MOVING_STRATEGY_KEY:
                    model.registerCommand(new ToggleStrategyCommand(model));
                    break;
                case MvcGameKeys.TOGGLE_SHOOTING_MODE_KEY:
                    model.registerCommand(new ToggleShootingModeCommand(model));
                    break;
                case MvcGameKeys.STORE_GAME_SNAPSHOT_KEY:
                    CareTaker.getInstance().createMemento();
                    break;
                case MvcGameKeys.RESTORE_GAME_SNAPSHOT_KEY:
                    CareTaker.getInstance().restoreMemento();
                    break;
                case MvcGameKeys.UNDO_LAST_COMMAND_KEY:
                    model.undoLastCommand();
                    break;
                case MvcGameKeys.TOGGLE_EXPLOSIVE_KEY:
                    model.registerCommand(new ToggleExplosiveCommand(model));
                    break;
                case MvcGameKeys.TOGGLE_FAST_KEY:
                    model.registerCommand(new ToggleFastCommand(model));
                    break;
                case MvcGameKeys.TOGGLE_PIERCING_KEY:
                    model.registerCommand(new TogglePiercingCommand(model));
                    break;
                case MvcGameKeys.TOGGLE_HELP_KEY:
                    model.registerCommand(new ToggleHelpCommand(model));
                    break;
                case MvcGameKeys.RESTART_GAME_KEY:
                    model.registerCommand(new RestartGameCommand(model));
                    break;
                case MvcGameKeys.PAUSE_KEY:
                    model.registerCommand(new TogglePauseCommand(model));
                    break;
                case MvcGameKeys.SAVE_GAME_KEY:
                    model.registerCommand(new SaveGameCommand(model));
                    break;
                case MvcGameKeys.LOAD_GAME_KEY:
                    model.registerCommand(new LoadGameCommand(model));
                    break;
                default:
                    //nothing
            }
        }
        model.update();
        pressedKeysCodes.clear(); //solves problem with multiple shots being fired for single press, as a side effect causes delay in the movement input
    }
}
