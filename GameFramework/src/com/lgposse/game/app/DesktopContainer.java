package com.lgposse.game.app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class DesktopContainer extends JFrame implements GameContainer, Runnable {
	private static final long serialVersionUID = 85189416056245610L;
	public GameApp gameApp;
	protected JPanel panel;
	protected CardLayout cardLayout;

	public DesktopContainer() {
		this.cardLayout = new CardLayout();
		this.panel = new JPanel(cardLayout);
	}
	
	public void setProperties(String title, Dimension dimensions) {
		this.setSize(dimensions);
		this.setLocation(100, 100);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(panel, BorderLayout.CENTER);
	}

	@Override
	public void setActiveComponent(Component comp) {
		panel.add(comp, comp.getName());
		cardLayout.show(panel, comp.getName());
	}
}
