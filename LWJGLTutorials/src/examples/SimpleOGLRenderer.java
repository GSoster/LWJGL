package examples;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

/**
 * 
 * 	######### VIDEO 3  ###########
 * 	
 */


public class SimpleOGLRenderer{

	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final String TITLE = "Simple OGL Renderer";
	
	public SimpleOGLRenderer(){
		try{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
		
		//intialization code OpenGL
		glMatrixMode(GL_PROJECTION);//state
		glLoadIdentity();//not explanation now
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);//set the OpenGL perspective (x,y,z)
		glMatrixMode(GL_MODELVIEW);
		
		
		
		while(!Display.isCloseRequested()){
			//render
			
			//clear screen before render
			glClear(GL_COLOR_BUFFER_BIT);
			
			glBegin(GL_QUADS); //upper left corner -> upper right corner -> bottom right corner --> bottom left corner
				glVertex2i(400,400);//upper left corner
				glVertex2i(450,400);//upper right corner
				glVertex2i(450,450);//bottom left corner
				glVertex2i(400,450);//bottom right corner
			
			glEnd();
			
			
			
			//start drawing a shape: glBegin
			glBegin(GL_LINES);//define the shape as a line
				glVertex2i(100, 100);//vertices of the beggin line
				glVertex2i(200, 200);//destination of the line
			glEnd();
			
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	/*public static void main(String[] args){
		new SimpleOGLRenderer();
	}*/
	
}
