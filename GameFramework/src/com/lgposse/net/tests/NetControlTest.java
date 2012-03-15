package com.lgposse.net.tests;

import com.lgposse.game.net.NetControl;

public class NetControlTest extends Thread {

	
	public static void main(String[] args) {
		new NetControlTest().start();
	}
	
	public void run() {
		NetControl n = new NetControl("localhost");
		
		ExampleGame game1 = new ExampleGame("game1");
		n.sendGame(game1);
//		Thread.sleep(2000);
		n.requestGame("game1");
//		Thread.sleep(2000);
		n.requestGame("game2");

		n.requestGame(game1);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		n.close();
		
	}

}
