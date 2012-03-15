package com.lgposse.jumper.app;

import com.lgposse.game.app.GameApp;
import com.lgposse.game.app.GameContainer;
import com.lgposse.game.models.Game;
import com.lgposse.game.models.Player;
import com.lgposse.jumper.controllers.JumperGameControl;
import com.lgposse.jumper.models.JumperGame;

public class JumperGameApp extends GameApp {

	public JumperGame game;
	
	public JumperGameApp(GameContainer gameContainer) {
		super(gameContainer);
	}

	@Override
	public void gotPlayer(Player player) {
		this.player = player;
		JumperGame game = new JumperGame("test");
		if(game.addPlayer(player)) {
			game.addPlayer(new Player("player2")); // debug
			this.gotGame(game);
		}
	}

	@Override
	public void gotGame(Game game) {
		this.game = (JumperGame) game;
		this.gameContainer.setActiveComponent(new JumperGameControl(this));
	}
	
	@Override
	public boolean canMove() {
		if(this.game.players.indexOf(this.player) == this.game.turn) return true;
		else return false;
	}
}
