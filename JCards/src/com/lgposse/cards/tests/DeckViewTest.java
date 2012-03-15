package com.lgposse.cards.tests;

import javax.swing.JFrame;

import acm.graphics.GCanvas;

import com.lgposse.cards.models.Deck;
import com.lgposse.cards.views.DeckView;


public class DeckViewTest implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new DeckViewTest().run();
	}

	public void run() {
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
		JFrame frame = new JFrame("Card Game");
		Deck d = new Deck(false);
		DeckView dv = new DeckView(d, false);
		GCanvas gc = new GCanvas();
		gc.add(dv,100,100);
		frame.add(gc);
		frame.setSize(640, 480);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
