package com.lgposse.net.server;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

public class EchoServerThread extends ServerThread {

	public EchoServerThread(MultiServer parent, Socket socket)
			throws IOException {
		super(parent, socket);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gotObject(Object o) {
		this.sendObject((Serializable) o);
	}

}
