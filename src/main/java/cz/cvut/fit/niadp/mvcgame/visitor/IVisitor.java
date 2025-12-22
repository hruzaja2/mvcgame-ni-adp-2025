package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbstractMissile;

public interface IVisitor {
    public void visit(AbstractCannon cannon);
    public void visit(AbstractMissile missile);
}
