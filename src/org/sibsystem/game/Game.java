package org.sibsystem.game;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.LWJGLException;

import org.sibsystem.config.Options;
 
public class Game {
	public static final String optionsFileName = "options.txt";
	public static final String windowTitleName = "Sibchyl System";
	public static void main(String[] args) {
		/* game code here */
		Options gameOptions = new Options(optionsFileName);
		
		try {
			Display.setDisplayMode(new DisplayMode(gameOptions.windowWidth, gameOptions.windowHeight));
			Display.setTitle(windowTitleName);
			Display.create();
		} catch (LWJGLException e) {
			System.err.println("Unable to create LWJGL display");
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		while(!Display.isCloseRequested()) {
			Display.update();
			Display.sync(gameOptions.frameCap);
		}
		Display.destroy();
		System.exit(0);
	}
}
