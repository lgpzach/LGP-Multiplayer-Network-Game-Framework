package com.lgposse.game.database;

import java.util.ArrayList;

import com.lgposse.game.models.Game;
import com.lgposse.thirdparty.MySQLDatabase;

public abstract class GameDatabase extends MySQLDatabase {

	public GameDatabase(String hostname, int port, String database,
			String username, String password) {
		super(hostname, port, database, username, password);
	}
	
	public abstract Game getGame(int id);
	public abstract ArrayList<Game> getAllGames();
	
	public void removeAllGames() {
		super.truncate("games");
	}
}
