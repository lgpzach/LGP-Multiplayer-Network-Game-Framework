package com.lgposse.cards.models;

import java.util.Collections;
import java.util.Hashtable;

import com.lgposse.game.models.Game;
import com.lgposse.game.models.Player;

public class CardGame extends Game {

	private static final long serialVersionUID = -794289479845268167L;
	public Deck deck;
	public Hashtable<Player, Hand> hands;
	public Pile playfield;
	public Pile discard;
	public int cardsPerHand;
	
	public CardGame(String id) {
		super(id);
		this.type = "Card Game";
		this.deck = new Deck(true);
		this.maxPlayers = 4;
		this.cardsPerHand = 3;
		this.turn = 0;
		this.hands = new Hashtable<Player, Hand>();
		this.playfield = new Pile();
		this.discard = new Pile();
	}

	/**
	 * Add a player to the game. Only works if game is joinable, and max players haven't been added yet.
	 */
	@Override
	public boolean addPlayer(Player p) {
		if(this.state == states.JOINABLE && this.players.size() < this.maxPlayers) {
			this.players.add(this.turn, p);
			this.turn++;
			this.hands.put(p, this.deck.dealNewHand(this.cardsPerHand, p.name));
			return true;
		}
		return false;
	}

	@Override
	public void dropPlayer(Player p) {
		this.players.remove(this.players.indexOf(p));
		discardAll(p);
		this.hands.remove(p);
	}

	private void discardAll(Player p) {
		for(int i = 0; i < (this.hands.get(p).cards.size() - 1); i++) {
			deck.cards.add(this.hands.get(p).cards.get(i));
			this.hands.get(p).cards.remove(this.hands.get(i));
		}
		deck.shuffle();
	}

	@Override
	public void startGame() {
		if(this.players.size() > 0) {
			this.state = states.IN_PROGRESS;
			this.turn = 0;
		}
	}

	@Override
	public void endGame() {
		this.state = states.FINISHED;
	}
	
	public String toString() {
		String out = "Game ID:" + this.id + "\n";
		out += "Deck: \n" + this.deck + "\n";
		out += "Players: \n" + this.hands.toString() + "\n";
		out += "Turn:" + this.turn + "\n";
		out += "State:" + this.state.toString() + "\n";
		out += "Current Players\n" + this.players.toString() + "\n";
		out += "Playfield contents\n" + this.playfield.toString() + "\n";
		return out;
	}
	
	/**
	 * Card game functions
	 */
	
	public void playCard(Player p, Card c) {
		if((hands.get(p).cards.contains(c)) && // card is in your hand
			(this.players.get(turn) == p) && // your turn
			(this.inProgress())) // game is in progress
		{
			hands.get(p).giveCard(c, playfield);
			this.endTurn();
		} else {
			System.out.println("Card not played.");
		}
	}
	
	public void playCard(Player p, String cardId) {
		Card c = hands.get(p).cardFromId(cardId);
		if((c != null) && (this.inProgress())) {
			this.playCard(p, c);
		} else {
			System.out.println("Card not played.");
		}
	}
	
	/**
	 * Score a hand. All card games will override this method.
	 * @return the user who won the hand
	 */
	public Player scoreHand() {
		if(this.inProgress() && this.playfieldFull()) {
			Collections.sort(playfield.cards);
			Collections.reverse(playfield.cards);
			Player p = this.playerFromName(playfield.cards.get(0).owner);
			this.playfieldCleanup();
			return p;
		} else return null;
	}
	
	/**
	 * Get the hand with the 
	 * @param p
	 * @return
	 */
	public Hand getHand(Player p) {
		return this.hands.get(p);
	}
	
	/**
	 * Helper methods
	 */
	
	/**
	 * Is the game in progress?
	 * @return true if game is in progress
	 */
	private boolean inProgress() {
		if (this.state == states.IN_PROGRESS) return true;
		else return false;
	}
	
	/**
	 * Have all the players played?
	 * @return true if all players have played in the current round
	 */
	private boolean playfieldFull() {
		if(this.playfield.cards.size() == this.players.size()) {
			return true;
		} else return false;
	}
	
	/**
	 * Move all cards from playfield to discard pile
	 */
	private void playfieldCleanup() {
		discard.cards.addAll(playfield.cards);
		playfield.cards.clear();
	}
}
