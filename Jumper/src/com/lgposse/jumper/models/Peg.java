package com.lgposse.jumper.models;

import java.io.Serializable;

import com.lgposse.game.models.Model;

public class Peg extends Model implements Serializable {
	/**
	 * A gamepiece in the Jumer game
	 */
	private static final long serialVersionUID = -2910779755460601703L;
	public int id;
	public enum types {PLAYER_ONE, PLAYER_TWO, EMPTY};
	public types type;
	
	public Peg(int id, types type) {
		this.id   = id;
		this.type = type;
	}
	
	public Peg() {
		this.type = types.EMPTY;
	}
	
	public String toString() {
		switch(type) {
		case EMPTY:
			return ".";
		case PLAYER_ONE:
			return "O";
		case PLAYER_TWO:
			return "+";
		default:
			return "";
		}
	}
}
