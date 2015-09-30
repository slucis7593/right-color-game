package com.slucis.rightcolor.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.slucis.rightcolor.utils.GlobalReference;

public class Assets implements Disposable, AssetErrorListener {
	public static final Assets instance = new Assets();

	private static final String TAG = Assets.class.getName();

	public AssetManager assetManager;
	
	public AssetBradyFonts assetBradyFonts;
	
	private Assets() {
		
	}
	
	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;
		// set asset manager error handler
		assetManager.setErrorListener(this);
		// load texture atlas
		
		// load sounds
		
		// load musics
		
		// load fonts
		assetBradyFonts = new AssetBradyFonts();
	}
	
	public void addAssets() {		
		assetManager.load(GlobalReference.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
	}

	@Override
	public void error(@SuppressWarnings("rawtypes") AssetDescriptor asset, Throwable throwable) {
		Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'",
				(Exception) throwable);		
	}

	@Override
	public void dispose() {
		assetManager.dispose();		
	}

	/** Inner Class **/
	public class AssetDefaultFonts {
		public final BitmapFont defaultSmall;
		public final BitmapFont defaultNormal;
		public final BitmapFont defaultBig;

		public AssetDefaultFonts () {
			// create three fonts using Libgdx's built-in 15px bitmap font
			defaultSmall = new BitmapFont();
			defaultNormal = new BitmapFont();
			defaultBig = new BitmapFont();
			// set font sizes
			defaultSmall.setScale(0.75f);
			defaultNormal.setScale(1.0f);
			defaultBig.setScale(2.0f);
			// enable linear texture filtering for smooth fonts
			defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			defaultNormal.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			defaultBig.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	}
	
	public class AssetBradyFonts {
		public final BitmapFont smallSize;
		public final BitmapFont normalSize;
		public final BitmapFont bigSize;
		
		public AssetBradyFonts() {
			smallSize = new BitmapFont(Gdx.files.internal("fonts/Brady Bunch.fnt"), Gdx.files.internal("fonts/Brady Bunch.png"), false);
			normalSize = new BitmapFont(Gdx.files.internal("fonts/Brady Bunch.fnt"), Gdx.files.internal("fonts/Brady Bunch.png"), false);
			bigSize = new BitmapFont(Gdx.files.internal("fonts/Brady Bunch.fnt"), Gdx.files.internal("fonts/Brady Bunch.png"), false);
			
			smallSize.setScale(0.5f);
			normalSize.setScale(1.0f);
			bigSize.setScale(2.0f);
			
			smallSize.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			normalSize.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			bigSize.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	}
}
