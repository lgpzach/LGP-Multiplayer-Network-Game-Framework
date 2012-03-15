package com.lgposse.cards.tests;

import org.junit.Test;

import com.lgposse.cards.models.Deck;
import com.lgposse.cards.models.Hand;

public class HandTest {

	@Test
	public void test() {
		Deck d = new Deck(false);
		Hand h1 = d.dealNewHand(10);
		System.out.println(h1);
		//System.out.println(d);
		Hand h2 = d.dealNewHand(15, "shad0w");
		System.out.println(h2);
	}

}
