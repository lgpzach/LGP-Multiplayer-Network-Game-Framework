package com.lgposse.cards.tests;

import javax.swing.JFrame;

import com.lgposse.cards.models.CardGame;
import com.lgposse.cards.views.PlayfieldView;

import acm.graphics.GCanvas;

public class PlayfieldViewTest implements Runnable {
	
	public static void main(String[] args) {
		new PlayfieldViewTest().run();
	}
	
	public void run() {
		JFrame frame = new JFrame("Card Game");
		frame.setSize(640, 480);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GCanvas gc = new GCanvas();
		
		CardGame game = CardGameTest.exampleCardGame();
		
		PlayfieldView pv = new PlayfieldView(game, false);
		
		gc.add(pv, frame.getWidth() / 2 - pv.getWidth() / 2 + 40, frame.getHeight() / 2 - pv.getHeight() / 2 + 20);
		frame.add(gc);
		frame.setVisible(true);
	}

}
