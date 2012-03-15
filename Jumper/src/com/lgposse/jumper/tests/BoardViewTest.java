package com.lgposse.jumper.tests;

import javax.swing.JFrame;

import acm.graphics.GCanvas;

import com.lgposse.jumper.models.Board;
import com.lgposse.jumper.views.BoardView;

public class BoardViewTest implements Runnable {
	public static void main(String[] args) {
		new BoardViewTest().run();
	}
	
	public void run() {
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
		JFrame frame = new JFrame("Card Game");
		frame.setSize(640, 480);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GCanvas c = new GCanvas();
		BoardView v = new BoardView(new Board());
		v.scale(3);
		c.add(v);
		frame.add(c);
		
		frame.setVisible(true);
	}
}
