package com.lgposse.cards.app;

import com.lgposse.cards.controllers.CardGameControl;
import com.lgposse.cards.controllers.CardGameLobbyControl;
import com.lgposse.cards.models.CardGame;
import com.lgposse.cards.tests.CardGameDatabaseTest;
import com.lgposse.game.app.GameApp;
import com.lgposse.game.app.GameContainer;
import com.lgposse.game.models.Game;
import com.lgposse.game.models.Player;

public class CardGameApp extends GameApp {

	public CardGame game;
	public CardGameControl gameControl;
	
	public CardGameApp(GameContainer container) {
		super(container);
		//database = CardGameDatabaseTest.exampleCardGameDatabase(); // TODO: make a real database extension
	}

	@Override
	public void gotPlayer(Player player) {
		this.player = player;
		this.lobbyControl = new CardGameLobbyControl(this);
		this.gameContainer.setActiveComponent(this.lobbyControl);
	}

	@Override
	public void gotGame(Game game) {
		this.game = (CardGame) game;
		this.gameControl = new CardGameControl(this);
		this.gameContainer.setActiveComponent(this.gameControl);
	}

	@Override
	public boolean canMove() {
		if(this.game.players.indexOf(this.player) == this.game.turn) return true;
		else return false;
	}

}
