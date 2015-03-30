package examples;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * 
 * 	######### VIDEO 5  ###########
 * 	
 */


public class DemoTexture{

	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final String TITLE = "DemoTexture";
	
	private Texture wood;
	
	public DemoTexture(){
		try{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
		
		wood = loadTexture("wood");
		
		
		//intialization code OpenGL
		glMatrixMode(GL_PROJECTION);//state
		glLoadIdentity();//not explanation now
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);//set the OpenGL perspective (x,y,z)
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);//allow the use of 2D Textures
		
		
		
		
		
		while(!Display.isCloseRequested()){
			//render
			
			//clear screen before render
			glClear(GL_COLOR_BUFFER_BIT);
			
			wood.bind();//bind texture to OPENGL Context
			
			glBegin(GL_QUADS); //upper left corner -> upper right corner -> bottom right corner --> bottom left corner
				glTexCoord2f(0, 0);//upper left
				glVertex2i(400,400);//upper left corner
				glTexCoord2f(1, 0);//1 means the ENTIRE WIDTH of the texture
				glVertex2i(450,400);//upper right corner
				glTexCoord2f(1, 1);
				glVertex2i(450,450);//bottom left corner
				glTexCoord2f(0, 1);
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
	
	private Texture loadTexture(String key){
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/"+key+".png")));
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch (IOException e) { 
			e.printStackTrace();
		}
		return null;
	}
	
	/*public static void main(String[] args){
		new DemoTexture();
	}*/
	
}
