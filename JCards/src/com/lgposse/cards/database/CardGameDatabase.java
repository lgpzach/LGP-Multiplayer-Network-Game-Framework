package com.lgposse.cards.database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lgposse.cards.models.CardGame;
import com.lgposse.game.database.GameDatabase;
import com.lgposse.game.models.Game;

public class CardGameDatabase extends GameDatabase {

	public CardGameDatabase(String hostname, int port, String database,
			String username, String password) {
		super(hostname, port, database, username, password);
	}

	@Override
	public CardGame getGame(int id) {
		try {
			super.connect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		CardGame g = null;
		ResultSet r = this.getOne("games", "*", "id = '" + id + "'");
		try {
			while(r.next()) {
				ObjectInputStream gameData =
						new ObjectInputStream(r.getBlob("data")
								.getBinaryStream());
				g = (CardGame) gameData.readObject();
			}
		} catch (SQLException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.close();
		}
		return g;
	}

	public int listGame(CardGame g) {
		try {
			super.connect();
			PreparedStatement s = this.connection.prepareStatement("insert into " +
					this.database + ".games values(default, ?, ?)");
			s.setString(1, g.name);
			s.setBlob(2, g.getInputStream());
			s.execute();
			ResultSet r = this.getOne("games", "id", "name = '" + g.name + "'");
			int id = -1;
			if(r.next()) {
				id = r.getInt(1);
				g.id = id;
				this.updateGame(g);
			}
			this.close();
			super.close();
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int updateGame(CardGame g) {
		try {
			super.close();
			super.connect();
			PreparedStatement s = this.connection.prepareStatement("update " +
					this.database + ".games set data = ? where id = ?");
			s.setBlob(1, g.getInputStream());
			s.setInt(2, g.id);
			s.execute();
			ResultSet r = this.getOne("games", "id", "name = '" + g.name + "'");
			int id = -1;
			if(r.next()) {
				id = r.getInt(1);
			}
			super.close();
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public ArrayList<Game> getAllGames() {
		ArrayList<Game> games = new ArrayList<Game>();
		try {
			this.connect();
			ResultSet r = this.getAll("games");
			while(r.next()) {
				ObjectInputStream gameData =
						new ObjectInputStream(r.getBlob("data")
								.getBinaryStream());
				CardGame g = (CardGame) gameData.readObject();
				games.add(g);
			}
			this.close();
		} catch (SQLException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return games;
	}

}
