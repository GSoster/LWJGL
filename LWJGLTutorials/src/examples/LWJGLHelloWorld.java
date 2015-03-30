package examples;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class LWJGLHelloWorld {

	/**
	 * 
	 * 	######### VIDEO 2  ###########
	 * 	
	 */
	
	
	public LWJGLHelloWorld(){
		try{
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Hello LWJGL");
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
		
		while(!Display.isCloseRequested()){
			//render
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	/*public static void main(String[] args){
		new LWJGLHelloWorld();
	}*/
	
}
