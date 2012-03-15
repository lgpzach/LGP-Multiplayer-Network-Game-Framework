package com.lgposse.cards.controllers;

import com.lgposse.cards.models.CardGame;
import com.lgposse.game.app.GameApp;
import com.lgposse.game.controllers.LobbyControl;

public class CardGameLobbyControl extends LobbyControl {

	private static final long serialVersionUID = 8019580529550282492L;

	public CardGameLobbyControl(GameApp gameApp) {
		super(gameApp);
	}

	@Override
	protected void createGame(String gameName) {
		CardGame cardGame = new CardGame(gameName);
		gameApp.gotGame(cardGame);
	}

}
