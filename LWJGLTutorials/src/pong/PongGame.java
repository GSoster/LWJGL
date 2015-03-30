package pong;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

import entities.MoveableEntity;
import entities.AbstractMoveableEntity;
import entities.Entity;
import static org.lwjgl.opengl.GL11.*;

public class PongGame {

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final String TITLE = "PONG GAME!";
	private boolean isRunning = true;
	private Ball ball;
	private Bat bat;
	private Bat batAutomatic;
	private List<MoveableEntity> entitiesList = new ArrayList<MoveableEntity>();
	
	public PongGame() {
		setUpDisplay();
		setUpOpenGL();
		setUpEntities();
		setUpTimer();
		while (isRunning) {
			render();
			logic(getDelta());
			input();
			Display.update();
			Display.sync(60);
			if (Display.isCloseRequested())
				this.isRunning = false;
		}
		
		Display.destroy();
	}

	// delta stuff
	private long lastFrame;

	private long getTime() {
		return ((Sys.getTime() * 1000) / Sys.getTimerResolution());
	}

	private int getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}

	
	
	private void input(){
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
			bat.setDY(-.2);
		else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			bat.setDY(.2);
		else
			bat.setDY(0);
	}
	
	private void setUpDisplay() {
		try{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
	}

	private void setUpOpenGL() {
		// intialization code OpenGL
		glMatrixMode(GL_PROJECTION);// state
		glLoadIdentity();// not explanation now
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);// set the OpenGL perspective
											// (x,y,z)
		glMatrixMode(GL_MODELVIEW);
	}

	private void setUpEntities() {
		bat = new Bat(10, HEIGHT/2 -80 / 2, 10, 80);
		batAutomatic = new Bat(WIDTH - 20, HEIGHT/2 -80 / 2, 10, 80);
		ball = new Ball(WIDTH/2 - 10/2, HEIGHT/2 - 10/2, 10, 10);
		ball.setDX(-.1);
		entitiesList.add(bat);
		entitiesList.add(batAutomatic);
		entitiesList.add(ball);
		
	}

	private void setUpTimer() {
		lastFrame = getTime();
	}

	private boolean isRunning() {
		return false;
	}

	private void render() {
		glClear(GL_COLOR_BUFFER_BIT);//clear the screen
		
		for(MoveableEntity e : entitiesList){
			e.draw();
		}
	}

	private void logic(int delta) {
		
		for(MoveableEntity e : entitiesList){
			e.update(delta);			
		}		
		
		if(bat.intersects(ball))
			ball.setDX(0.3);
		if(batAutomatic.intersects(ball))
			ball.setDX(-0.3);
	}

	private static class Bat extends AbstractMoveableEntity {

		public Bat(double x, double y, double width, double height) {
			super(x, y, width, height);
		}

		@Override
		public void draw() {
			glRectd(x, y, x + width, y + height);

		}

	}
	

	
	private static class Ball extends AbstractMoveableEntity {

		public Ball(double x, double y, double width, double height) {
			super(x, y, width, height);
		}

		@Override
		public void draw() {
			glRectd(x, y, x + width, y + height);

		}

	}
	

	public static void main(String args[]) {
		new PongGame();
	}

}
