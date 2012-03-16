package com.lgposse.game.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.lgposse.game.models.Game;

public class GameList implements Serializable {

	private static final long serialVersionUID = 8050447185512807994L;
	public ArrayList<Game> games;
	
	public GameList() {
		this.games = new ArrayList<Game>();
	}
	
	public GameList(Collection<Game> games) {
		this.games = new ArrayList<Game>(games);
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
