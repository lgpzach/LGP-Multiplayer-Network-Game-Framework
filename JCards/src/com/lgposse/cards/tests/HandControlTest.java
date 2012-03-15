package com.lgposse.cards.tests;

import javax.swing.JFrame;

import acm.graphics.GCanvas;

import com.lgposse.cards.controllers.HandControl;
import com.lgposse.cards.models.Deck;
import com.lgposse.cards.models.Hand;

public class HandControlTest implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new HandControlTest().run();
	}

	public void run() {
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
		JFrame frame = new JFrame("Card Game");
		Deck d = new Deck(false);
		//Hand h1 = d.dealNewHand(13);
		Hand h2 = d.dealNewHand(13);
		//Hand h3 = d.dealNewHand(13);
		//Hand h4 = d.dealNewHand(13);
		//HandControl v1 = new HandControl(h1, true, false);
		HandControl v2 = new HandControl(h2, true, true);
		//HandControl v3 = new HandControl(h3, true, false);
		//HandControl v4 = new HandControl(h4, true, false);
		GCanvas gc = new GCanvas();
		//gc.add(v1,20,20);
		gc.add(v2,20,170);
		//gc.add(v3,20,330);
		//gc.add(v4,20,480);
		frame.add(gc);
		frame.setSize(640, 480);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
