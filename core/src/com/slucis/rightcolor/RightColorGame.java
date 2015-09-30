package com.slucis.rightcolor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.slucis.rightcolor.game.Assets;
import com.slucis.rightcolor.screens.SplashScreen;
import com.slucis.rightcolor.utils.GamePreferences;

public class RightColorGame extends Game {	
//	private static final String TAG = RightColorGame.class.getName();

	@Override
	public void create () {		
		Assets.instance.init(new AssetManager());		
		
//		GamePreferences.instance.setHighScore(100);
//		GamePreferences.instance.save();
		
		GamePreferences.instance.load();
		
		//Gdx.app.log(TAG, "Score: " + GamePreferences.instance.getHighScore());
		
		setScreen(new SplashScreen(this));
	}
}
