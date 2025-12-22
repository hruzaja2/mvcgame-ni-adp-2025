package cz.cvut.fit.niadp.mvcgame.model;

public class Vector {

    private int dX = 0;
	private int dY = 0;
	
	public Vector() {
	}

	public Vector(int dX, int pY) {
		this.dX = dX;
		this.dY = pY;
	}

	public int getDX() {
		return dX;
	}
    
    public int getDY() {
		return dY;
	}
    
    public void setDX(int x) {
		this.dX = x;
	}
    
    public void setDY(int y) {
		this.dY = y;
	}

    public void add(Vector vector){
        setDX(getDX() + vector.getDX());
        setDY(getDY() + vector.getDY());
    }
}
