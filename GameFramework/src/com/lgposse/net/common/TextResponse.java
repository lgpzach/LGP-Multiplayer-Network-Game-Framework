package com.lgposse.net.common;

import java.io.Serializable;

public class TextResponse implements Serializable {

	private static final long serialVersionUID = 7636211664761119814L;
	public String channel;
	public String text;
	
	public TextResponse(String channel, String text) {
		this.channel = channel;
		this.text = text;
	}
}