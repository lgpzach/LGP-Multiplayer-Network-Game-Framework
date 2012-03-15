package com.lgposse.cards.views;

import java.util.ArrayList;

import com.lgposse.cards.models.CardGame;
import com.lgposse.cards.models.Hand;
import com.lgposse.game.models.Player;
import com.lgposse.game.views.View;


import acm.graphics.GCanvas;
import acm.graphics.GPoint;

public class CardGameView extends GCanvas implements View {
	private static final long serialVersionUID = 6763581701346673298L;
	CardGame g;
	Player me;
	ArrayList<HandView> handViews;
	PlayfieldView playfieldView;
	//DiscardView discardView;

	public CardGameView(CardGame g, Player me) {
		this.setSize(800,600);
		this.me = me;
		this.g = g;
		handViews = new ArrayList<HandView>();
		playfieldView = new PlayfieldView(g, false);
		this.update();
	}
	
	public void update() {
		this.handViews.clear();
		this.removeAll();
		this.playfieldView.update();
		int i = 0;
		
		int ime = g.players.indexOf(me); // index of me
		
		for(Player p : g.players) {
			boolean flipped;
			if(p.equals(me)) flipped = false;
			else flipped = true;
			
			boolean horizontal;
			if (i % 2 == 0) horizontal = true;
			else horizontal = false;
			
			Hand h = g.getHand(p);
			HandView hv = new HandView(h, horizontal, flipped);
			handViews.add(hv);
			
			String pos = decodePosition(i, ime);
			//String pos = decodePosition(i);
			
			this.add(hv, this.getPosition(hv, pos));
			i++;
		}
		this.add(playfieldView, this.getWidth() / 2 - playfieldView.getWidth() / 2 + 40, this.getHeight() / 2 - playfieldView.getHeight() / 2 + 40);
	}
	
/**	private String decodePosition(int i) {
		switch(i) {
		case 0:
			return "south";
		case 1:
			return "west";
		case 2:
			return "north";
		case 3:
			return "east";
		default:
			return "";
		}
	} **/
	
	private String decodePosition(int i, int me) {
		if(i == me) return "south";
		else if(within(1, i, me)) return "east";
		else if(within(2, i, me)) return "north";
		else if(within(3, i, me)) return "west";
		else return null;
	}
	
	private boolean within(int steps, int i, int me) {
		if(((i + steps) == me) || ((i - steps) == me)) return true;
		else return false;
	}
	
	private GPoint getPosition(HandView hv, String position) {
		int wt = this.getWidth(); // total width
		int ht = this.getHeight(); // total height
		int wh = wt / 2; // half width
		int hh = ht / 2; // half height
		
		int xt = (int) Math.round(hv.getWidth()); // hand width
		int yt = (int) Math.round(hv.getHeight()); // hand height
		int xh = xt / 2; // hand half width
		int yh = yt / 2; // hand half height
		
		GPoint d;
		switch(position) {
		case "north":
			d = new GPoint(wh - xh, 0);
			break;
		case "south":
			d = new GPoint(wh - xh, ht - yt);
			break;
		case "east":
			d = new GPoint(wt - xt, hh - yh);
			break;
		case "west":
			d = new GPoint(0, hh - yh);
			break;
		default:
			d = null;
			break;
		}
		return d;
	}
}