package com.lgposse.cards.tests;

import javax.swing.JFrame;

import com.lgposse.game.views.GameListView;

public class GameListViewTest {
	public static void main(String[] args) {
		new GameListViewTest().run();
	}

	public void run() {
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
		JFrame frame = new JFrame("Card Game");
		GameListView v = new GameListView(CardGameListTest.exampleCardGameList());
		frame.add(v);
		frame.setSize(640, 480);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
