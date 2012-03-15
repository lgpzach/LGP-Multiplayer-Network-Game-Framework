package com.lgposse.cards.models;

import java.util.ArrayList;

public class Hand extends Deck {

	private static final long serialVersionUID = -1030527177706914182L;
	public String owner;
	
	public Hand(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public void setOwner(String owner) {
		// take ownership of all the cards in the hand
		this.owner = owner;
		for(Card c : this.cards) {
			c.owner = this.owner;
		}
	}
	
	public void giveCard(Card c, Hand h) {
		h.receiveCard(c);
		this.cards.remove(this.cards.indexOf(c));
	}
	
	public void receiveCard(Card c) {
		c.owner = this.owner;
		this.cards.add(c);
	}

	public Card cardFromId(String id) {
		for(Card c : this.cards) {
			if(c.id == id) return c;
		}
		return null;
	}
}
