package com.lgposse.jumper.tests;

import org.junit.Test;

import com.lgposse.jumper.models.Board;
import com.lgposse.jumper.models.Graveyard;

public class GraveyardTest {

	@Test
	public void test() {
		Board b = new Board();
		Graveyard g = new Graveyard();
		g.add(b.pegByCoordinates(0, 0));
		g.add(b.pegByCoordinates(0, 0));
		g.add(b.pegByCoordinates(0, 0));
		g.add(b.pegByCoordinates(0, 0));
		g.add(b.pegByCoordinates(0, 0));
		g.add(b.pegByCoordinates(0, 0));
		g.add(b.pegByCoordinates(0, 0));
		g.add(b.pegByCoordinates(0, 0));
		g.add(b.pegByCoordinates(0, 0));
		g.add(b.pegByCoordinates(0, 0));
		g.add(b.pegByCoordinates(7, 0));
		g.add(b.pegByCoordinates(0, 0));
		g.add(b.pegByCoordinates(0, 0));
		g.add(b.pegByCoordinates(0, 0));
		System.out.println(g);
	}

}
