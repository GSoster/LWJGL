package entities;

import java.awt.Rectangle;

public abstract class AbstractEntity implements Entity {

	
	protected double x, y, width, height;
	protected Rectangle hitbox = new Rectangle();
	
	/**
	 * We dont implement draw() and update() to force classes that extend this one to implement that. 
	 */
	
	
	public AbstractEntity(double x, double y, double width, double height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);

	}


	@Override
	public void setLocation(double x, double y) {
		setX(x);
		setY(y);
	}

	@Override
	public void setX(double x) {
		this.x = x;

	}

	@Override
	public void setY(double y) {
		this.y = y;

	}

	@Override
	public void setWidth(double width) {
		this.width = width;

	}

	@Override
	public void setHeight(double height) {
		this.height = height;

	}

	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}

	@Override
	public double getWidth() {
		return this.width;		
	}

	@Override
	public double getHeight() {
		return this.height;
	}

	@Override
	public boolean intersects(Entity other) {
		hitbox.setBounds((int)x, (int)y, (int)width, (int)height);
		return hitbox.intersects(other.getX(), other.getY(), other.getWidth(), other.getHeight());
	}

}
