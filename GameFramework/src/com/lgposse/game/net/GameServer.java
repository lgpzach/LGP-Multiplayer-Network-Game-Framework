package com.lgposse.game.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Hashtable;

import com.lgposse.game.models.Game;
import com.lgposse.net.server.MultiServer;
import com.lgposse.net.server.MultiServerControl;
import com.lgposse.net.server.ServerThread;

public class GameServer extends MultiServer {

	public Hashtable<String, Game> games;
	
	public GameServer() {
		super();
		this.games = new Hashtable<String, Game>();
	}
	
	public static void main(String args[]) {
		GameServer server = new GameServer();
		server.start();
		MultiServerControl control = new MultiServerControl();
		control.start();
	}
	
	@Override
	public void run() {
		try {
			clients = new ArrayList<ServerThread>();
			server = new ServerSocket(36777);
			while(true) {
				new GameServerThread(this, server.accept()).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
