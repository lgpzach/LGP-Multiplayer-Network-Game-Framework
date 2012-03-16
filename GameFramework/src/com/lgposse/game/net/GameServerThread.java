package com.lgposse.game.net;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import com.lgposse.game.database.GameList;
import com.lgposse.game.models.Game;
import com.lgposse.net.common.TextRequest;
import com.lgposse.net.server.ServerThread;

public class GameServerThread extends ServerThread {
	protected GameServer parent;

	public GameServerThread(GameServer parent, Socket socket)
			throws IOException {
		super(parent, socket);
		this.parent = parent;
	}

	@Override
	public void gotObject(Object o) {
		if(o instanceof Game) {
			Game game = (Game) o;
			parent.games.put(game.name, game);
			System.out.println("Game updated: " + game.name);
			this.parent.broadcastObject(new GameList(this.parent.games.values()));
		}
		else if(o instanceof TextRequest) {
			TextRequest req = (TextRequest) o;
			switch(req.channel) {
			case "gamerequest":
				String name = req.text;
				if(parent.games.get(name) != null) {
					this.sendObject(parent.games.get(name));
				}
				break;
			case "gamelistrequest":
				GameList gameList = new GameList();
				gameList.games = new ArrayList<Game>(parent.games.values());
				this.sendObject(gameList);
				break;
			}
		}
	}

}
