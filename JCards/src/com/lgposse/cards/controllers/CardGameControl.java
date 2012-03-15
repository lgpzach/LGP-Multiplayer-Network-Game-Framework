package com.lgposse.cards.controllers;

import com.lgposse.cards.app.CardGameApp;
import com.lgposse.cards.views.CardGameView;

public class CardGameControl extends CardGameView {

	private static final long serialVersionUID = 360829195377535582L;

	public CardGameControl(CardGameApp gameApp) {
		super(gameApp.game, gameApp.player);
		this.setName("CardGameControl");
	}

}
