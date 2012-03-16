package com.lgposse.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class MultiServer extends Thread {
	public static void main(String args[]) {
		MultiServer theServer = new MultiServer();
		theServer.start();
		MultiServerControl control = new MultiServerControl();
		control.start();
	}
	
	protected ServerSocket server;
	protected ArrayList<ServerThread> clients;
		
	public void run() {
		try {
			clients = new ArrayList<ServerThread>();
			server = new ServerSocket(36777);
			while(true) {
				new EchoServerThread(this, server.accept()).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int addClient(ServerThread thread) {
		clients.add(thread);
		return clients.indexOf(thread);
	}
	
	public void removeClient(ServerThread thread) {
		clients.remove(thread);
	}
}