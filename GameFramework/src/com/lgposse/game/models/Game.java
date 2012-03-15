package com.lgposse.game.models;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public abstract class Game extends Model implements Serializable {
	private static final long serialVersionUID = 6388853509844777124L;
	public int id;							// game ID, used for storage and retrieval purposes
	public String name;						// game name. ex: "shad0w's game." for use in lobbys and for identification
	public String type;						// game type. ex: "card game", "tic tac toe", etc.
	public ArrayList<Player> players;		// list of current players
	public int maxPlayers;					// maximum players. game won't let more than this amount join
	public enum states {					// game states. either the game hasn't started, it has, or it has ended.
		JOINABLE, IN_PROGRESS, FINISHED
	};
	public int turn;						// if the game supports turns, this is a valuable asset
	public states state;					// current game state
	
	public Game(String name) {
		this.name = name;
		this.players = new ArrayList<Player>();
		this.state = states.JOINABLE;
	}
	
	public abstract boolean addPlayer(Player p);
	public abstract void dropPlayer(Player p);
	public abstract void startGame();
	public abstract void endGame();
	
	/**
	 * Ends the user's turn.
	 * @return whether the turn ended or not.
	 */
	public boolean endTurn() {
		if(this.state == states.IN_PROGRESS) {
			try {
				this.players.get(turn + 1);
				turn++;
			} catch (IndexOutOfBoundsException e) {
				turn = 0;
			}
			return true;
		} else return false;
	}
	
	/**
	 * Gives a user's object from their name
	 * @param name
	 * @return the user object
	 */
	public Player playerFromName(String name) {
		for(Player p : this.players) {
			if(p.name == name) return p;
		}
		return null;
	}
	
	/**
	 * Returns a serialized version of the game in a ByteArrayInputStream
	 * @return ByteArrayInputStream of game
	 */
	public ByteArrayInputStream getInputStream() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			oos.close();
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			return bais;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
