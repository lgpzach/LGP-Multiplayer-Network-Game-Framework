package com.lgposse.net.common;

import java.io.Serializable;
import java.util.Date;

public class SampleDataObject implements Serializable {
	private static final long serialVersionUID = 7116700073575370935L;
	public String greeting = "Test string. Hello!";
	public int age = 19;
	public int atk = 10;
	public long fortunehash = 173834897;
	public char a = 'a';
	public Date date = new Date(System.currentTimeMillis());
	
	public String testStringMethod() {
		return "hello!";
	}
}