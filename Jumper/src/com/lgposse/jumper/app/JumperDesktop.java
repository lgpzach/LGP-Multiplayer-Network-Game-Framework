package com.lgposse.jumper.app;

import java.awt.Dimension;

import com.lgposse.game.app.DesktopContainer;

public class JumperDesktop extends DesktopContainer {

	private static final long serialVersionUID = 1512377791338725256L;

	public JumperDesktop() {
		this.gameApp = new JumperGameApp(this);
	}
	
	@Override
	public void run() {
		this.setProperties("Jumper", new Dimension(800,600));
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new JumperDesktop().run();
	}

}
