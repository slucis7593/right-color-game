package com.slucis.rightcolor.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.slucis.rightcolor.RightColorGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Right Color";
		config.width = 360;
		config.height = 600;
		
		new LwjglApplication(new RightColorGame(), config);
	}
}
