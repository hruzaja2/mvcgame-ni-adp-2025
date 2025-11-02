package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;

public class CannonA extends AbstractCannon {
    //int power
    //float angle

    public CannonA(Position position, IGameObjectsFactory gameObjectsFactory){
        this.position = position;
        this.gameObjectsFactory = gameObjectsFactory;
    }

    public void moveUp(){
        move(new Vector(0, -1 * MvcGameConfig.MOVE_STEP));
    }

    public void moveDown(){
        
        move(new Vector(0, MvcGameConfig.MOVE_STEP));
    }

    public AbstractMissile shoot(){
        return gameObjectsFactory.createMissile();
    }

    
}
