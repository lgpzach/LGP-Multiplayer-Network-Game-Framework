package com.lgposse.jumper.tests;

import org.junit.Test;

import com.lgposse.jumper.models.Board;

// [ROW, COLUMN]

public class BoardTest {
	public Board b;
	@Test
	public void test() {
		b = new Board();
		pb();
		
		b.jump(b.pegByCoordinates(0, 0), b.pegByCoordinates(2, 0));
		b.jump(b.pegByCoordinates(1, 0), b.pegByCoordinates(3, 0));
		b.jump(b.pegByCoordinates(0, 1), b.pegByCoordinates(2, 3));
		
		b.jump(b.pegByCoordinates(7, 1), b.pegByCoordinates(5, 1));
		b.jump(b.pegByCoordinates(6, 1), b.pegByCoordinates(4, 1));
		
		// capture test
		b.jump(b.pegByCoordinates(3, 0), b.pegByCoordinates(5, 2));
		// double jump test
		b.jump(b.pegByCoordinates(5, 2), b.pegByCoordinates(5, 0));
		pb();
	}
	
	public void pb() {
		System.out.println(b);
	}

}
