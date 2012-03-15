package com.lgposse.game.controllers;

import com.lgposse.game.app.GameApp;
import com.lgposse.game.models.Player;
import com.lgposse.user.controllers.UserControl;
import com.lgposse.user.controllers.UserControlForm;

public class PlayerControl extends UserControl {

	private static final long serialVersionUID = 3687654745600827940L;
	private GameApp gameApp;

	public PlayerControl(GameApp gameApp) {
		super();
		this.form = new UserControlForm(this);
		this.add(form);
		this.gameApp = gameApp;
	}

	public void setUser(Player p) {
		this.gameApp.gotPlayer(p);
	}

	@Override
	public void registerUser(String username, String password, String email) {
		// TODO register user function
		
	}

	@Override
	public void loginUser(String username, String password) {
		// FIXME: login
		gameApp.gotPlayer(new Player(username));
	}

}
