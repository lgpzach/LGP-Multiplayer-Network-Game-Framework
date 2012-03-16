package com.lgposse.game.net;

import java.io.IOException;

import com.lgposse.game.app.GameApp;
import com.lgposse.game.models.Game;
import com.lgposse.net.client.Client;
import com.lgposse.net.common.ObjectListener;
import com.lgposse.net.common.TextRequest;

public class NetControl implements ObjectListener {
	protected Client client;
	protected GameApp app;
	
	public NetControl(GameApp app, String host, int port) {
		this.app = app;
		client = new Client(this, host, port);
		client.start();
	}
	
	public void requestGameList() {
		client.sendObject(new TextRequest("gamelistrequest"));
	}
	
	public void requestGame(Game g) {
		client.sendObject(new TextRequest("gamerequest", g.name));
	}
	
	public void requestGame(String name) {
		client.sendObject(new TextRequest("gamerequest", name));
	}
	
	public void sendGame(Game g) {
		client.sendObject(g);
	}

//	@Override
//	public void gotObject(Object o) {
//		if(o instanceof Game) {
//			app.gotGame((Game) o);
//		}
//	}
	
	@Override
	public void gotObject(Object o) {
		System.out.println(o);
		app.gotObject(o);
	}

	public void close() {
		client.sendObject("Bye.");
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
