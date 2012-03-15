package com.lgposse.cards.tests;

import org.junit.Test;

import com.lgposse.cards.models.Card;

public class CardTest {

	@Test
	public void test() {
		Card c = new Card(1,8);
		System.out.println(c);
	}

}
