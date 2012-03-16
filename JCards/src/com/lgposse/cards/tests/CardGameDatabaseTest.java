package com.lgposse.cards.tests;

import java.util.Random;

import org.junit.Test;

import com.lgposse.cards.database.CardGameDatabase;
import com.lgposse.cards.models.CardGame;

public class CardGameDatabaseTest {

	private CardGameDatabase d;
	
	@Test
	public void test() {
		//d= CardGameDatabaseTest.exampleCardGameDatabase();
		//listGetAndUpdateTest();
		//populateDatabaseWithSampleGames(15);
	}
	
	/**
	 * Tests all the main functions of CardGameDatabase, including listing, getting, and updating games.
	 */
	public void listGetAndUpdateTest() {
		CardGame g = (CardGame) d.getGame(5);
		System.out.println(g);
		g = CardGameTest.exampleCardGame();
		g.id = 5;
		d.updateGame(g);
		Random r = new Random();
		String name = Integer.toString(r.nextInt(3000));
		CardGame g2 = CardGameTest.exampleCardGame(name);
		int i = d.listGame(g2);
		System.out.println(i);
	}
	
	/**
	 * 
	 * @return
	 */
	public void populateDatabaseWithSampleGames(int numberOfGames) {
		d.removeAllGames();
		for(int i = 0; i < numberOfGames; i++) {
			System.out.println(d.openConnections);
			CardGame g = CardGameTest.exampleCardGame("Game " + Integer.toString(i));
			d.listGame(g);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	
	//public static CardGameDatabase exampleCardGameDatabase() {
		
		//return d;
	//}

}
