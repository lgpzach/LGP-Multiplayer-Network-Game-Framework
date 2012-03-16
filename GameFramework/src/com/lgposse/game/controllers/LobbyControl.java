package com.lgposse.game.controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import com.lgposse.game.app.GameApp;
import com.lgposse.game.database.GameList;
import com.lgposse.game.views.GameListView;

public abstract class LobbyControl extends GameListView implements MouseListener {

	private static final long serialVersionUID = -7019307990844111701L;
	protected GameApp gameApp;

	public LobbyControl(GameApp gameApp) {
		super(new GameList());
		this.gameApp = gameApp;
		this.requestGameList();
		initializeComponents();
	}
	
	public void requestGameList() {
		gameApp.netControl.requestGameList();
	}

	private void initializeComponents() {
		this.setName("LobbyControl");
		this.infoPanel = new GameInfoControl(gameApp);
		this.btnCreateGame.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == btnCreateGame) {
			String gameName = JOptionPane.showInputDialog("Game name? ", gameApp.player.name + "'s game");
			this.createGame(gameName);
		}
	}

	protected abstract void createGame(String gameName);
	
	public void gotGameList(GameList list) {
		this.gameList = list;
		this.update();
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
