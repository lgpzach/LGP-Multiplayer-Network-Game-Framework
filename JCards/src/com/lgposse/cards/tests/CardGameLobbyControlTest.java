package com.lgposse.cards.tests;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;

import com.lgposse.cards.app.CardGameApp;
import com.lgposse.cards.controllers.CardGameLobbyControl;
import com.lgposse.game.app.GameContainer;

public class CardGameLobbyControlTest extends JFrame implements GameContainer {
	private static final long serialVersionUID = 2235204288765501048L;

	public static void main(String[] args) {
		new CardGameLobbyControlTest().run();
	}
	
	public void run() {
		this.setLayout(new BorderLayout());
		this.setSize(800,600);
		CardGameLobbyControl control = new CardGameLobbyControl(new CardGameApp(this));
		this.add(control);
		this.setVisible(true);
	}

	@Override
	public void setActiveComponent(Component comp) {
		// TODO Auto-generated method stub
		
	}
}
