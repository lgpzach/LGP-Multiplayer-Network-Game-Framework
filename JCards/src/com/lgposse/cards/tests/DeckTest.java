package com.lgposse.cards.tests;

import org.junit.Test;

import com.lgposse.cards.models.Deck;

public class DeckTest {

	@Test
	public void test() {
		Deck d1 = new Deck(false);
		System.out.println(d1);
		Deck d2 = new Deck(true);
		System.out.println(d2);
	}

}
