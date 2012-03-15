package com.lgposse.cards.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck implements Serializable {

	private static final long serialVersionUID = 6113939450098517139L;
	public int id;
	public ArrayList<Card> cards;
	
	public Deck(boolean shuffled) {
		Random r = new Random();
		this.id = r.nextInt(1000);
		cards = new ArrayList<Card>();
		for(int suit = 0; suit < 4; suit++) {
			for(int rank = 2; rank < 15; rank++) {
				cards.add(new Card(suit, rank));
			}
		}
		if (shuffled) {
			this.shuffle();
		}
		for(Card c : cards) {
			c.id = Integer.toString(this.id) + "-" + c.id;
		}
	}
	
	public Deck() {}
	
	public String toString() {
		String out = "";
		for(Card c : cards) {
			out += c.toString() + '\n';
		}
		return out;
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public Hand dealNewHand(int cards) {
		ArrayList<Card> c = new ArrayList<Card>();
		for(int i = 0; i < cards; i++) c.add(this.pop_card());
		Hand h = new Hand(c);
		return h;
	}
	
	public Hand dealNewHand(int cards, String owner) {
		Hand h = this.dealNewHand(cards);
		h.setOwner(owner);
		return h;
	}
	
	private Card pop_card() {
		try {
			int size = cards.size() - 1;
			Card c = cards.get(size);
			cards.remove(size);
			return c;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Card pop_card(int index) {
		try {
			Card c = cards.get(index);
			cards.remove(index);
			return c;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return null;
	}
}
