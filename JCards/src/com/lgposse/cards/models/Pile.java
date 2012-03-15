package com.lgposse.cards.models;

import java.util.ArrayList;

public class Pile extends Hand {

	private static final long serialVersionUID = 125915265953598222L;

	public Pile(ArrayList<Card> cards) {
		super(cards);
	}

	public Pile() {
		super(new ArrayList<Card>());
	}
	
	@Override
	public void receiveCard(Card c) {
		this.cards.add(c);
	}
}
