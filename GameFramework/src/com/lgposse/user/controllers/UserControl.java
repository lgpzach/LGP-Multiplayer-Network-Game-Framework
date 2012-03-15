package com.lgposse.user.controllers;

import javax.swing.JPanel;

public abstract class UserControl extends JPanel {

	private static final long serialVersionUID = 6231326020654197989L;
	protected UserControlForm form;
	
	public UserControl() {
		this.setName("UserControl");
	}

	public abstract void registerUser(String username, String password, String email);
	public abstract void loginUser(String username, String password);
}
