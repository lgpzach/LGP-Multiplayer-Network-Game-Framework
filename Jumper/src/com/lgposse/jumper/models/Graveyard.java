package com.lgposse.jumper.models;

import java.util.ArrayList;

import com.lgposse.game.models.Model;

public class Graveyard extends Model {

	private static final long serialVersionUID = 8094024648332548935L;
	protected ArrayList<Peg> pegs;
	
	public Graveyard() {
		this.pegs = new ArrayList<Peg>();
	}
	
	public void add(Peg p) {
		this.pegs.add(new Peg(-1, p.type));
	}
	
	public void removeAll(Peg p) {
		this.pegs.removeAll(pegs);
	}
	
	public ArrayList<Peg> getPegs() {
		return this.pegs;
	}
	
	public String toString() {
		int count = 16;
		String out = "";
		String empty = new Peg(-1, Peg.types.EMPTY).toString();
		for(Peg p : pegs) {
			out += p.toString();
			if(count == 9) out += "\n";
			count--;
		}
		if(count > 8) {
			for(int i = count; i > 8; i--) {
				out += empty;
			}
			out += "\n";
			for(int i = 8; i > 0; i--) {
				out += empty;
			}
		} else {
			for(int i = count; i > 0; i--) {
				out += empty;
			}
		}
		return out;
	}
}
