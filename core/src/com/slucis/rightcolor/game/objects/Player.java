package com.slucis.rightcolor.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.slucis.rightcolor.game.Assets;
import com.slucis.rightcolor.utils.GlobalReference;

public class Player {
	public Animation anim;
	public float stateTime;
	public float x, y;
	
	public Player() {		
		x = -25;
		y = 100;
		
		Array<AtlasRegion> regions = null;
		TextureAtlas atlas = Assets.instance.assetManager.get(GlobalReference.TEXTURE_ATLAS_OBJECTS); 
		
		for (Texture t : atlas.getTextures()) {
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		regions = atlas.findRegions("fox_idle");
		anim = new Animation(0.2f, regions, PlayMode.LOOP_PINGPONG);
	}

	public void update(float delta) {
		stateTime += delta;		
	}

	public void render(SpriteBatch batch) {
		batch.draw(anim.getKeyFrame(stateTime), x, y);		
	}
}
