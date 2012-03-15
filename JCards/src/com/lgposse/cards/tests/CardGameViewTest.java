package com.lgposse.cards.tests;

import javax.swing.JFrame;

import com.lgposse.cards.models.CardGame;
import com.lgposse.cards.views.CardGameView;
import com.lgposse.game.models.Player;

public class CardGameViewTest implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CardGameViewTest().run();
	}

	public void run() {
		JFrame frame = new JFrame("Card Game");
		CardGame g = CardGameTest.exampleCardGame();
		Player me = g.players.get(0);
		CardGameView view = new CardGameView(g, me);
		frame.setSize(800, 600);
		frame.add(view);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
