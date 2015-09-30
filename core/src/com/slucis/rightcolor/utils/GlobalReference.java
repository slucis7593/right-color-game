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

import com.badlogic.gdx.graphics.Color;

public class GlobalReference {
	
	/** Constants **/	
	// Visible game world is 5 meters wide
	public static final float VIEWPORT_WIDTH = 480f;

	// Visible game world is 5 meters tall
	public static final float VIEWPORT_HEIGHT = 800f;

	// GUI Width
	public static final float VIEWPORT_GUI_WIDTH = 480.0f;

	// GUI Height
	public static final float VIEWPORT_GUI_HEIGHT = 800.0f;

	// Location of description file for texture atlas
	public static final String TEXTURE_ATLAS_OBJECTS = "images/rightcolor.pack";
	public static final String TEXTURE_ATLAS_UI = "images/rightcolor-ui.pack";	
	public static final String TEXTURE_ATLAS_LIBGDX_UI = "images/uiskin.atlas";

	// Location of description file for skins	
	public static final String SKIN_RIGHT_COLOR_UI = "images/rightcolor-ui.json";
	public static final String SKIN_LIBGDX_UI = "images/uiskin.json";

	// Amount of extra lives at level start
	public static final int LIVES_START = 3;

	// Delay after game over
	public static final float TIME_DELAY_GAME_OVER = 5;

	// Delay after game finished
	public static final float TIME_DELAY_GAME_FINISHED = 6;

	// Game preferences file
	public static final String PREFERENCES = "rightcolor.prefs";

	// Image name
	public static final String SPLASH_IMAGE_NAME = "white-rectangle";
	
	/** Variables **/
	public static Color backgroundColor = new Color(27f / 255f, 206f / 255f, 95f / 255f, 255f / 255f);//167f / 255f, 224f / 255f, 229f / 255f, 255f / 255f);
}
