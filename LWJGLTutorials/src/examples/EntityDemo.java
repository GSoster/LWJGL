package examples;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

import entities.AbstractEntity;
import entities.AbstractMoveableEntity;
import entities.Entity;
import entities.MoveableEntity;

/**
 * 
 * 	######### VIDEO 8  ###########
 * https://www.youtube.com/watch?v=-LwBWysU-FY&list=PL19F2453814E0E315&index=8
 * 	
 */


public class EntityDemo{

	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final String TITLE = "EntityDemo";
	
	//delta stuff
	private long lastFrame;
	
	private long getTime(){
		return ((Sys.getTime() * 1000) / Sys.getTimerResolution());
	}
	
	private int getDelta(){
		long currentTime = getTime();
		int delta = (int)(currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}
	
	
	
	
	public static class Box extends AbstractMoveableEntity{

		public Box(double x, double y, double width, double height) {
			super(x, y, width, height);
		}

		@Override
		public void draw() {
			glRectd(getX(), getY(), getWidth(), getHeight());		
		}
	}
	
	public static class Point extends AbstractEntity{

		public Point(double x, double y) {
			super(x, y, 5, 5);
		}

		@Override
		public void draw() {
			glBegin(GL_POINTS);
				glVertex2d(x, y);
			glEnd();
		}

		@Override
		public void update(int delta) {
			//setLocation(Mouse.getX(), HEIGHT - Mouse.getY() - 1);
		}
		

		
	}
	
	
	public EntityDemo(){
		try{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
		
		//initialization code entities
		MoveableEntity box = new Box(100, 100, 50, 50);	
		Entity point = new Point(10,10);
		
		
		//intialization code OpenGL
		glMatrixMode(GL_PROJECTION);//state
		glLoadIdentity();//not explanation now
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);//set the OpenGL perspective (x,y,z)
		glMatrixMode(GL_MODELVIEW);
		
		
		//delta stuff
		lastFrame = getTime();
		int delta;
		
		while(!Display.isCloseRequested()){
			//render
						
			
			//clear screen before render
			glClear(GL_COLOR_BUFFER_BIT);
			
			point.setLocation(Mouse.getX(), HEIGHT - Mouse.getY() - 1);
			
			delta = getDelta();
			point.update(delta);
			box.update(delta);
			
			
			if(box.intersects(point)){
				box.setDX(0.2);
			}
			
			point.draw();
			box.draw();
			
			
			Display.update();
			Display.sync(60);//FPS
		}
		Display.destroy();
	}
	
	public static void main(String[] args){
		new EntityDemo();
	}
	
}
