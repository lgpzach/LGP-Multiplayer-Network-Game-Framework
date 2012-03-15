package com.lgposse.cards.tests;

import org.junit.Test;

import com.lgposse.cards.database.CardGameDatabase;
import com.lgposse.game.database.GameList;

public class CardGameListTest {

	@Test
	public void test() {
		GameList gameList = CardGameListTest.exampleCardGameList();
		System.out.println(gameList);
	}
	
	public static GameList exampleCardGameList() {
		CardGameDatabase cardGameDatabase = CardGameDatabaseTest.exampleCardGameDatabase();
		GameList gameList = new GameList(cardGameDatabase);
		return gameList;
	}

}
