package com.lgposse.net.server;

import java.util.Scanner;

public class MultiServerControl extends Thread {
	
	public void run() {
		System.out.println("Type anything and press enter to exit.");
		Scanner s =new Scanner(System.in);
		s.next();
		System.exit(1);
	}

}