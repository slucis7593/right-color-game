/*******************************************************************************
 * Copyright 2013 Andreas Oehlke
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


package com.slucis.rightcolor.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Base64Coder;

public class GamePreferences {
	public static final String TAG = GamePreferences.class.getName();

	public static final GamePreferences instance = new GamePreferences();

	public boolean sound;
	public boolean music;
	
	private float highScore;

	public int getHighScore() {
		return (int)((highScore - 0.93f) / 0.75f);
	}

	public void setHighScore(int highScore) {
		this.highScore = (highScore * 0.75f + 0.93f);
	}
	
	public float getTrueHighScore() {
		return highScore;
	}

	public Preferences prefs;

	// singleton: prevent instantiation from other classes
	private GamePreferences () {
		prefs = Gdx.app.getPreferences(GlobalReference.PREFERENCES);
	}

	public void load () {
		sound = prefs.getBoolean("sound", true);
		music = prefs.getBoolean("music", true);
		highScore = getDecodedFloat(prefs.getString("high_score", "0"));		
	}

	public void save () {
		prefs.putBoolean("sound", sound);
		prefs.putBoolean("music", music);
		prefs.putString("high_score", getEncodedString(highScore));
		prefs.flush();
	}

	private String getEncodedString(float value) {
		return Base64Coder.encodeString(Float.toString(value	));
	}
	
	private float getDecodedFloat(String encodedString) {
		try {
			return Float.parseFloat(Base64Coder.decodeString(encodedString));
		} catch (Exception e) {
			return 0f;
		}
	}
}
