package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public  abstract class AbstractCannon extends GameObject {
    protected IGameObjectsFactory gameObjectsFactory;

    public abstract void moveUp();
    public abstract void moveDown();

    public abstract AbstractMissile shoot();

    public void accept(IVisitor visitor){
        visitor.visit(this);
    }
}
