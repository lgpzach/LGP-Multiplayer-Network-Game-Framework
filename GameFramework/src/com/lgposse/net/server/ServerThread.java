package com.lgposse.net.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;

import com.lgposse.net.common.ObjectListener;
import com.lgposse.net.common.TextRequest;
import com.lgposse.net.common.TextResponse;


public abstract class ServerThread extends Thread implements ObjectListener {
	
	protected MultiServer parent;
	protected Socket socket;
	protected ObjectOutputStream out;
	protected ObjectInputStream in;
	protected boolean loggingEnabled = true;
	protected int loggingPriority = 2;
	protected int id;
	protected String name;
	
	public ServerThread(MultiServer parent, Socket socket) throws IOException {
		try {
			log("Thread started.",2);
			
			// set up local variables
			this.parent = parent;
			this.socket = socket;
			
			// add self to multiServer
			id = parent.addClient(this);
			
			log("Got client ID: " + Integer.toString(id),2);
			
			//set up output /  input streams
			log("Starting output stream...",5);
			out = new ObjectOutputStream(socket.getOutputStream());
			log("Starting input stream...",5);
			in  = new ObjectInputStream(socket.getInputStream());
		}
		catch (SocketException e) { close(); }
	}
	
	@Override
	public void run() {
		try {
			Object input;

			while(((input = in.readObject()) != null)) {
				if (input instanceof String) {
					String i = (String) input;
					if(i.equals("Bye.")) {
						close();
						break;
					}
				} else {
					this.gotObject(input);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			//e.printStackTrace();
		}
	}
	
	public void sendObject(Serializable obj) {
		try {
			out.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void close() {
		log("Removing myself (ID #" + Integer.toString(id) + ")",2);
		parent.removeClient(this);
		try {
			out.close();
			in.close();
			socket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		log("Terminated successfully.",2);
	}
	
	private void log(String message, int priority) {
		if (loggingEnabled && (priority <= loggingPriority)) {
			System.out.println("ServerThread #" + Long.toString(this.getId()) + ": " + 
					message);
		}
	}
	
	/**
	 * Prompts the client for information.
	 * @param channel
	 * @param prompt
	 * @return
	 */
	public String textAsk(String channel, String prompt) {
		log(prompt,3);
		try {
			out.writeObject(new TextRequest(channel, prompt));
			Object nameresp = in.readObject();
			if(nameresp instanceof TextResponse) {
				String response = ((TextResponse) nameresp).text;
				log("Got answer: " + response,2);
				return response;
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
