package com.lgposse.game.controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.lgposse.game.app.GameApp;
import com.lgposse.game.views.GameInfoView;

public class GameInfoControl extends GameInfoView implements MouseListener {

	private static final long serialVersionUID = -1036313890339696994L;
	private GameApp gameApp;

	public GameInfoControl(GameApp gameApp) {
		super();
		this.gameApp = gameApp;
		btnJoinGame.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == btnJoinGame) {
			this.gameApp.gotGame(this.game);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}


}
