package com.lgposse.cards.app;

import java.awt.Dimension;

import com.lgposse.game.app.DesktopContainer;

public class CardGameDesktop extends DesktopContainer {

	private static final long serialVersionUID = -3606136438509909648L;

	public CardGameDesktop() {
		super();
		this.gameApp = new CardGameApp(this);
		run();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CardGameDesktop().run();
	}

	@Override
	public void run() {
		this.setProperties("Card Game", new Dimension(800,600));
		this.setVisible(true);
	}

}
