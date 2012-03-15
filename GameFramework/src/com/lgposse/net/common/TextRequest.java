package com.lgposse.net.common;

import java.io.Serializable;

public class TextRequest implements Serializable {
	
	private static final long serialVersionUID = -3218934149330640172L;
	public String channel;
	public String text;
	
	public TextRequest(String channel, String text) {
		this.channel = channel;
		this.text = text;
	}
	
	public TextRequest(String channel) {
		this.channel = channel;
		this.text = "";
	}
}