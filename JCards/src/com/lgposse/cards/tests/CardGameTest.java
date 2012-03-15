package com.lgposse.cards.tests;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

import org.junit.Test;

import com.lgposse.cards.models.CardGame;
import com.lgposse.game.models.Player;

public class CardGameTest {

	CardGame game;
	Player u1;
	Player u2;
	Player u3;
	Player u4;
	@Test
	public void test() {
		game = new CardGame("0x12345");
		u1 = new Player("john");
		u2 = new Player("jane");
		u3 = new Player("jacky");
		u4 = new Player("jerry");
		game.addPlayer(u1);
		game.addPlayer(u2);
//		pg();
//		
//		PlayerTest();
//		cardTest();
//		turnTest();
//		scoreTest();
//		serializeTest();
		randomExampleCardGameTest();
	}
	
	private void randomExampleCardGameTest() {
		CardGame g = CardGameTest.exampleCardGame("random");
		System.out.println(g);
	}

	public void serializeTest() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(System.out);
			oos.writeObject(game);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void PlayerTest() {
		game.addPlayer(u3);
		game.addPlayer(u4);
		game.addPlayer(new Player("jaime")); // overloading Players, this will fail
		pgc();
	}

	public void cardTest() {
		game.startGame(); // nothing will work if game not started
		game.playCard(u1, game.hands.get(u1).cards.get(0).id);
		game.playCard(u1, game.hands.get(u1).cards.get(0).id); // won't play the card
		game.playCard(u2, game.hands.get(u2).cards.get(0).id);
		game.playCard(u1, game.hands.get(u2).cards.get(0).id); // won't play the card
		pg();
	}
	
	public void turnTest() {
		game.addPlayer(u3);
		game.addPlayer(u4);
		game.startGame();
		for(int i = 0; i < 10; i++) {
			game.endTurn();
			System.out.println(game.turn);
		}
		game.endGame();
		pgc();
	}
	
	public void scoreTest() {
		game.startGame();
		game.playCard(u1, game.hands.get(u1).cards.get(0).id);
		Player u = game.scoreHand();
		System.out.println(u); // should be null
		
		game.playCard(u2, game.hands.get(u2).cards.get(0).id);
		pgc();
		u = game.scoreHand();
		
		pgc();
		System.out.println(u); // should have either u1 or u2 names
	}
	
	public void pg() {
		System.out.println(game);
	}
	
	public void pgc() {
		System.out.println("Hands:\n" + game.hands.toString() + "\n"
						 + "Turn: " + Integer.toString(game.turn) + "\n"
						 + "Playfield contents:\n" + game.playfield.toString());
	}
	
	public static CardGame exampleCardGame() {
		CardGame g = new CardGame("Example");
		Player su1 = new Player("john");
		Player su2 = new Player("jane");
		Player su3 = new Player("jacky");
		Player su4 = new Player("jerry");
		g.addPlayer(su1);
		g.addPlayer(su2);
		g.addPlayer(su3);
		g.addPlayer(su4);
		g.startGame();
		g.playCard(su1, g.hands.get(su1).cards.get(0).id);
		g.playCard(su2, g.hands.get(su2).cards.get(0).id);
		g.playCard(su3, g.hands.get(su3).cards.get(0).id);
		g.playCard(su4, g.hands.get(su4).cards.get(0).id);
		return g;
	}
	
	public static CardGame exampleCardGame(String name) {
		Random r = new Random();
		int players = r.nextInt(4) + 1;
		
		CardGame game = new CardGame(name);
		
		for(int i = 0; i < players; i++) {
			Player p = new Player("Player" + Integer.toString(i));
			game.addPlayer(p);
		}
		game.startGame();
		
		boolean stop = false;
		for(Player p : game.players) {
			stop = r.nextBoolean();
			if(stop != true) {
				game.playCard(p, game.hands.get(p).cards.get(0).id);
			} else break;
		}
		return game;
	}

}
