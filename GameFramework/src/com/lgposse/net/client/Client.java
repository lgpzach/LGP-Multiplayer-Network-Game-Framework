package com.lgposse.net.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;
import com.lgposse.net.common.ObjectListener;

/**
 * Oi... building off my previous old client, this new client is
 * meant to be a component, not a standalone app.
 * @author Zach
 *
 */
public class Client extends Thread {
	protected ObjectListener listener;
	protected Socket socket;
	protected ObjectOutputStream out;
	protected ObjectInputStream in;
	
	public Client(ObjectListener listener, String host, int port) throws Exception {
		this.listener = listener;
		socket = new Socket(host, port);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	public void sendObject(Serializable obj) {
		try {
			out.writeObject(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			Object input = null;
			while(true) {
				input = in.readObject();
				if(input != null) {
					listener.gotObject(input);
				}
			}
		} catch (SocketException e) {
			try {
			this.out.close();
			this.in.close();
			this.socket.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() throws IOException {
		this.out.close();
		this.in.close();
		this.socket.close();
		
	}
}
