package com.lgposse.cards.models;

import java.io.Serializable;

public class Card implements Comparable<Card>, Serializable {
	private static final long serialVersionUID = -1877228107277461245L;
	public int suit;
	public int rank;
	public String id;
	public String owner;
	
	public Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
		this.id = Integer.toString(suit) + "-" + Integer.toString(rank);
	}
	
	public String toString() {
		return "(" + this.id + ")\t[" + this.owner + "]\t" + decode_rank(this.rank) + " of " + decode_suit(this.suit);
	}
	
	public static String decode_rank(int rank) { 
		switch(rank) {
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			return Integer.toString(rank);
		case 11:
			return "jack";
		case 12:
			return "queen";
		case 13:
			return "king";
		case 1:
		case 14:
			return "ace";
		default:
			return "ERROR";
		}
	}
	
	public static String decode_suit(int suit) {
		switch(suit) {
		case 0:
			return "spades";
		case 1:
			return "clubs";
		case 2:
			return "diamonds";
		case 3:
			return "hearts";
		default:
			return "ERROR";
		}
	}

	public static String decode_rank_symbol(int rank) { 
		switch(rank) {
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			return Integer.toString(rank);
		case 11:
			return "J";
		case 12:
			return "Q";
		case 13:
			return "K";
		case 1:
		case 14:
			return "A";
		default:
			return "ERROR";
		}
	}
	
	public static String decode_suit_symbol(int suit) {
		String heart = "\u2665";
		String diamond = "\u2666";
		String spade = "\u2660";
		String club = "\u2663";
		switch(suit) {
		case 0:
			return spade;
		case 1:
			return club;
		case 2:
			return diamond;
		case 3:
			return heart;
		default:
			return "E";
		}
	}

	public int compareTo(Card other) {
		other = (Card) other;
		if(this.rank > other.rank) return 1;
		else if(this.rank < other.rank) return -1;
		else return 0;
	}
}
