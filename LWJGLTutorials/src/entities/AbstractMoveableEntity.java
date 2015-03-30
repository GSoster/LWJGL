package entities;

public abstract class AbstractMoveableEntity extends AbstractEntity implements MoveableEntity {

	protected double dx, dy;	

	public AbstractMoveableEntity(double x, double y, double width, double height){
		super(x, y, width, height);
		setDX(0);
		setDY(0);
	}
	
	
	public void update(int delta){
		this.x += delta * dx;
		this.y += delta * dy;
	}
	
	public double getDX(){
		return this.dx;
	}
	public double getDY(){
		return this.dy;
	}
	public void setDX(double dx){
		this.dx = dx;
	}
	public void setDY(double dy){
		this.dy = dy;
	}
	
}
