package com.lgposse.tests;

import java.util.Hashtable;

import org.junit.Test;

public class PutTest {

	@Test
	public void test() {
		Hashtable<Integer, String> x = new Hashtable<Integer, String>();
		x.put(1, "hi");
		System.out.println(x.get(1));
		x.put(1, "hey");
		System.out.println(x.get(1));
	}

}
