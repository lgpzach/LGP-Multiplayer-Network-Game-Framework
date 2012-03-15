package com.lgposse.user.models;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -4118506269976115794L;
	public String name;
	
	public User(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name + "\n";
	}
}
