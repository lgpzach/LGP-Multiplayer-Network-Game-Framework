package com.lgposse.cards.views;

import com.lgposse.cards.models.Card;
import com.lgposse.cards.models.CardGame;

public class PlayfieldView extends HandView {

	private CardGame g;
	
	public PlayfieldView(CardGame g, boolean flipped) {
		super(g.playfield, flipped);
		this.g = g;
		this.update();
	}
	
	@Override
	public void update() {
		int r = 40;
		int n = h.cards.size();
		int i;
		for (Card c : h.cards) {
			i = g.players.indexOf(g.playerFromName(c.owner));
			CardView cv = new CardView(c, flipped);
			double t = 2 * Math.PI * i / n;
			int x = (int) Math.round(r * Math.cos(t));
			int y = (int) Math.round(r * Math.sin(t));
			this.add(cv, x, y);
		}
	}

}
