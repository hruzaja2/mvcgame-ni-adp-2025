package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbstractMissile extends GameObject {
    public void accept(IVisitor visitor){
        visitor.visit(this);
    }
}
