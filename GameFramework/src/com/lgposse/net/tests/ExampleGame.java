package com.lgposse.net.tests;

import com.lgposse.game.models.Game;
import com.lgposse.game.models.Player;

public class ExampleGame extends Game {

	public ExampleGame(String name) {
		super(name);
		this.type = "Example game";
		this.maxPlayers = 1;
		this.addPlayer(new Player("example player"));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7131387830412406822L;

	@Override
	public boolean addPlayer(Player p) {
		this.players.add(p);
		this.startGame();
		return true;
	}

	@Override
	public void dropPlayer(Player p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startGame() {
		this.state = Game.states.IN_PROGRESS;
	}

	@Override
	public void endGame() {
		// TODO Auto-generated method stub

	}

}
