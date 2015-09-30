package com.slucis.rightcolor.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;
import com.slucis.rightcolor.RightColorGame;
import com.slucis.rightcolor.screens.MainMenuScreen;
import com.slucis.rightcolor.utils.CameraHelper;

public class WorldController extends InputAdapter implements Disposable {

	public enum GAME_STATE {
		END, PLAY, PAUSE
	};

	// Objects	
	private RightColorGame game;
	public UIManager uiManager;
	public Level level;
	
	// Game Attributes
	public GAME_STATE gameState;

	private boolean accelerometerAvailable;

	private float timeLeftEndGameDelay;

	// Camera Helper
	public CameraHelper cameraHelper;

	// Game Settings
	public int score;

	public WorldController(RightColorGame game) {
		this.game = game;
		this.uiManager = new UIManager(this);
		init();
	}	

	private void init() {
		accelerometerAvailable = Gdx.input
				.isPeripheralAvailable(Peripheral.Accelerometer);

		cameraHelper = new CameraHelper(this);
		initLevel();
	}

	private void initLevel() {
		gameState = GAME_STATE.PLAY;
		score = 0;
		level = new Level();
	}

	public void pause() {
		gameState = GAME_STATE.PAUSE;
	}

	public void resume() {
		gameState = GAME_STATE.PLAY;
	}

	public void update(float delta) {
		uiManager.update(MathUtils.clamp(delta, delta, 1 / 60f));
		
		switch (gameState) {
		case PAUSE:
			updatePause(delta);
			break;
		case PLAY:
			updatePlay(delta);			
			break;
		case END:
			updateEnd(delta);
			break;
		default:
			break;
		}
	}
	
	private void updatePause(float delta) {
		
	}
	
	private void updatePlay(float delta) {
		handleInput();
		level.update(delta);
		cameraHelper.update(delta);		
		checkEndGame();
	}
	
	private void checkEndGame() {
		// if ... { 
		// AudioManager.instance.stopMusic();
		// gameState = GAME_STATE.END;
		// timeLeftEndGameDelay = GlobalReference.TIME_DELAY_GAME_FINISHED;
		// if win {
		// 
		// } else {
		//
		// }
		//}
	}
	
	private void updateEnd(float delta) {
		if (timeLeftEndGameDelay <= 0) {
			backToMenu();
		} else {
			timeLeftEndGameDelay -= delta;
		}
	}

	private void handleInput() {
		if (Gdx.app.getType() == ApplicationType.Desktop) {
			// handleDesktopInput();
		} else {
			if (accelerometerAvailable) {
				// handleMobileInput();
			}
		}
	}

	private void backToMenu() {
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.P) {
			if (gameState == GAME_STATE.PLAY) {
				pause();
				uiManager.tBtnClick.setText("Resume");
			}
			else {
				resume();
				uiManager.tBtnClick.setText("Pause");
			}
		}
		return true;
	}

	@Override
	public void dispose() {
		uiManager.dispose();
	}
}
