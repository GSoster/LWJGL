package examples;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * 
 * 	######### VIDEO 4  ###########
 * 	
 */


public class InputDemo{

	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final String TITLE = "InputDemo";
	
	
	private List<Box> shapes = new ArrayList<Box>();
	private boolean somethingIsSelected = false;
	private volatile boolean randomColorCooldown = false;
	
	public InputDemo(){
		try{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
		
		
		shapes.add(new Box(15, 15));
		shapes.add(new Box(100, 150));
		
		//intialization code OpenGL
		glMatrixMode(GL_PROJECTION);//state
		glLoadIdentity();//not explanation now
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);//set the OpenGL perspective (x,y,z)
		glMatrixMode(GL_MODELVIEW);
		
		
		int mouseY;
		int mouseX;
		while(!Display.isCloseRequested()){
			//render
			
			//clear screen before render
			glClear(GL_COLOR_BUFFER_BIT);
			
			//Examples of Keyboard and Mouse Input			
			
			while(Keyboard.next()){
				if(Keyboard.getEventKey() == Keyboard.KEY_C && Keyboard.getEventKeyState())
					shapes.add(new Box(15,15));
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				Display.destroy();
				System.exit(0);
			}
						
				
			
			mouseY = Display.getHeight() - Mouse.getY() -1;
			mouseX = Mouse.getX();						
			
			for(Box box : shapes){
				if(Mouse.isButtonDown(0) && box.inBounds(mouseX, mouseY)){
					this.somethingIsSelected = true;
					box.selected = true;
					System.out.println("You clicked me!");
				}
				if(Mouse.isButtonDown(1)){
					this.somethingIsSelected = false;
					box.selected = false;
				}
			
				if(Mouse.isButtonDown(2) && box.inBounds(mouseX, mouseY))//middle mouse button
					box.randomizeColors();										
				
				if(box.selected)
					box.update(Mouse.getDX(), -Mouse.getDY());
				
				box.draw();
			}
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	
	private static class Box{
		public int x, y;
		private float colorRed, colorBlue, colorGreen;
		public boolean selected = false;
		private Random rGenerator;
		
		Box(int x, int y){
			this.x = x;
			this.y = y;
			this.rGenerator= new Random();
			colorRed = this.rGenerator.nextFloat();
			colorBlue = this.rGenerator.nextFloat();
			colorGreen = this.rGenerator.nextFloat();
		}
		
		void update(int dx, int dy){
			x += dx;
			y += dy;
		}
		
		public boolean inBounds(int mouseX, int mouseY){
			if(mouseX > x && mouseX < x+ 50 && mouseY > y && mouseY < y + 50)							
				return true;
			return false;
		}
		
		
		 void randomizeColors(){
			colorRed = this.rGenerator.nextFloat();
			colorBlue = this.rGenerator.nextFloat();
			colorGreen = this.rGenerator.nextFloat();
		}
		
		void draw(){
			glColor3f(colorRed, colorGreen, colorBlue);
			
			glBegin(GL_QUADS);
				glVertex2f(x, y);
				glVertex2f(x + 50, y);
				glVertex2f(x + 50, y + 50);
				glVertex2f(x , y + 50);
				
				
			glEnd();
		}
				
	}
	
	/*public static void main(String[] args){
		new InputDemo();
	}*/
	
}
