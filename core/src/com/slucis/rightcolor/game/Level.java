package com.slucis.rightcolor.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.slucis.rightcolor.game.objects.Player;

public class Level {

	private Array<Integer> theRule;
	private int question;
	private int trueAnswer;
	private boolean isShowingRule;
	private int setIconID;
	private float stageTime;
	
	
	public Level() {
		theRule = new Array<Integer>();
		theRule.addAll(1, 2, 3);		
	}
	
	public void update(float delta) {		
		
	}

	public void render(SpriteBatch batch) {	
		
	}
	
	// Xáo random các vị trí
	private void genNewRule() {
		theRule.shuffle();
	}
	
	private boolean Check(int answer) {
		
		return false;
	}
	
	private void NewGameStage() {
		CreateGameStage();
		DisplayGameStage();
	}
	
	private void CreateGameStage() {
		
	}
	
	private void DisplayGameStage() {
		
	}
}
