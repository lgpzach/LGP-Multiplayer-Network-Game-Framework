package com.lgposse.game.net;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import com.lgposse.game.app.GameApp;
import com.lgposse.game.database.GameList;
import com.lgposse.game.models.Game;

public class NewGameList implements Serializable {

	private static final long serialVersionUID = -3895494422957584450L;
	public ArrayList<Game> games;
	protected GameApp app;
	
	public NewGameList(GameApp app) {
		this.app = app;
		this.update();
	}
	
	public NewGameList() {
		this.games = new ArrayList<Game>();
	}
	
	public void update() {
		app.netControl.requestGameList();
	}
	
	public String toString() {
		String out = "";
		for(Game g : this.games) {
			out += GameList.gameToString(g);
		}
		return out;
	}
	
	public static String gameToString(Game g) {
		return String.format("Game ID: %s. Name: %s. Players: %s / %s.\n", Integer.toString(g.id), g.name, Integer.toString(g.players.size()), g.maxPlayers);
	}
	
	public String[] toArray() {
		ArrayList<String> arrayList = new ArrayList<String>();
		for(Game g : this.games) {
			arrayList.add(g.name);
		}
		String[] stringArray = Arrays.copyOf(arrayList.toArray(), arrayList.toArray().length, String[].class);
		return stringArray;
	}
}
