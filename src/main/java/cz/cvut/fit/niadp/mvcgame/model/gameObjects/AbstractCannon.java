package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public  abstract class AbstractCannon extends GameObject {
    protected IGameObjectsFactory gameObjectsFactory;
    protected IShootingMode shootingMode;

    public abstract void moveUp();
    public abstract void moveDown();

    public abstract void aimUp();
    public abstract void aimDown();
    public abstract void powerUp();
    public abstract void powerDown();

    public abstract void toggleShootingMode();
    public abstract List<AbstractMissile> shoot();
    public abstract void primitiveShoot();

    public abstract double getAngle();
    public abstract int getPower();
    public abstract void setAngle(double angle);
    public abstract void setPower(int power);

    public IShootingMode getShootingMode(){
        return shootingMode;
    }

    public void setShootingMode(IShootingMode mode){
        this.shootingMode = mode;
    }

    public void accept(IVisitor visitor){
        visitor.visit(this);
    }
}
