package com.lgposse.net.tests;

import com.lgposse.game.models.Game;
import com.lgposse.net.client.Client;
import com.lgposse.net.common.ObjectListener;

public class NewClientTest implements ObjectListener {

	@Override
	public void gotObject(Object o) {
		if(o instanceof Game) {
			Game g = (Game) o;
			System.out.println(g.players);
			System.exit(0);
		}
	}
	
	public void run() {
		Client c;
		try {
			c = new Client(this, "localhost", 36777);
			c.start();
			ExampleGame g = new ExampleGame("test");
			c.sendObject(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new NewClientTest().run();

	}

}
