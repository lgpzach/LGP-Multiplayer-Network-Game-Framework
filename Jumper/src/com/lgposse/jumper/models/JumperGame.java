package com.lgposse.jumper.models;

import java.util.Hashtable;

import com.lgposse.game.models.Game;
import com.lgposse.game.models.Player;

/**
 * @author Zach
 *
 */
public class JumperGame extends Game {
	
	private static final long serialVersionUID = -2397711178707199652L;
	public Hashtable<Player, Peg.types> playerOrder;
	public Board board;
	
	public JumperGame(String name) {
		super(name);
		this.type = "Jumper";
		this.maxPlayers = 2;
		this.turn = 0;
		this.board = new Board();
		playerOrder = new Hashtable<Player, Peg.types>();
	}

	@Override
	public boolean addPlayer(Player p) {
		boolean success;
		if(this.state == Game.states.JOINABLE && this.players.size() < this.maxPlayers) {
			this.players.add(p);
			switch(this.players.size()) {
			case 1:
				this.playerOrder.put(p, Peg.types.PLAYER_ONE);
				break;
			case 2:
				this.playerOrder.put(p, Peg.types.PLAYER_TWO);
				break;
			}
			success = true;
		} else success = false;
		if(this.players.size() == this.maxPlayers) startGame();
		return success;
	}

	@Override
	public void dropPlayer(Player p) {
		try {
			this.players.remove(p);
		} catch (Exception e) { e.printStackTrace(); }
		
		if(this.state == Game.states.IN_PROGRESS) {
			if(this.players.size() < this.maxPlayers) {
				this.endGame();
			}
		}
	}

	@Override
	public void startGame() {
		this.state = states.IN_PROGRESS;
	}

	@Override
	public void endGame() {
		this.state = states.FINISHED;
	}

	public boolean isTurn(Player player) {
		if(this.players.indexOf(player) == this.turn) return true;
		else return false;
	}

	public boolean isPiece(Player player, Peg peg) {
		if(this.playerOrder.get(player) == peg.type) return true;
		else return false;
	}

}
