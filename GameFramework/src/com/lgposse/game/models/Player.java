package com.lgposse.game.models;

import com.lgposse.user.models.User;

public class Player extends User {

	private static final long serialVersionUID = 1233707118230007111L;
	public int coins;
	
	public Player(String name) {
		super(name);
		this.coins = 0;
	}
}
