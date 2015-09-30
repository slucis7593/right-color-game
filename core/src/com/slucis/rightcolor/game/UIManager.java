package com.slucis.rightcolor.game;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.visible;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.slucis.rightcolor.game.WorldController.GAME_STATE;
import com.slucis.rightcolor.utils.GlobalReference;

public class UIManager implements Disposable {
	protected static final String TAG = UIManager.class.getName();
	
	// Objects
	private Stage stage;
	private Skin skinMenu;
	private Skin skinLibgdx;
	
	private WorldController worldController;
	
	// Attributes
	private boolean isPause;
	
	private Color backgroundColor = GlobalReference.backgroundColor;
	
	// UI Actors
	public LabelStyle labelStyle;

	public Label lblMessage;
	
	public TextButton tBtnClick;

	public Image imgSplash;

	// Tables
	public Table layerMenu;	

	public UIManager(WorldController worldController) {
		this.worldController = worldController;
		
		stage = new Stage(new ExtendViewport(GlobalReference.VIEWPORT_GUI_WIDTH,
				GlobalReference.VIEWPORT_GUI_HEIGHT));

		// Set up input processor
		Gdx.input.setInputProcessor(stage);

		rebuildStage();
	}

	private void rebuildStage() {
		skinLibgdx = new Skin(Gdx.files.internal(GlobalReference.SKIN_LIBGDX_UI),
				new TextureAtlas(GlobalReference.TEXTURE_ATLAS_LIBGDX_UI));
		skinLibgdx.getRegion("textfield").getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		skinMenu  = new Skin(Gdx.files.internal(GlobalReference.SKIN_RIGHT_COLOR_UI), 
				new TextureAtlas(GlobalReference.TEXTURE_ATLAS_UI));
		skinMenu.getRegion(GlobalReference.SPLASH_IMAGE_NAME).getTexture()
				.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		labelStyle = new LabelStyle(Assets.instance.assetBradyFonts.smallSize,
				Color.WHITE);

		// Build all layers
		layerMenu = buildMenuLayer();

		// Assemble stage
		stage.clear();

		stage.addActor(layerMenu);

		// Draw final
		imgSplash = new Image(skinMenu, GlobalReference.SPLASH_IMAGE_NAME);
		imgSplash.setColor(backgroundColor);
		imgSplash.setFillParent(true);
		stage.addActor(imgSplash);

		// First action
		// You need to set image.visible = false to available click on Button
		// Because image layer is in upper of button layer
		// U can't click button if image layer is still visible
		imgSplash.addAction(sequence(alpha(0f, 0.5f), visible(false)));
	}

	private Table buildMenuLayer() {

		Table layer = new Table();
		layer.setFillParent(true);

		lblMessage = new Label("S.Lucis: Hello the World", labelStyle);		
		lblMessage.setScale(0.2f);
		lblMessage.setColor(Color.WHITE);

		layer.add(lblMessage);

		layer.row();
		
		tBtnClick = new TextButton("Pause", skinLibgdx, "default");
		tBtnClick.addListener(new ChangeListener() {			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (worldController.gameState == GAME_STATE.PLAY) {
					worldController.pause();
					tBtnClick.setText("Resume");
				}
				else {
					worldController.resume();
					tBtnClick.setText("Pause");
				}
			}
		});
		layer.add(tBtnClick).width(100f).height(80f);
		
		// layer.debug();

		return layer;
	}

	public void update(float delta) {
		if (!isPause) {
			stage.act(Math.min(delta, 1 / 60f));
		}
	}

	public void render() {
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
		skinMenu.dispose();
		skinLibgdx.dispose();		
	}
	
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	public void resume() {
		isPause = false;
	}

	public void pause() {
		isPause = true;
	}	

	public InputProcessor getInputProcessor() {
		return stage;
	}
}
