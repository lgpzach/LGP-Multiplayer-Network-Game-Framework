package com.lgposse.jumper.net;

import com.lgposse.game.net.NetControl;
import com.lgposse.jumper.app.JumperGameApp;

public class JumperNet extends NetControl {

	public JumperNet(JumperGameApp app, String host, int port) {
		super(app, host, port);
	}
	
	@Override
	public void gotObject(Object o) {
		// TODO Auto-generated method stub
		
	}

}
